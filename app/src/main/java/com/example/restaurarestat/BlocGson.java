package com.example.restaurarestat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BlocGson extends AppCompatActivity {
    ArrayList<Bloc> mBlocList;

    private RecyclerView mRecyclerView;
    private BlocAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //quan es tomba o locale canvia, runtime configuration changes...
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Gson gson = new Gson();
        String json = gson.toJson(mBlocList);
        outState.putString("blocs", json);
        Log.d("test", "onSaveInstanceState: paso onSave");
    }

    // l'alternativa d aquest de sota, és fer-ho al oncreate, com a la MainActivity
    //   if (savedInstanceState != null) {
    //      ....
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String json = savedInstanceState.getString("blocs");
        Type type = new TypeToken<ArrayList<Bloc>>() {}.getType();
        Gson gson = new Gson();
        mBlocList = gson.fromJson(json, type);


        //mAdapter.notifyDataSetChanged(); no pot propagar canvis, no va...
        buildRecyclerView();


        Log.d("test", "onRestoreInstanceState: paso rest"+
                mBlocList.get(0).getHoraInici()+mBlocList.get(1).getHoraInici());

        if (mBlocList == null) {
            mBlocList = new ArrayList<>();
            Log.d("test", "onRestoreInstanceState: null!!");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloc_gson);

        //https://www.youtube.com/watch?v=jcliHGR3CHo&list=LLSrVQD6aijhcCpv8EeUZXBQ&index=12&t=0s

        //loadData();
        // prova  a comentar loadData(), descomenta el de sota, i en tombar, veuràs que es perd la
        // llista recycler...
        if (mBlocList == null) {
            mBlocList = new ArrayList<>();
        }
        // solució? sobreescriure onSaveInstanceState i onRestoreInstanceState
        // comenta els mètodes de sobre per veure que en tombar, es perd la llista...

        // TODO: 14/01/20 fer botó load per loadData...

        Log.d("test", "onCreate: ordre 1.onSaveInstanceState, 2.onCreate 3.onRestoreInstanceState");
        buildRecyclerView();
        setInsertButton();

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        //guarda un ArrayList de Blocs de temperatures

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        //li podries passar un objecte Bloc aquí sota... tot ho passa a String...
        String json = gson.toJson(mBlocList);

        //també es fa servir gson.toJson per passar paràmetre com a String, a una nova activity
        //amb intent.putExtra("key", json)....
        // i el fromJson(getIntent().getStringExtra("key"),Bloc.class) per recuperar l objecte a la nova activity

        editor.putString("blocs", json);
        editor.apply();
        Log.d("test", "saved "+json);

    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("blocs", null);
        //recupera el String, i el passa al tipus que necessitem
        Type type = new TypeToken<ArrayList<Bloc>>() {}.getType();
        mBlocList = gson.fromJson(json, type);
        //si fos un objecte Bloc el que volgués carregar, seria més senzill, no cal el tipus Type
        // Type és per llista d'objectes custom
        // només caldria fer bloc= gson.fromJSon(json, Bloc.class)

        if (mBlocList == null) {
            mBlocList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new BlocAdapter(mBlocList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        // TODO: 14/01/20 interfície listener a BlocAdapter
        mAdapter.setOnItemClickListener(new BlocAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {

                try {
                    Intent intent = new Intent(BlocGson.this, ActivityDisplayBloc.class);
                    intent.putExtra("Bloc", mBlocList.get(position));

                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("test", "onItemClick: "+e.getMessage()+e.getCause());

                }
            }
        });


    }

    private void setInsertButton() {
        Button buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    EditText line1 = findViewById(R.id.edittext_line_1);
                    EditText line2 = findViewById(R.id.edittext_line_2);

                    insertItem(line1.getText().toString(), line2.getText().toString());
            }
        });
    }

    private void insertItem(String line1, String line2) {
        try {

            mBlocList.add(new Bloc(line1, line2,"hot"));
            mAdapter.notifyItemInserted(mBlocList.size());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("test", "insertItem: "+e.getCause()+e.getMessage());
        }
    }

}
