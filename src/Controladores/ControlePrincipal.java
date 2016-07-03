package Controladores;

import Limites.TelaPrincipal;
import javax.swing.JOptionPane;

public class ControlePrincipal {

    private TelaPrincipal limite;
    private ControleFuncionario ctrFunc;
    private ControlePaciente ctrPac;
    private ControleConsulta ctrConsul;
    private ControleAnulacao ctrAnul;
    private ControleFalecimento ctrFalec;
    private ControleProntuario ctrPront;

    public ControlePrincipal() {

        try {
            ctrAnul = new ControleAnulacao();
            ctrFunc = new ControleFuncionario();
            ctrPac = new ControlePaciente();
            ctrConsul = new ControleConsulta(this);
            ctrFalec = new ControleFalecimento(this);
            ctrPront = new ControleProntuario(this);
        } catch (Exception exp) {
            limite.showMessage("Error: " + exp.getMessage());
        }

        recuperarDados();
        limite = new TelaPrincipal(this);
    }

    public ControleFuncionario getCtrFuncionario() {
        return ctrFunc;
    }

    public ControleFalecimento getCtrlFalecimento() {
        return ctrFalec;
    }

    public ControleProntuario getCtrlProntuario() {
        return ctrPront;
    }

    public ControlePaciente getCtrlPaciente() {
        return ctrPac;
    }

    public ControleConsulta getCtrlConsulta() {
        return ctrConsul;
    }

    public ControleAnulacao getCtrlAnulacao() {
        return ctrAnul;
    }

    public String getIdentificacaoFuncionario() {
        return limite.getNumFuncional();
    }

    // FUNCAO PARA ABRIR AS JANELAS RESPECTIVAS
    public void openWindow(int n) {
        switch (n) {
            case 1:
                System.out.println("Salvando");
                try {
                    salvarDados();
                    System.out.println("Salvando");
                } catch (Exception exc) {

                    System.out.println("erro");
                    System.out.println("O sistema falhou salvar os dados em arquivo!");
                    System.out.println(exc.getMessage());
                }
                break;
            case 2:
                break;
            case 3:
                ctrFunc.interfaceCadastroFuncionario();
                break;
            case 4:
                ctrPac.interfaceCadastroPaciente();
                break;
            case 5:
                ctrPac.interfaceAlteracaoDadosContato();
                break;
            case 6:
                ctrConsul.interfaceMarcarConsulta();
                break;
            case 7:
                ctrConsul.interfaceAgendaDaUnidadePorEspecialidade();
                break;
            case 8:
                ctrConsul.interfaceAnularConsulta();
                break;
            case 9:
                ctrFalec.interfaceCadastroFalecimento();
                break;
            case 10:
                ctrPront.interfaceCadastroProntuario();
                break;
            case 11:
                ctrConsul.interfaceHistoricoConsultas();
                break;
        }
    }

    //Metodo que salva todos os dados contidos no sistema em arquivo de texto
    public void salvarDados() throws Exception {
        ctrAnul.salvarAnulacoes();
        ctrConsul.salvarConsultas();
        ctrFalec.salvarFalecimentos();
        ctrFunc.salvarFuncionarios();
        ctrPac.salvarPacientes();
        ctrPront.salvarProntuarios();;
    }

    //Metodo que recupera todos os dados do sistema de arquivo de texto
    public void recuperarDados() {
        try {
            ctrAnul.recuperarAnulacoes();
            ctrConsul.recuperarConsultas();
            ctrFalec.recuperarFalecimentos();
            ctrFunc.recuperarFuncionarios();
            ctrPac.recuperarPacientes();
            ctrPront.recuperarProntuarios();

        } catch (Exception exc) {
            System.out.println("O sistema falhou na recuperação dos dados salvos em arquivo!");
            System.out.println(exc.getMessage());
        }
    }
}
