package br.com.bancoms.controller;

import br.com.bancoms.dto.MovimentoBuscaDTO;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.util.DateUtil;
import br.com.bancoms.view.ExtratoView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class ExtratoController {

    public ClienteController clienteController;
    private ExtratoView extratoView;

    public ExtratoController(ClienteController clienteController) {
        this.clienteController = clienteController;
        extratoView = new ExtratoView(this);
    }

    public void iniciarExtratos() {
        extratoView.iniciarView(clienteController);
    }


    public EventHandler<ActionEvent> cancelarAction() {
        return (event) -> clienteController.retornarMenuAction(extratoView);
    }


    public EventHandler<ActionEvent> consultarExtratoAction() {
        return (event) -> {

            ArrayList<Optional<Movimento>> lista = consultarExtratos(new MovimentoBuscaDTO
                    (
                            clienteController.getContaSessao().getNumero(),
                            0,
                            "",
                            ""
                    ));

            if (!lista.isEmpty()) {
                extratoView.limparMovimentosView();
                adicionarMovimentosView(lista);
            }


        };
    }

    private void adicionarMovimentosView(ArrayList<Optional<Movimento>> lista) {
        for (int index = 1; index < lista.size(); index++) {
            Optional<Movimento> movimento = lista.get(index);
            if (movimento.isPresent()) {
                extratoView.adicionarMovimentoItem(movimento.get(), index);
            }
        }
    }

    private ArrayList<Optional<Movimento>> consultarExtratos(MovimentoBuscaDTO busca) {

        busca.setDataFim(DateUtil.getCurrentDateString());
        Calendar dateInicio = DateUtil.getCurrentCalendar();
        dateInicio.add(Calendar.DAY_OF_MONTH, -extratoView.getPeriodo());
        busca.setDataInicio(DateUtil.parseDefaultUSA(dateInicio));

        if (extratoView.getTipoAtual().equals("Todos")) {
            return MovimentoService.getInstance().listarTodosMovimentos(busca);
        } else {
            busca.setTipoMovimento(extratoView.getTipoAtualValor());
            return MovimentoService.getInstance().listarMovimentosPorTipo(busca);
        }

    }
}
