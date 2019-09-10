package br.rafael.cadatrouniversidades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatEditText etCodigo;
    private AppCompatEditText etNome;
    private AppCompatEditText etCidade;
    private AppCompatSpinner spEstado;
    private AppCompatButton btSalvar;
    private AppCompatButton btExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializaComponenetes();
        inicializaEventos();
        carregaInformacoesSpinner();
    }

    private void inicializaComponenetes() {
        etCodigo = findViewById(R.id.etCodigo);
        etNome = findViewById(R.id.etNome);
        etCidade = findViewById(R.id.etCidade);
        spEstado = findViewById(R.id.spEstado);
        btSalvar = findViewById(R.id.btSalvar);
        btExcluir = findViewById(R.id.btExcluir);
    }

    private void inicializaEventos() {
        btSalvar.setOnClickListener(this);
        btExcluir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSalvar:
                confirmaTela();
                break;
            case R.id.btExcluir:
                break;
        }

    }

    private void carregaInformacoesSpinner() {
        ArrayAdapter<CharSequence> adapterEstados =
                ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapterEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEstado.setAdapter(adapterEstados);
    }

    private void confirmaTela() {
        if (!validaTela())
            return;


        if (salvaInformacoes()) {
            this.finish();
        }
    }

    private boolean validaTela() {
        if (etCodigo.getText().toString().trim().isEmpty()) {
            Mensageira.mostraMensagem(this, R.string.InformeCodigo);
            etCodigo.requestFocus();
            return false;
        }

        if (etNome.getText().toString().trim().isEmpty()) {
            Mensageira.mostraMensagem(this, R.string.InformeNome);
            etNome.requestFocus();
            return false;
        }

        if (etCidade.getText().toString().trim().isEmpty()) {
            Mensageira.mostraMensagem(this, R.string.InformeCidade);
            etCidade.requestFocus();
            return false;
        }

        if (spEstado.getSelectedItemPosition() == 0) {
            Mensageira.mostraMensagem(this, R.string.InformeEstado);
            return false;
        }

        return true;
    }

    private boolean salvaInformacoes() {
        return true;
    }
}
