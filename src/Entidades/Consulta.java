package Entidades;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable,Comparable{
    private String especialidade,numBeneficiarioPaciente,motivo,numFuncionalFuncionario;
    private Date data,dataRegistro;

    public Consulta(String pEspecialidade, String pNumBeneficiarioPaciente, String pMotivo, String pNumFuncionalFuncionario, Date pData,Date pRegistro) {
        especialidade = pEspecialidade;
        numBeneficiarioPaciente = pNumBeneficiarioPaciente;
        motivo = pMotivo;
        numFuncionalFuncionario = pNumFuncionalFuncionario;
        data = pData;
        dataRegistro = new Date();
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setNumBeneficiarioPaciente(String numBeneficiarioPaciente) {
        this.numBeneficiarioPaciente = numBeneficiarioPaciente;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setNumFuncionalFuncionario(String numFuncionalFuncionario) {
        this.numFuncionalFuncionario = numFuncionalFuncionario;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getNumBeneficiarioPaciente() {
        return numBeneficiarioPaciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getNumFuncionalFuncionario() {
        return numFuncionalFuncionario;
    }

    public Date getData() {
        return data;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    @Override
    public int compareTo(Object o) {
        Consulta c = (Consulta)o;
        
        return this.getEspecialidade().compareTo(c.getEspecialidade());
    }
    
}
