package com.example.restaurarestat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /*

    When there are runtime configuration changes in your Android phone,
    like changing the screen orientation or the device’s language,
    your whole app process will be destroyed and recreated from scratch and together with it,
    all member variables will be reset.

    The system already takes care of default views like the text in an EditText field
    or the scrolling position of a RecyclerView or ListView.

    But we have to restore the variables of our activity ourselves and we do this by overriding
    onSaveInstanceState and passing the values to the outState Bundle. After the Activity has been recreated,
    there are 2 places where we can get our values back: onCreate or onRestoreInstanceState,
    which both get passed the savedInstanceState Bundle.

     */

    private TextView mTextViewCount;
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCount = findViewById(R.id.text_view_count);

        //setTitle("RestaurarEstat");
        Button buttonDecrement = findViewById(R.id.button_decrement);
        Button buttonIncrement = findViewById(R.id.button_increment);

        buttonDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });


        //fab
        // TODO: 12/18/19


        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        Integer record = sharedPreferences.getInt("record", 0);

        //opcional, carregar el record al txtViewCount
        //record=mCount;

        Toast.makeText(this, "Puntuació és:" + record, Toast.LENGTH_LONG).show();



        //si s'ha girat... o canviat idioma...
        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt("count");

            // TODO: 19/12/19 comentar linia 91 per veure que en girar-ho es perd l estat
            // els textview es perden...
            // aquí es recupera dins onCreate, a l'altre activity CountDownTimer, sobreescrivint el mètode
            // onRestoreInstanceState

            mTextViewCount.setText(String.valueOf(mCount));
            Toast.makeText(this, "entra a restaurar", Toast.LENGTH_SHORT).show();
        }
    }

    private void decrement() {
        mCount--;
        mTextViewCount.setText(String.valueOf(mCount));
    }

    private void increment() {
        mCount++;
        mTextViewCount.setText(String.valueOf(mCount));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("count", mCount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(new Intent(this, CountDownTimerActivity.class));

            return true;
        }
        else if (id == R.id.bloc) {

                startActivity(new Intent(this, BlocGson.class));

                return true;

            }

        return super.onOptionsItemSelected(item);
    }

    public void saveSharedPref(View view) {

        //guarda el contatge...
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("record", mCount);
        editor.apply();


        Gson gson = new Gson();

    }
}
