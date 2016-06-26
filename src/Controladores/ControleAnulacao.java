package Controladores;

import Entidades.*;
import java.io.*;
import java.util.*;

public class ControleAnulacao {

    private ArrayList<Anulacao> objAnulacoes;

    public ControleAnulacao() {
        objAnulacoes = new ArrayList<>();
    }

    public void cadastrarAnulacao(Consulta pConsultaAnulada, Date pData, String pMotivo) {
        objAnulacoes.add(new Anulacao(pConsultaAnulada, pData, pMotivo));
    }

    public void salvarAnulacoes() throws Exception {
        FileOutputStream fileOs = new FileOutputStream("anulacoes.dat");
        ObjectOutputStream objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objAnulacoes);
        objOs.flush();
        objOs.close();
    }

    public void recuperarAnulacoes() throws Exception {
        File arquivo = new File("anulacoes.dat");

        if (arquivo.exists()) {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objAnulacoes = (ArrayList<Anulacao>) objIs.readObject();
            objIs.close();
        }
    }
}
