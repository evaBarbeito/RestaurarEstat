package com.example.restaurarestat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloc_gson);

        //https://www.youtube.com/watch?v=jcliHGR3CHo&list=LLSrVQD6aijhcCpv8EeUZXBQ&index=12&t=0s

        loadData();
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
        String json = gson.toJson(mBlocList);
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
