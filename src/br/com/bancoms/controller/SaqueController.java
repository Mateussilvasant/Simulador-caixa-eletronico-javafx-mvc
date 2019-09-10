package br.com.bancoms.controller;

import br.com.bancoms.components.DoubleTeclado;
import br.com.bancoms.components.Teclado;
import br.com.bancoms.dto.MovimentoTO;
import br.com.bancoms.service.ContaService;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.view.MainView;
import br.com.bancoms.view.SaqueView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class SaqueController {

    private ClienteController clienteController;
    private TecladoController tecladoController;
    private SaqueView saqueView;

    public SaqueController(ClienteController clienteController){
        this.clienteController = clienteController;
        saqueView = new SaqueView(this);
        tecladoController = new TecladoController();
    }

    public void iniciarSaque(MainView mainView) {
        tecladoController.anexarTeclado(Teclado.Tipo.DOUBLE,null);
        saqueView.initSaqueView(mainView,tecladoController);
    }

    public EventHandler<ActionEvent> realizarSaqueAction() {
        return event ->{
            if(tecladoController.getTeclado().verificarValor()){
                double valor = ((DoubleTeclado) tecladoController.getTeclado()).getValor();

                realizarSaque(valor);

            } else{
                MainView.getAlert("Validação",
                        "Informe o valor", Alert.AlertType.INFORMATION).show();
            }
        };
    }

    private void realizarSaque(double valor) {
        try {
            MovimentoService movimentoService = MovimentoService.getInstance();
            MovimentoTO movimentoTO;

            if((movimentoTO = ContaService.getInstance().realizarSaque(clienteController.getContaSessao(),valor)) != null){
                movimentoService.registrarMovimento(movimentoTO);
                saqueRealizado();
            }

        } catch (Exception e) {
            saqueNaoRealizado(e.getMessage());
        }
    }

    public EventHandler<ActionEvent> retornarMenu() {
        return (event) -> clienteController.view.retornarMenuPrincipal(saqueView, clienteController.viewClient);
    }

    private void saqueNaoRealizado(String mensagem) {
        MainView.getAlert("Erro",
                mensagem, Alert.AlertType.INFORMATION).show();
        retornarMenu().handle(null);
    }

    private void saqueRealizado() {
        MainView.getAlert("Saque Informação",
                "Saque realizado com sucesso!", Alert.AlertType.INFORMATION).show();
        retornarMenu().handle(null);
    }

}
