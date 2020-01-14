package com.example.restaurarestat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDisplayBloc extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bloc);

        try {
            Intent intent = getIntent();
            Bloc bloc = intent.getParcelableExtra("Bloc");

            String horaInici = bloc.getHoraInici();
            String temperature = bloc.getTemperatura();
            String humitat = bloc.getHumitat();

            TextView textView = findViewById(R.id.txtHora);
            textView.setText(horaInici);

            TextView textView1 = findViewById(R.id.txtTemp);
            textView1.setText(temperature);

            TextView textView2 = findViewById(R.id.txtHumitat);
            textView2.setText(humitat);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("test", "onCreate: "+e.getMessage()+e.getCause());
        }

    }
}
