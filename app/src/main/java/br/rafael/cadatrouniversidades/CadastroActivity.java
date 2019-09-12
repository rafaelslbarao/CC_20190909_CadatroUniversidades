package br.rafael.cadatrouniversidades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.io.Serializable;

import br.rafael.cadatrouniversidades.Model.Universidade;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatEditText etCodigo;
    private AppCompatEditText etNome;
    private AppCompatEditText etCidade;
    private AppCompatSpinner spEstado;
    private AppCompatButton btSalvar;
    //
    private Universidade universidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializaComponenetes();
        inicializaEventos();
        carregaInformacoesSpinner();
        carregaInformacoesPassadasPorParametro();
    }

    private void inicializaComponenetes() {
        etCodigo = findViewById(R.id.etCodigo);
        etNome = findViewById(R.id.etNome);
        etCidade = findViewById(R.id.etCidade);
        spEstado = findViewById(R.id.spEstado);
        btSalvar = findViewById(R.id.btSalvar);
    }

    private void inicializaEventos() {
        btSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSalvar:
                confirmaTela();
                break;
        }

    }

    private void carregaInformacoesSpinner() {
        ArrayAdapter<CharSequence> adapterEstados =
                ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapterEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEstado.setAdapter(adapterEstados);
    }

    private void carregaInformacoesPassadasPorParametro()
    {
        Serializable objetoPassado = getIntent().getSerializableExtra(Universidade.EXTRA_NAME);
        if(objetoPassado != null)
        {
            universidade = (Universidade) objetoPassado;
            carregaInformacoesParaAtualizacao();
        }
    }

    private void carregaInformacoesParaAtualizacao()
    {
        etCodigo.setEnabled(false);
        etCodigo.setText(universidade.getCodigo().toString());
        etNome.setText(universidade.getNome());
        etCidade.setText(universidade.getCidade());

        for(int i = 0; i < spEstado.getCount(); i++)
        {
            if(spEstado.getItemAtPosition(i).equals(universidade.getEstado())) {
                spEstado.setSelection(i);
                break;
            }
        }

        etCodigo.setText(universidade.getCodigo().toString());
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
        //
        Universidade minhaUniversidade = new Universidade();
        minhaUniversidade.setCodigo(Long.parseLong(etCodigo.getText().toString()));
        minhaUniversidade.setNome(etNome.getText().toString());
        minhaUniversidade.setCidade(etCidade.getText().toString());
        minhaUniversidade.setEstado(spEstado.getSelectedItem().toString());
        //
        Intent data = new Intent();
        data.putExtra(Universidade.EXTRA_NAME, minhaUniversidade);
        //
        setResult(RESULT_OK, data);
        return true;
    }
}
