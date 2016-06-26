package Controladores;

import java.util.*;
import java.io.*;
import Entidades.*;
import Limites.LimiteCadastroProntuario;

public class ControleProntuario {

    private ArrayList<Prontuario> objProntuarios;
    private ControlePrincipal objControle;

    public ControleProntuario(ControlePrincipal pControlador) {
        objControle = pControlador;
        objProntuarios = new ArrayList<>();
    }

    public void cadastrarProntuario(String pNumBeneficiario, String pQueixas, String pResumoExame, String pResumoDiagnostico, String pTratamentos, Date pData) {
        objProntuarios.add(new Prontuario(pNumBeneficiario, pQueixas, pResumoExame, pResumoDiagnostico, pTratamentos, pData));
    }

    public void interfaceCadastroProntuario() {
        new LimiteCadastroProntuario(objControle);
    }

    public void salvarProntuarios() throws Exception {
        FileOutputStream fileOs = new FileOutputStream("prontuarios.dat");
        ObjectOutputStream objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objProntuarios);
        objOs.flush();
        objOs.close();
    }

    public void recuperarProntuarios() throws Exception {
        File arquivo = new File("prontuarios.dat");

        if (arquivo.exists()) {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objProntuarios = (ArrayList<Prontuario>) objIs.readObject();
            objIs.close();
        }
    }
}
