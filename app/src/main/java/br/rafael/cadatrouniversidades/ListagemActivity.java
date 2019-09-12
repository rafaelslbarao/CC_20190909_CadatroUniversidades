package br.rafael.cadatrouniversidades;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.rafael.cadatrouniversidades.Model.Universidade;

import static br.rafael.cadatrouniversidades.R.id.tvCodigo;
import static br.rafael.cadatrouniversidades.R.id.tvNome;

public class ListagemActivity extends AppCompatActivity {

    private static final int REQUEST_CADASTRO_UNIVERSIDADE = 1;
    private static final int REQUEST_ATUALIZACAO_UNIVERSIDADE = 2;
    //
    private FloatingActionButton btAdicionar;
    private RecyclerView rvInformacoes;
    //
    private ArrayList<Universidade> listaUniversidades = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaComponentes();
        inicializaEventos();
        inicializaAdapter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_CADASTRO_UNIVERSIDADE:
                if(resultCode == RESULT_OK)
                {
                    Universidade universidadeRetornada = (Universidade) data.getSerializableExtra(Universidade.EXTRA_NAME);
                    listaUniversidades.add(0, universidadeRetornada);
                    rvInformacoes.getAdapter().notifyItemInserted(0);
                }
                break;
            case REQUEST_ATUALIZACAO_UNIVERSIDADE:
                if(resultCode == RESULT_OK)
                {
                    final Universidade universidadeRetornada = (Universidade) data.getSerializableExtra(Universidade.EXTRA_NAME);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for(int i =0; i < listaUniversidades.size(); i++) {
                                if(listaUniversidades.get(i).getCodigo().equals(universidadeRetornada.getCodigo()))
                                {
                                    listaUniversidades.set(i, universidadeRetornada);
                                    rvInformacoes.getAdapter().notifyItemChanged(i);
                                    break;
                                }
                            }
                        }
                    }, 1000);

                }
                break;
        }
    }

    private void inicializaComponentes() {
        btAdicionar = findViewById(R.id.btAdicionar);
        rvInformacoes = findViewById(R.id.rvInformacoes);
    }

    private void inicializaEventos() {
        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaCadastro = new Intent(ListagemActivity.this, CadastroActivity.class);
                startActivityForResult(telaCadastro, REQUEST_CADASTRO_UNIVERSIDADE);
            }
        });
    }

    private void inicializaAdapter()
    {
        MeuAdapter meuAdapter = new MeuAdapter();
        rvInformacoes.setAdapter(meuAdapter);
    }

    private class MeuHolder extends RecyclerView.ViewHolder
    {
        private AppCompatTextView tvCodigo;
        private AppCompatTextView tvNome;

        public MeuHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvNome = itemView.findViewById(R.id.tvNome);
        }
    }

    private class MeuAdapter extends RecyclerView.Adapter<MeuHolder>
    {
        @NonNull
        @Override
        public MeuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MeuHolder meuHolder
                    = new MeuHolder(getLayoutInflater().inflate(R.layout.item_cadastro_universidade, parent, false));
            return meuHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MeuHolder holder,final int position) {
            final Universidade universidade = listaUniversidades.get(position);
            holder.tvCodigo.setText(universidade.getCodigo().toString());
            holder.tvNome.setText(universidade.getNome().toString());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listaUniversidades.remove(position);
                    notifyItemRemoved(position);
                    return true;
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent telaCadastro = new Intent(ListagemActivity.this, CadastroActivity.class);
                    telaCadastro.putExtra(Universidade.EXTRA_NAME, universidade);
                    startActivityForResult(telaCadastro, REQUEST_ATUALIZACAO_UNIVERSIDADE);
                }
            });
        }

        @Override
        public int getItemCount() {
            return listaUniversidades.size();
        }
    }
}
