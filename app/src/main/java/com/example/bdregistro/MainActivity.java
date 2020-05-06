package com.example.bdregistro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.bdregistro.R.id.edtcedula;


public class MainActivity extends AppCompatActivity {

    Spinner estr, niv;
    EditText ced, nom, sala;
    Button guardar, listado, eliminar, actualizar, buscar;
    Registro reg;
    RegistroController c;
    private RegistroCursorAdapter var;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        ced = findViewById(edtcedula);
        nom = findViewById(R.id.edtnombre);
        estr = findViewById(R.id.spinnerestrato);
        sala = findViewById(R.id.edtsalario);
        niv = findViewById(R.id.spinnerniveleducativo);
        guardar = findViewById(R.id.btnguardar);
        listado = findViewById(R.id.btnlistado);
        eliminar = findViewById(R.id.btneliminar);
        actualizar = findViewById(R.id.btnactualizar);
        buscar = findViewById(R.id.btnbuscar);

        c = new RegistroController(getApplicationContext());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg = new Registro(ced.getText().toString(), nom.getText().toString(),
                        estr.getItemAtPosition(estr.getFirstVisiblePosition()).toString(),
                        sala.getText().toString(),
                        niv.getItemAtPosition(niv.getFirstVisiblePosition()).toString());
                Toast.makeText(getApplicationContext(), reg.cedula, Toast.LENGTH_SHORT).show();
                if (reg.cedula.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Error. Campo cedula vacio", Toast.LENGTH_SHORT).show();
                }else{
                    if(!c.buscarRegistro(reg.cedula)) {
                        Log.d("Buscar", "No encontrado");
                        long id = c.agregarRegistro(reg);
                        Toast.makeText(getApplicationContext(), "Registro guardado", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.d("Buscar", "encontrado");
                        Toast.makeText(getApplicationContext(),"El usuario ya se encuentra registrado",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg = new Registro(ced.getText().toString(), nom.getText().toString(),
                        estr.getItemAtPosition(estr.getFirstVisiblePosition()).toString(),
                        sala.getText().toString(),
                        niv.getItemAtPosition(niv.getFirstVisiblePosition()).toString());
        Toast.makeText(getApplicationContext(), reg.cedula, Toast.LENGTH_SHORT).show();
        if (c.buscarRegistro(reg.cedula)) {
            Log.d("Buscar", "Encontrado");
            c.eliminarRegistro(reg.cedula);
            Toast.makeText(getApplicationContext(), "Registro eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("Buscar", "No encontrado");
            Toast.makeText(getApplicationContext(), "El registro no fue encontrado", Toast.LENGTH_SHORT).show();
        }

            }
        });

        listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListadoActivity.class);
                startActivity(i);
            }
        });


        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg = new Registro(ced.getText().toString(), nom.getText().toString(),
                        estr.getItemAtPosition(estr.getFirstVisiblePosition()).toString(),
                        sala.getText().toString(),
                        niv.getItemAtPosition(niv.getFirstVisiblePosition()).toString());
                if(c.buscarRegistro(reg.cedula)){
                    Log.e("Buscar", "Entro");
                    long id = c.actualizarRegistro(reg);
                    Toast.makeText(getApplicationContext(), "Registro actualizado", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "El registro no fue encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg = new Registro(ced.getText().toString(), nom.getText().toString(),
                        estr.getItemAtPosition(estr.getFirstVisiblePosition()).toString(),
                        sala.getText().toString(),
                        niv.getItemAtPosition(niv.getFirstVisiblePosition()).toString());
                if(c.buscarRegistro(reg.cedula)){
                    Log.e("Buscar", "Entro");
                    Toast.makeText(getApplicationContext(), "Registro encontrado", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, ListadoBuscar.class);
                    i.putExtra("cedula", reg.cedula);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "El registro no fue encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
