package Entidades;

import java.io.Serializable;

public class Funcionario implements Serializable {

    private String nome, numFuncional, funcao;

    //Constantes da classe Funcionario
    public static final String ATENDENTE = "Atendente";
    public static final String MEDICO = "Médico";
    public static final String ENFERMEIRO = "Enfermeiro";

    public Funcionario(String pNome, String pNumFuncional, String pFuncao) {
        nome = pNome;
        numFuncional = pNumFuncional;
        funcao = pFuncao;
    }

    public void setNome(String pNome) {
        nome = pNome;
    }

    public void setNumFuncional(String pNumFuncional) {
        numFuncional = pNumFuncional;
    }

    public void setFuncao(String pFuncao) {
        funcao = pFuncao;
    }

    public String getNome() {
        return nome;
    }

    public String getNumFuncional() {
        return numFuncional;
    }

    public String getFuncao() {
        return funcao;
    }
}
