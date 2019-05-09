package br.com.bancoms.tests;

import org.junit.jupiter.api.Test;

import br.com.bancoms.dao.ClienteDAO;
import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.ContaCorrente;
import junit.framework.TestCase;

public class ClienteTest extends TestCase
{
    @Test
    public void testLoginClienteSucesso()
    {
	ContaCorrente conta = new ContaCorrente(1, "CONTA-CORRENTE", 1, 123456, 1000.00);
	Cliente clienteRetornoEsperado = new Cliente("Mateus", "da Silva Santos", conta);

	ClienteDAO dao = new ClienteDAO();
	Cliente clienteRetornoFeito = dao.realizarLogin(123456, "123a");

	assertEquals(clienteRetornoEsperado.getNome(), clienteRetornoFeito.getNome());
	assertEquals(clienteRetornoEsperado.getSobrenome(), clienteRetornoFeito.getSobrenome());
	assertEquals(clienteRetornoEsperado.getConta().getId(), clienteRetornoFeito.getConta().getId());
	assertEquals(clienteRetornoEsperado.getConta().getDescricao(), clienteRetornoFeito.getConta().getDescricao());
	assertEquals(clienteRetornoEsperado.getConta().getNumero(), clienteRetornoFeito.getConta().getNumero());
	assertEquals(clienteRetornoEsperado.getConta().getSaldo(), clienteRetornoFeito.getConta().getSaldo());
	assertEquals(clienteRetornoEsperado.getConta().getTipo(), clienteRetornoFeito.getConta().getTipo());
    }

    @Test
    public void testLoginClienteFalho()
    {
	ContaCorrente conta = new ContaCorrente(1, "CONTA-CORRENTE", 1, 123456, 1000.00);
	Cliente clienteRetornoEsperado = new Cliente("Mateus", "da Silva Santos", conta);

	ClienteDAO dao = new ClienteDAO();
	Cliente clienteRetornoFeito = dao.realizarLogin(0, "123a");

	assertEquals(clienteRetornoEsperado.getNome(), clienteRetornoFeito.getNome());
	assertEquals(clienteRetornoEsperado.getSobrenome(), clienteRetornoFeito.getSobrenome());
	assertEquals(clienteRetornoEsperado.getConta().getId(), clienteRetornoFeito.getConta().getId());
	assertEquals(clienteRetornoEsperado.getConta().getDescricao(), clienteRetornoFeito.getConta().getDescricao());
	assertEquals(clienteRetornoEsperado.getConta().getNumero(), clienteRetornoFeito.getConta().getNumero());
	assertEquals(clienteRetornoEsperado.getConta().getSaldo(), clienteRetornoFeito.getConta().getSaldo());
	assertEquals(clienteRetornoEsperado.getConta().getTipo(), clienteRetornoFeito.getConta().getTipo());

    }
}
