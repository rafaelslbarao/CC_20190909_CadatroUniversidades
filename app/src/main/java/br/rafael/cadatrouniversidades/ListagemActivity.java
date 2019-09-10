package br.rafael.cadatrouniversidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListagemActivity extends AppCompatActivity {

    private FloatingActionButton btAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaComponentes();
        inicializaEventos();
    }

    private void inicializaComponentes()
    {
        btAdicionar = findViewById(R.id.btAdicionar);
    }

    private void inicializaEventos()
    {
        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaCadastro = new Intent(ListagemActivity.this, CadastroActivity.class);
                startActivity(telaCadastro);
            }
        });
    }
}
