package br.com.reservafacil.agendamento.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ContaBancariaTest {
	
	ContaBancaria contaBancariaInvalida;
	ContaBancaria contaBancariaValida ;
	
	@Before
	public void antes () {
		contaBancariaInvalida = new ContaBancaria("1234-0", 25.0);
		contaBancariaValida = new ContaBancaria("12345-0", 50.0);
	}

	@Test
	public void DEVE_RETORNAR_FALSE_PARA_CONTA_FORA_DO_PADRAO() {
		assertFalse(contaBancariaInvalida.isNumeroDaContaNoPadrao());
	}
	
	@Test
	public void DEVE_RETORNAR_TRUE_PARA_CONTA_NO_PADRAO() {
		assertTrue(contaBancariaValida.isNumeroDaContaNoPadrao());
	}
	
}
