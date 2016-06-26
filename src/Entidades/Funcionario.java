package Entidades;

import java.io.Serializable;

public class Funcionario implements Serializable{
    private String nome,numFuncional,funcao;
    
    //Constantes da classe Funcionario
    public static final String ATENDENTE = "Atendente";
    public static final String MEDICO = "Médico";
    public static final String ENFERMEIRO = "Enfermeiro";

    public Funcionario(String pNome, String pNumFuncional, String pFuncao) {
        nome = pNome;
        numFuncional = pNumFuncional;
        funcao = pFuncao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumFuncional(String numFuncional) {
        this.numFuncional = numFuncional;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
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
