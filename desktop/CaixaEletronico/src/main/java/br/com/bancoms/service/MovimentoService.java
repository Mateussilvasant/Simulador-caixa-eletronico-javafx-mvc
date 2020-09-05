package br.com.bancoms.service;

import br.com.bancoms.dao.MovimentoDAO;
import br.com.bancoms.dto.MovimentoBuscaDTO;
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

    public Optional<ArrayList<Optional<Movimento>>> listarMovimentosPorTipo(MovimentoBuscaDTO movimentoBuscaDTO) {
        return movimentoDAO.listarMovimentosPorTipo(movimentoBuscaDTO);
    }

    public Optional<ArrayList<Optional<Movimento>>> listarTodosMovimentos(MovimentoBuscaDTO movimentoBuscaDTO) {
        return movimentoDAO.listarTodosMovimentos(movimentoBuscaDTO);
    }

    public double getSaldoDiferencial(MovimentoBuscaDTO busca) {
        return movimentoDAO.getSaldoDiferencial(busca);
    }

    public void registrarMovimento(Movimento movimento) {
        movimentoDAO.registrarMovimento(movimento);
    }

    public void registrarMovimentos(ArrayList<Movimento> movimentos) {
        if (!movimentos.isEmpty()) {
            movimentos.forEach(movimento -> movimentoDAO.registrarMovimento(movimento));
        }
    }
}
