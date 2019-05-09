package br.com.bancoms.service;

import java.util.ArrayList;

import br.com.bancoms.dao.MovimentoDAO;
import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.Conta;
import br.com.bancoms.model.Movimento;

public class MovimentoService
{

    private static MovimentoService movimentoService;
    private MovimentoDAO movimentoDAO;

    public MovimentoService()
    {
	movimentoDAO = new MovimentoDAO();
    }

    public static MovimentoService getInstance()
    {

	if (movimentoService != null)
	{
	    return movimentoService;
	}

	movimentoService = new MovimentoService();

	return movimentoService;

    }

    public int cadastrarMovimento(Movimento movimento, Conta conta)
    {
	return movimentoDAO.cadastrarMovimento(movimento, conta);
    }

    public ArrayList<Movimento> listarMovimentos(Conta conta)
    {
	return movimentoDAO.listarMovimentos(conta);
    }
}
