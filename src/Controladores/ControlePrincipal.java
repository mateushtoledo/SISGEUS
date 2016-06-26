package Controladores;

import Limites.*;

public class ControlePrincipal {
    private LimitePrincipal objLimitePrincipal;
    private ControleAnulacao objCtrlAnulacao;
    private ControleConsulta objCtrlConsulta;
    private ControleFalecimento objCtrlFalecimento;
    private ControleFuncionario objCtrlFuncionario;
    private ControlePaciente objCtrlPaciente;
    private ControleProntuario objCtrlProntuario;

    public ControlePrincipal() {
        objCtrlAnulacao = new ControleAnulacao();
        objCtrlConsulta = new ControleConsulta(this);
        objCtrlFalecimento = new ControleFalecimento(this);
        objCtrlFuncionario = new ControleFuncionario();
        objCtrlPaciente = new ControlePaciente();
        objCtrlProntuario = new ControleProntuario(this);
        recuperarDados();
        objLimitePrincipal = new LimitePrincipal(this);
    }

    public ControleAnulacao getCtrlAnulacao() {
        return objCtrlAnulacao;
    }

    public ControleConsulta getCtrlConsulta() {
        return objCtrlConsulta;
    }

    public ControleFalecimento getCtrlFalecimento() {
        return objCtrlFalecimento;
    }

    public ControleFuncionario getCtrlFuncionario() {
        return objCtrlFuncionario;
    }

    public ControlePaciente getCtrlPaciente() {
        return objCtrlPaciente;
    }

    public ControleProntuario getCtrlProntuario() {
        return objCtrlProntuario;
    }
    
    public String getIdentificacaoFuncionario()
    {
        return objLimitePrincipal.getNumFuncional();
    }
    
    public void interfacePrincipalAtendente()
    {
        new LimiteInterfaceAtendente(this);
    }
    
    public void salvarDados() throws Exception
    {
        objCtrlAnulacao.salvarAnulacoes();
        objCtrlConsulta.salvarConsultas();
        objCtrlFalecimento.salvarFalecimentos();
        objCtrlFuncionario.salvarFuncionarios();
        objCtrlPaciente.salvarPacientes();
        objCtrlProntuario.salvarProntuarios();
    }
    
    public void recuperarDados()
    {
        try{
            objCtrlAnulacao.recuperarAnulacoes();
            objCtrlConsulta.recuperarConsultas();
            objCtrlFalecimento.recuperarFalecimentos();
            objCtrlFuncionario.recuperarFuncionarios();
            objCtrlPaciente.recuperarPacientes();
            objCtrlProntuario.recuperarProntuarios();
        }catch(Exception exc){
            System.out.println("O sistema falhou na recuperação dos dados salvos em arquivo!");
            System.out.println(exc.getMessage());
        }
    }
}
