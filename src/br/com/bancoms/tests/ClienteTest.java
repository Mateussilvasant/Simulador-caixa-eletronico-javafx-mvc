package br.com.bancoms.tests;

import org.junit.jupiter.api.Test;

import br.com.bancoms.model.Cliente;
import br.com.bancoms.service.ClienteService;
import br.com.bancoms.vo.ClienteVO;
import junit.framework.TestCase;

public class ClienteTest extends TestCase
{
    @Test
    public void testLoginClienteSucesso()
    {
	Cliente clienteRetornoEsperado = new Cliente("Mateus", "da Silva Santos");

	Cliente clienteRetornoFeito = ClienteService.getInstance().realizarLogin(new ClienteVO(123456, "123a"));

	assertEquals(clienteRetornoEsperado.getNome(), clienteRetornoFeito.getNome());
	assertEquals(clienteRetornoEsperado.getSobrenome(), clienteRetornoFeito.getSobrenome());
    }

}
