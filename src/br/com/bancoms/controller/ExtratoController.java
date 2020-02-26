package br.com.bancoms.controller;

import br.com.bancoms.dto.MovimentoBuscaDTO;
import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.service.ClienteService;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.util.DateUtil;
import br.com.bancoms.view.ExtratoView;
import br.com.bancoms.view.MovimentoItemView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class ExtratoController {

    public ClienteController clienteController;
    private ExtratoView extratoView;
    private MovimentoItemView movimentoItemViewAtual;

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

    public EventHandler<ActionEvent> detalhesMovimentoAction(MovimentoItemView itemView) {
        return (event) -> {

            if (movimentoItemViewAtual != null) {
                movimentoItemViewAtual.removerDetalhesView();
            }

            Optional<Cliente> clienteOrigem = ClienteService.getInstance().consultarCliente(itemView.getMovimento().getNumeroContaDestino());

            clienteOrigem.ifPresent(cliente -> itemView.detalhesView(cliente, clienteController.clienteSessao));

            movimentoItemViewAtual = itemView;

        };
    }

    public EventHandler<ActionEvent> consultarExtratoAction() {
        return (event) -> {

            extratoView.limparMovimentosView();

            ArrayList<Optional<Movimento>> lista = consultarExtratos(new MovimentoBuscaDTO
                    (
                            clienteController.getContaSessao().getNumero(),
                            0,
                            "",
                            ""
                    ));

            System.out.println(lista.toString());

            if (!lista.isEmpty()) {
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

    /* Retorna uma lista  de movimentos de acordo com opção selecionada na view*/
    private ArrayList<Optional<Movimento>> consultarExtratos(MovimentoBuscaDTO busca) {

        setMovimentoDataPeriodo(busca);

        if (extratoView.getTipoAtual().equals("Todos")) {
            return MovimentoService.getInstance().listarTodosMovimentos(busca);
        } else {
            busca.setTipoMovimento(extratoView.getTipoAtualValor());

            System.out.println(busca.toString());

            return MovimentoService.getInstance().listarMovimentosPorTipo(busca);
        }

    }

    private void setMovimentoDataPeriodo(MovimentoBuscaDTO busca) {
        busca.setDataFim(DateUtil.getCurrentDateString());

        Calendar dateInicio = DateUtil.getCurrentCalendar();
        dateInicio.add(Calendar.DAY_OF_MONTH, -extratoView.getPeriodo());

        busca.setDataInicio(DateUtil.parseDefaultUSA(dateInicio));
    }


}
