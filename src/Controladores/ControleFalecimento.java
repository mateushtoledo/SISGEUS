package Controladores;

import java.util.*;
import java.io.*;
import Limites.LimiteCadastroFalecimento;
import Entidades.Falecimento;

public class ControleFalecimento {

    private ControlePrincipal objControlador;
    private ArrayList<Falecimento> objFalecimentos;

    public ControleFalecimento(ControlePrincipal pCtrl) {
        objControlador = pCtrl;
        objFalecimentos = new ArrayList<>();
    }

    //Metodo que cadastra falecimento quando e conhecida sua causa
    public void cadastrarFalecimento(int pDia, int pMes, int pAno, String pCausa) {
        objFalecimentos.add(new Falecimento(new Date(pAno, pMes, pDia), pCausa));
    }

    //Metodo que cadastra falecimento quando nao e conhecida sua causa
    public void cadastrarFalecimento(int pDia, int pMes, int pAno) {
        objFalecimentos.add(new Falecimento(new Date(pAno, pMes, pDia)));
    }

    //Metodo que exibe interface para realizar ocadastro de falecimento
    public void interfaceCadastroFalecimento() {
        new LimiteCadastroFalecimento(objControlador);
    }

    //Metodo que salva os dados de falecimento em arquivo de texto
    public void salvarFalecimentos() throws Exception {
        FileOutputStream fileOs = new FileOutputStream("data/falecimentos.dat");
        ObjectOutputStream objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objFalecimentos);
        objOs.flush();
        objOs.close();
    }

    //Metodo que recupera os dados de falecimento de arquivo de texto
    public void recuperarFalecimentos() throws Exception {
        File arquivo = new File("data/falecimentos.dat");

        if (arquivo.exists()) {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objFalecimentos = (ArrayList<Falecimento>) objIs.readObject();
            objIs.close();
        }
    }
}
