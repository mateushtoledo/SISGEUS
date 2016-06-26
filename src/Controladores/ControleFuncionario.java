package Controladores;

import java.util.*;
import java.io.*;
import Entidades.*;
import Limites.LimiteCadastroFuncionario;

public class ControleFuncionario {

    private ArrayList<Funcionario> objFuncionarios;

    public ControleFuncionario() {
        objFuncionarios = new ArrayList<>();
    }

    public void cadastrarFuncionario(String pNome, String pNumFuncional, String pFuncao) {
        objFuncionarios.add(new Funcionario(pNome, pNumFuncional, pFuncao));
    }

    public void cadastrarMedico(String pNome, String pNumFuncional, String pFuncao, String pEspecializacao) {
        objFuncionarios.add(new Medico(pNome, pNumFuncional, pFuncao, pEspecializacao));
    }

    public Medico marcarConsulta(String pEspecialidade, String pNumBeneficiarioPaciente, String pMotivo, String pNumFuncionalFuncionario, Date pData, Date pRegistro) {
        for (Funcionario func : objFuncionarios) {
            if (func instanceof Medico) {
                Medico doctor = (Medico) func;
                if (doctor.getEspecializacao().equals(pEspecialidade)) {
                    boolean resultado = doctor.marcarConsulta(pEspecialidade, pNumBeneficiarioPaciente, pMotivo, pNumFuncionalFuncionario, pData, pRegistro);

                    if (resultado) {
                        return doctor;
                    }
                }
            }
        }

        return null;
    }

    public void anularConsultaDaAgendaDoMedico(Consulta pConsulta) {
        for (Funcionario func : objFuncionarios) {
            if (func instanceof Medico) {
                Medico doctor = (Medico) func;

                boolean resultado = doctor.removerConsulta(pConsulta);
            }
        }
    }

    public void interfaceCadastroFuncionario() {
        new LimiteCadastroFuncionario(this);
    }

    public ArrayList<Funcionario> obterAtendentes() {
        ArrayList<Funcionario> objAtendentes = new ArrayList<>();

        for (Funcionario func : objFuncionarios) {
            if (func.getFuncao().equals(Funcionario.ATENDENTE)) {
                objAtendentes.add(func);
            }
        }

        return objAtendentes;
    }

    public ArrayList<Medico> obterMedicos() {
        ArrayList<Medico> objMedicos = new ArrayList<>();

        for (Funcionario func : objFuncionarios) {
            if (func instanceof Medico) {
                objMedicos.add((Medico) func);
            }
        }

        return objMedicos;
    }

    public boolean loginAtendente(String pNome, String pNumFuncional) {
        for (Funcionario func : objFuncionarios) {
            if (func.getFuncao().equals(Funcionario.ATENDENTE)) {
                if (func.getNome().equals(pNome) && func.getNumFuncional().equals(pNumFuncional)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean loginMedico(String pNome, String pNumFuncional) {
        for (Funcionario func : objFuncionarios) {
            if (func.getFuncao().equals(Funcionario.MEDICO)) {
                if (func.getNome().equals(pNome) && func.getNumFuncional().equals(pNumFuncional)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean loginResponsavelPorPessoal(String pNome, String pNumFuncional) {
        if (pNome.equals("Jean Carlos de Oliveira") && pNumFuncional.equals("35138")) {
            return true;
        } else {
            return false;
        }
    }

    public void salvarFuncionarios() throws Exception {
        FileOutputStream fileOs = new FileOutputStream("funcionarios.dat");
        ObjectOutputStream objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objFuncionarios);
        objOs.flush();
        objOs.close();
    }

    public void recuperarFuncionarios() throws Exception {
        File arquivo = new File("funcionarios.dat");

        if (arquivo.exists()) {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objFuncionarios = (ArrayList<Funcionario>) objIs.readObject();
            objIs.close();
        }
    }
}
