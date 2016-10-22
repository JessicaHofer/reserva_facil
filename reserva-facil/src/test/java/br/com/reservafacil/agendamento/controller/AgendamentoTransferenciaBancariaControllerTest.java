package br.com.reservafacil.agendamento.controller;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import br.com.reservafacil.agendamento.modelo.AgendamentoTransferenciaBancaria;
import br.com.reservafacil.agendamento.modelo.ContaBancaria;
import br.com.reservafacil.agendamento.modelo.TipoDeTransacao;

public class AgendamentoTransferenciaBancariaControllerTest {

	AgendamentoTransferenciaBancariaController sujeito;

	AgendamentoTransferenciaBancaria agendamentoComSaldoInsuficiente;

	AgendamentoTransferenciaBancaria agendamentoComTransacaoTipoA;

	AgendamentoTransferenciaBancaria agendamentoComTransacaoTipoB;

	AgendamentoTransferenciaBancaria agendamentoComTransacaoTipoC;

	AgendamentoTransferenciaBancaria agendamentoComTransacaoTipoD;

	ContaBancaria contaOrigemComSaldoInsuficiente;

	ContaBancaria contaOrigemComSaldo;

	ContaBancaria contaDestino;

	Calendar dataDeCadastro;

	Calendar dataDeAgendamento;

	Double valorTransferido;

	TipoDeTransacao tipoDeTransacaoA;

	TipoDeTransacao tipoDeTransacaoB;

	TipoDeTransacao tipoDeTransacaoC;

	TipoDeTransacao tipoDeTransacaoD;

	@Before
	public void antes() {
		contaOrigemComSaldoInsuficiente = new ContaBancaria("12345-0", 50.0);
		contaOrigemComSaldo = new ContaBancaria("12345-0", 130000.0);
		contaDestino = new ContaBancaria("67890-1", 100.0);
		dataDeCadastro = new GregorianCalendar(2016, 9, 19);
		valorTransferido = 100.0;
		tipoDeTransacaoA = new TipoDeTransacao("A");
		tipoDeTransacaoB = new TipoDeTransacao("B");
		tipoDeTransacaoC = new TipoDeTransacao("C");
		tipoDeTransacaoD = new TipoDeTransacao("D");

		sujeito = new AgendamentoTransferenciaBancariaController();
	}

