package br.com.bancoms.service;

import br.com.bancoms.dao.MovimentoDAO;
import br.com.bancoms.dto.MovimentoTO;
import br.com.bancoms.model.Movimento;

import java.util.ArrayList;
import java.util.Optional;

public class MovimentoService {

    private static MovimentoService movimentoService;
    private MovimentoDAO movimentoDAO;

    private MovimentoService() {
        movimentoDAO = new MovimentoDAO();
    }

    public static MovimentoService getInstance() {

        if (movimentoService != null) {
            return movimentoService;
        }

        movimentoService = new MovimentoService();

        return movimentoService;

    }

    public ArrayList<Optional<Movimento>> listarMovimentosPorTipo(int numeroConta, int tipoMovimento, String dataInicio, String dataFim) {
        return movimentoDAO.listarMovimentosPorTipo(numeroConta, tipoMovimento, dataInicio, dataFim);
    }

    public ArrayList<Optional<Movimento>> listarTodosMovimentos(int numeroConta, String dataInicio, String dataFim) {
        return movimentoDAO.listarTodosMovimentos(numeroConta, dataInicio, dataFim);
    }

    public void registrarMovimento(MovimentoTO movimentoTO) {
        movimentoDAO.registrarMovimento(movimentoTO);
    }

    public void registrarMovimentos(ArrayList<MovimentoTO> movimentos) {
        if (!movimentos.isEmpty()) {
            movimentos.forEach(movimentoTO -> movimentoDAO.registrarMovimento(movimentoTO));
        }
    }
}
