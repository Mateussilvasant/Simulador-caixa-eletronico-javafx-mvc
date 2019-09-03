package br.com.bancoms.service;

import br.com.bancoms.dao.MovimentoDAO;
import br.com.bancoms.dto.MovimentoTO;
import br.com.bancoms.model.Conta;
import br.com.bancoms.model.Movimento;

import java.util.ArrayList;

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

    public ArrayList<Movimento> listarMovimentos(Conta conta) {
        return movimentoDAO.listarMovimentos(conta);
    }

    public void registrarMovimento(MovimentoTO movimentoTO) {
        movimentoDAO.registrarMovimento(movimentoTO);
    }

    public void registrarMovimentos(ArrayList<MovimentoTO> movimentos) {

        movimentos.forEach(movimentoTO -> movimentoDAO.registrarMovimento(movimentoTO));

    }
}
