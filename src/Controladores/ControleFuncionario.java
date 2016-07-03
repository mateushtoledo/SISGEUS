package Controladores;

import java.util.*;
import java.io.*;
import Entidades.*;
import Limites.*;
import javax.swing.*;

public class ControleFuncionario {

    private ArrayList<Funcionario> objFuncionarios;
    private limiteFuncionario limite;

    public ControleFuncionario() throws Exception {
        objFuncionarios = new ArrayList<Funcionario>();

        recuperarFuncionarios();
    }
    
    public void interfaceCadastroFuncionario() {
        limite = new limiteFuncionario(this);
    }
    
    public void cadastrarFuncionario(String pNome, String pNumFuncional, String pFuncao) {
        objFuncionarios.add(new Funcionario(pNome, pNumFuncional, pFuncao));
    }

    public void cadastrarMedico(String pNome, String pNumFuncional, String pFuncao, String pEspecializacao) {
        objFuncionarios.add(new Medico(pNome, pNumFuncional, pFuncao, pEspecializacao));
    }

    public ArrayList<Funcionario> obterAtendentes() {
        ArrayList<Funcionario> objAtendentes = new ArrayList<Funcionario>();

        for (Funcionario func : objFuncionarios) {
            if (func.getFuncao().equals(Funcionario.ATENDENTE)) {
                objAtendentes.add(func);
            }
        }
        return objAtendentes;
    }

    public ArrayList<Medico> obterMedicos() {
        ArrayList<Medico> objMedicos = new ArrayList<Medico>();

        for (Funcionario func : objFuncionarios) {
            if (func instanceof Medico) {
                objMedicos.add((Medico) func);
            }
        }
        return objMedicos;
    }

    public String loginFuncionario(String pNome, String pNumFuncional) {
        for (Funcionario func : objFuncionarios) {
            if (func.getFuncao().equals(Funcionario.ATENDENTE)) {
                if (func.getNome().equals(pNome) && func.getNumFuncional().equals(pNumFuncional)) {
                    return func.getFuncao();
                }

            } else if (func.getFuncao().equals(Funcionario.MEDICO)) {
                if (func.getNome().equals(pNome) && func.getNumFuncional().equals(pNumFuncional)) {
                    return func.getFuncao();
                }
            }
        }
        //if (pNome.equals("Jean") && pNumFuncional.equals("35138")) {
        if (pNome.equals("Jean Carlos de Oliveira") && pNumFuncional.equals("35138")) {
        
            return "Responsável";
        }
        return "";
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

    public void salvarFuncionarios() throws Exception {
        FileOutputStream fileOs = new FileOutputStream("data/funcionarios.dat");
        ObjectOutputStream objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objFuncionarios);
        objOs.flush();
        objOs.close();
    }

    public void recuperarFuncionarios() throws Exception {
        File arquivo = new File("data/funcionarios.dat");

        if (arquivo.exists()) {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objFuncionarios = (ArrayList<Funcionario>) objIs.readObject();
            objIs.close();
        }
    }
}
