package br.com.reservafacil.agendamento.util;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class DataTest {

	Data sujeito;

	@Before
	public void antes() {
		sujeito = new Data(new GregorianCalendar(2016, 9, 19, 0, 0, 0));
	}

	@Test
	public void DEVE_VERIFICAR_SE_DATA_ATUAL_ANTERIOR_A_DATA_COMPARADA() {
		Calendar dataComparada = new GregorianCalendar(2016, 9, 20, 23, 59, 59);
		assertTrue(sujeito.before(dataComparada));
	}

	@Test
	public void DEVE_VERIFICAR_SE_DATA_ATUAL_POSTERIOR_A_DATA_COMPARADA() {
		Calendar dataComparada = new GregorianCalendar(2016, 9, 18, 23, 59, 59);
		assertTrue(sujeito.after(dataComparada));
	}

	@Test
	public void DEVE_VERIFICAR_SE_DATA_ATUAL_IGUAL_A_DATA_COMPARADA() {
		Calendar dataComparada = new GregorianCalendar(2016, 9, 19, 23, 59, 59);
		assertTrue(sujeito.equals(dataComparada));
	}
}
