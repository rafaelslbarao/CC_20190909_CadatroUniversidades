package br.rafael.cadatrouniversidades;

import android.content.Context;
import android.widget.Toast;

public class Mensageira {

    public static void mostraMensagem(Context context, int idMensagem)
    {
        Toast.makeText(context, idMensagem, Toast.LENGTH_LONG).show();
    }
}
