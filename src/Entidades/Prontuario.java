package Entidades;

import java.io.Serializable;
import java.util.Date;

public class Prontuario implements Serializable {

    private String queixas, resumoExame, resumoDiagnostico, tratamentos, numBeneficiario;
    private Date dataRegistro;

    public Prontuario(String pNumBeneficiario, String pQueuixas, String pResumoExame, String pResumoDiagnostico, String pTratamentos, Date pData) {
        numBeneficiario = pNumBeneficiario;
        queixas = pQueuixas;
        resumoExame = pResumoExame;
        resumoDiagnostico = pResumoDiagnostico;
        tratamentos = pTratamentos;
        dataRegistro = pData;
    }

    public void setQueixas(String queixas) {
        this.queixas = queixas;
    }

    public void setResumoExame(String resumoExame) {
        this.resumoExame = resumoExame;
    }

    public void setResumoDiagnostico(String resumoDiagnostico) {
        this.resumoDiagnostico = resumoDiagnostico;
    }

    public void setTratamentos(String tratamentos) {
        this.tratamentos = tratamentos;
    }

    public void setNumBeneficiario(String numBeneficiario) {
        this.numBeneficiario = numBeneficiario;
    }

    public String getQueixas() {
        return queixas;
    }

    public String getResumoExame() {
        return resumoExame;
    }

    public String getResumoDiagnostico() {
        return resumoDiagnostico;
    }

    public String getTratamentos() {
        return tratamentos;
    }

    public String getNumBeneficiario() {
        return numBeneficiario;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }
}