	@Test
	public void DEVE_EXIBIR_TELA_DE_ERRO_QUANDO_SALDO_DA_CONTA_ORIGEM_FOR_INSUFICIENTE() {

		dataDeAgendamento = new GregorianCalendar(2016, 9, 20);

		agendamentoComSaldoInsuficiente = new AgendamentoTransferenciaBancaria(contaOrigemComSaldoInsuficiente,
				contaDestino, valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoA);

		assertEquals("tela-de-erro", sujeito.realizaAgendamento(agendamentoComSaldoInsuficiente));
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_A() {

		dataDeAgendamento = new GregorianCalendar(2016, 9, 20);

		agendamentoComTransacaoTipoA = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoA);

		assertEquals("/calcula-valor-com-taxa-do-tipo-A", sujeito.realizaAgendamento(agendamentoComTransacaoTipoA));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoA(agendamentoComTransacaoTipoA));
		assertEquals(Double.valueOf(2 + 0.03 * agendamentoComTransacaoTipoA.getValorTransferido()),
				agendamentoComTransacaoTipoA.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_B_COM_TAXA_DE_10_REAIS() {

		dataDeAgendamento = new GregorianCalendar(2016, 10, 18);

		agendamentoComTransacaoTipoB = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoB);

		assertEquals("/calcula-valor-com-taxa-do-tipo-B", sujeito.realizaAgendamento(agendamentoComTransacaoTipoB));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoB(agendamentoComTransacaoTipoB));
		assertEquals(Double.valueOf(10.0), agendamentoComTransacaoTipoB.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_B_COM_TAXA_DE_8_REAIS() {

		dataDeAgendamento = new GregorianCalendar(2016, 10, 30);

		agendamentoComTransacaoTipoB = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoB);

		assertEquals("/calcula-valor-com-taxa-do-tipo-B", sujeito.realizaAgendamento(agendamentoComTransacaoTipoB));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoB(agendamentoComTransacaoTipoB));
		assertEquals(Double.valueOf(8.0), agendamentoComTransacaoTipoB.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_1_2_PORCENTO() {

		dataDeAgendamento = new GregorianCalendar(2016, 10, 23);

		agendamentoComTransacaoTipoC = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoC);

		assertEquals("/calcula-valor-com-taxa-do-tipo-C", sujeito.realizaAgendamento(agendamentoComTransacaoTipoC));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoC(agendamentoComTransacaoTipoC));
		assertEquals(Double.valueOf(0.012 * agendamentoComTransacaoTipoC.getValorTransferido()),
				agendamentoComTransacaoTipoC.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_2_1_PORCENTO() {

		dataDeAgendamento = new GregorianCalendar(2016, 10, 18);

		agendamentoComTransacaoTipoC = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoC);

		assertEquals("/calcula-valor-com-taxa-do-tipo-C", sujeito.realizaAgendamento(agendamentoComTransacaoTipoC));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoC(agendamentoComTransacaoTipoC));
		assertEquals(Double.valueOf(0.021 * agendamentoComTransacaoTipoC.getValorTransferido()),
				agendamentoComTransacaoTipoC.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_4_3_PORCENTO() {

		dataDeAgendamento = new GregorianCalendar(2016, 10, 12);

		agendamentoComTransacaoTipoC = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoC);

		assertEquals("/calcula-valor-com-taxa-do-tipo-C", sujeito.realizaAgendamento(agendamentoComTransacaoTipoC));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoC(agendamentoComTransacaoTipoC));
		assertEquals(Double.valueOf(0.043 * agendamentoComTransacaoTipoC.getValorTransferido()),
				agendamentoComTransacaoTipoC.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_5_4_PORCENTO() {

		dataDeAgendamento = new GregorianCalendar(2016, 10, 7);

		agendamentoComTransacaoTipoC = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoC);

		assertEquals("/calcula-valor-com-taxa-do-tipo-C", sujeito.realizaAgendamento(agendamentoComTransacaoTipoC));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoC(agendamentoComTransacaoTipoC));
		assertEquals(Double.valueOf(0.054 * agendamentoComTransacaoTipoC.getValorTransferido()),
				agendamentoComTransacaoTipoC.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_6_7_PORCENTO() {

		dataDeAgendamento = new GregorianCalendar(2016, 10, 2);

		agendamentoComTransacaoTipoC = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoC);

		assertEquals("/calcula-valor-com-taxa-do-tipo-C", sujeito.realizaAgendamento(agendamentoComTransacaoTipoC));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoC(agendamentoComTransacaoTipoC));
		assertEquals(Double.valueOf(0.067 * agendamentoComTransacaoTipoC.getValorTransferido()),
				agendamentoComTransacaoTipoC.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_7_4_PORCENTO() {

		dataDeAgendamento = new GregorianCalendar(2016, 9, 29);

		agendamentoComTransacaoTipoC = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoC);

		assertEquals("/calcula-valor-com-taxa-do-tipo-C", sujeito.realizaAgendamento(agendamentoComTransacaoTipoC));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoC(agendamentoComTransacaoTipoC));
		assertEquals(Double.valueOf(0.074 * agendamentoComTransacaoTipoC.getValorTransferido()),
				agendamentoComTransacaoTipoC.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_8_3_PORCENTO() {

		dataDeAgendamento = new GregorianCalendar(2016, 9, 24);

		agendamentoComTransacaoTipoC = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoC);

		assertEquals("/calcula-valor-com-taxa-do-tipo-C", sujeito.realizaAgendamento(agendamentoComTransacaoTipoC));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoC(agendamentoComTransacaoTipoC));
		assertEquals(Double.valueOf(0.083 * agendamentoComTransacaoTipoC.getValorTransferido()),
				agendamentoComTransacaoTipoC.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_ATE_25000_REAIS() {

		valorTransferido = 24000.0;

		agendamentoComTransacaoTipoD = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoD);

		assertEquals("/calcula-valor-com-taxa-do-tipo-D", sujeito.realizaAgendamento(agendamentoComTransacaoTipoD));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoD(agendamentoComTransacaoTipoD));
		assertEquals(Double.valueOf(2 + 0.03 * agendamentoComTransacaoTipoD.getValorTransferido()),
				agendamentoComTransacaoTipoD.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_DE_25001_A_120000_REAIS_E_TAXA_DE_10_REAIS() {

		valorTransferido = 30000.0;

		dataDeAgendamento = new GregorianCalendar(2016, 10, 18);

		agendamentoComTransacaoTipoD = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoD);

		assertEquals("/calcula-valor-com-taxa-do-tipo-D", sujeito.realizaAgendamento(agendamentoComTransacaoTipoD));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoD(agendamentoComTransacaoTipoD));
		assertEquals(Double.valueOf(10.0), agendamentoComTransacaoTipoD.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_DE_25001_A_120000_REAIS_E_TAXA_DE_8_REAIS() {

		valorTransferido = 110000.0;

		dataDeAgendamento = new GregorianCalendar(2016, 10, 30);

		agendamentoComTransacaoTipoD = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoD);

		assertEquals("/calcula-valor-com-taxa-do-tipo-D", sujeito.realizaAgendamento(agendamentoComTransacaoTipoD));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoD(agendamentoComTransacaoTipoD));
		assertEquals(Double.valueOf(8.0), agendamentoComTransacaoTipoD.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_MAIOR_A_120000_REAIS_E_TAXA_DE_1_2_PORCENTO() {

		valorTransferido = 125000.0;

		dataDeAgendamento = new GregorianCalendar(2016, 10, 23);

		agendamentoComTransacaoTipoD = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoD);

		assertEquals("/calcula-valor-com-taxa-do-tipo-D", sujeito.realizaAgendamento(agendamentoComTransacaoTipoD));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoD(agendamentoComTransacaoTipoD));
		assertEquals(Double.valueOf(0.012 * agendamentoComTransacaoTipoD.getValorTransferido()),
				agendamentoComTransacaoTipoD.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_MAIOR_A_120000_REAIS_E_TAXA_DE_2_1_PORCENTO() {

		valorTransferido = 125000.0;

		dataDeAgendamento = new GregorianCalendar(2016, 10, 18);

		agendamentoComTransacaoTipoD = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoD);

		assertEquals("/calcula-valor-com-taxa-do-tipo-D", sujeito.realizaAgendamento(agendamentoComTransacaoTipoD));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoD(agendamentoComTransacaoTipoD));
		assertEquals(Double.valueOf(0.021 * agendamentoComTransacaoTipoD.getValorTransferido()),
				agendamentoComTransacaoTipoD.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_MAIOR_A_120000_REAIS_E_TAXA_DE_4_3_PORCENTO() {

		valorTransferido = 125000.0;

		dataDeAgendamento = new GregorianCalendar(2016, 10, 12);

		agendamentoComTransacaoTipoD = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoD);

		assertEquals("/calcula-valor-com-taxa-do-tipo-D", sujeito.realizaAgendamento(agendamentoComTransacaoTipoD));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoD(agendamentoComTransacaoTipoD));
		assertEquals(Double.valueOf(0.043 * agendamentoComTransacaoTipoD.getValorTransferido()),
				agendamentoComTransacaoTipoD.getTipoDeTransacao().getTaxa());
	}
	
	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_MAIOR_A_120000_REAIS_E_TAXA_DE_5_4_PORCENTO() {

		valorTransferido = 125000.0;

		dataDeAgendamento = new GregorianCalendar(2016, 10, 7);

		agendamentoComTransacaoTipoD = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoD);

		assertEquals("/calcula-valor-com-taxa-do-tipo-D", sujeito.realizaAgendamento(agendamentoComTransacaoTipoD));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoD(agendamentoComTransacaoTipoD));
		assertEquals(Double.valueOf(0.054 * agendamentoComTransacaoTipoD.getValorTransferido()),
				agendamentoComTransacaoTipoD.getTipoDeTransacao().getTaxa());
	}
	
	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_MAIOR_A_120000_REAIS_E_TAXA_DE_6_7_PORCENTO() {

		valorTransferido = 125000.0;

		dataDeAgendamento = new GregorianCalendar(2016, 10, 2);

		agendamentoComTransacaoTipoD = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoD);

		assertEquals("/calcula-valor-com-taxa-do-tipo-D", sujeito.realizaAgendamento(agendamentoComTransacaoTipoD));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoD(agendamentoComTransacaoTipoD));
		assertEquals(Double.valueOf(0.067 * agendamentoComTransacaoTipoD.getValorTransferido()),
				agendamentoComTransacaoTipoD.getTipoDeTransacao().getTaxa());
	}
	
	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_MAIOR_A_120000_REAIS_E_TAXA_DE_7_4_PORCENTO() {

		valorTransferido = 125000.0;

		dataDeAgendamento = new GregorianCalendar(2016, 9, 29);

		agendamentoComTransacaoTipoD = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoD);

		assertEquals("/calcula-valor-com-taxa-do-tipo-D", sujeito.realizaAgendamento(agendamentoComTransacaoTipoD));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoD(agendamentoComTransacaoTipoD));
		assertEquals(Double.valueOf(0.074 * agendamentoComTransacaoTipoD.getValorTransferido()),
				agendamentoComTransacaoTipoD.getTipoDeTransacao().getTaxa());
	}
	
	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_MAIOR_A_120000_REAIS_E_TAXA_DE_8_3_PORCENTO() {

		valorTransferido = 125000.0;

		dataDeAgendamento = new GregorianCalendar(2016, 9, 24);

		agendamentoComTransacaoTipoD = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeCadastro, dataDeAgendamento, tipoDeTransacaoD);

		assertEquals("/calcula-valor-com-taxa-do-tipo-D", sujeito.realizaAgendamento(agendamentoComTransacaoTipoD));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoD(agendamentoComTransacaoTipoD));
		assertEquals(Double.valueOf(0.083 * agendamentoComTransacaoTipoD.getValorTransferido()),
				agendamentoComTransacaoTipoD.getTipoDeTransacao().getTaxa());
	}
}
