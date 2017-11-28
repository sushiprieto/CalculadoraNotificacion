package com.trabajo.carlos.a015_boletin2ej5;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edt1, edt2;
    private Button btnOk;
    private TextView txvResultado;
    private RadioButton rdbMas, rdbMenos, rdbPor, rdbEntre;

    int num1, num2;

    private String savedText1;

    private static final String SAVED_TEXT_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        btnOk = (Button) findViewById(R.id.btnOk);
        txvResultado = (TextView) findViewById(R.id.txvResultado);
        rdbMas = (RadioButton) findViewById(R.id.rdbMas);
        rdbMenos = (RadioButton) findViewById(R.id.rdbMenos);
        rdbPor = (RadioButton) findViewById(R.id.rdbPor);
        rdbEntre = (RadioButton) findViewById(R.id.rdbEntre);

        btnOk.setOnClickListener(this);
        rdbMas.setOnClickListener(this);
        rdbMenos.setOnClickListener(this);
        rdbPor.setOnClickListener(this);
        rdbEntre.setOnClickListener(this);

        savedText1 = edt1.getText().toString();
        edt1.setText(SAVED_TEXT_KEY);



        if(savedInstanceState !=null){

            savedText1 = savedInstanceState.getString(SAVED_TEXT_KEY);

        }

    }

    @Override
    public void onClick(View view) {


        double resultado = 0;

        num1 = Integer.parseInt(edt1.getText().toString());
        num2 = Integer.parseInt(edt2.getText().toString());

        switch (view.getId()) {

            case R.id.rdbMas:
                resultado = num1 + num2;

                txvResultado.setText("" + resultado);

                break;

            case R.id.rdbMenos:
                resultado = num1 - num2;

                txvResultado.setText("" + resultado);

                break;

            case R.id.rdbPor:
                resultado = num1 * num2;

                txvResultado.setText("" + resultado);

                break;

            case R.id.rdbEntre:

                if (num2 == 0)
                    Toast.makeText(MainActivity.this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
                else
                    resultado = num1 / num2;

                txvResultado.setText("" + resultado);

                break;

        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("Calculadora");
        builder.setContentText("Tu valor se ha guardado");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(SAVED_TEXT_KEY, edt1.getText().toString());
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        edt1.setText(savedInstanceState.getString(SAVED_TEXT_KEY));
    }
}
