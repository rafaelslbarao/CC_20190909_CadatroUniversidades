package br.rafael.cadatrouniversidades.Model;

import java.io.Serializable;

public class Universidade implements Serializable {

    public static final String EXTRA_NAME = "UNIVERSIDADE";

    private Long codigo;
    private String nome;
    private String cidade;
    private String estado;

    public Universidade() {
    }

    public Universidade(Long codigo, String nome, String cidade, String estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
