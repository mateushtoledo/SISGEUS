package Entidades;

import java.io.Serializable;
import java.util.*;

public class Medico extends Funcionario implements Serializable {

    private String especializacao;
    private ArrayList<Consulta> consultas;

    public Medico(String pNome, String pNumFuncional, String pFuncao, String pEspecializacao) {
        super(pNome, pNumFuncional, pFuncao);
        especializacao = pEspecializacao;
        consultas = new ArrayList<>();
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public boolean marcarConsulta(String pEspecialidade, String pNumBeneficiarioPaciente, String pMotivo, String pNumFuncionalFuncionario, Date pData, Date pRegistro) {
        for (Consulta c : consultas) {
            if (c.getData().compareTo(pData) == 0) {
                return false;
            }
        }

        consultas.add(new Consulta(pEspecialidade, pNumBeneficiarioPaciente, pMotivo, pNumFuncionalFuncionario, pData, pRegistro));
        return true;
    }

    public boolean removerConsulta(Consulta pConsulta) {
        if (!consultas.isEmpty()) {
            for (int i = 0; i < consultas.size(); i++) {
                Consulta c = consultas.get(i);

                if ((c.getData().compareTo(pConsulta.getData()) == 0) && (c.getEspecialidade().equalsIgnoreCase(pConsulta.getEspecialidade()))) {
                    consultas.remove(i);
                    return true;
                }
            }
        }

        return false;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

}
