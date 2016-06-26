package Entidades;

import java.io.Serializable;
import java.util.*;

public class Paciente implements Serializable {

    private ArrayList<Prontuario> dadosMedicos;
    private String nome, numBeneficiario, sexo, endereco, telefone;
    private Date nascimento;

    //Constantes da classe paciente
    public static final String MASCULINO = "Masculino";
    public static final String FEMININO = "Feminino";

    public Paciente(String pNome, String pNumBeneficiaro, String pSexo, String pEndereco, String pTelefone, Date pNascimento) {
        nome = pNome;
        numBeneficiario = pNumBeneficiaro;
        sexo = pSexo;
        endereco = pEndereco;
        telefone = pTelefone;
        nascimento = pNascimento;
        dadosMedicos = new ArrayList<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumBeneficiario(String numBeneficiario) {
        this.numBeneficiario = numBeneficiario;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public ArrayList<Prontuario> getDadosMedicos() {
        return dadosMedicos;
    }

    public String getNome() {
        return nome;
    }

    public String getNumBeneficiario() {
        return numBeneficiario;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public Date getNascimento() {
        return nascimento;
    }

}
