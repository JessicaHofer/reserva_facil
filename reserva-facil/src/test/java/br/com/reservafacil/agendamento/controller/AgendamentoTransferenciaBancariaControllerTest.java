package br.com.reservafacil.agendamento.controller;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.com.reservafacil.agendamento.modelo.AgendamentoTransferenciaBancaria;
import br.com.reservafacil.agendamento.modelo.ContaBancaria;
import br.com.reservafacil.agendamento.modelo.TipoDeTransacao;

public class AgendamentoTransferenciaBancariaControllerTest {

	AgendamentoTransferenciaBancariaController sujeito;

	AgendamentoTransferenciaBancaria agendamentoComSaldoInsuficiente;

	AgendamentoTransferenciaBancaria agendamentoComTransacaoTipoA;

	ContaBancaria contaOrigemComSaldoInsuficiente;

	ContaBancaria contaOrigemComSaldo;

	ContaBancaria contaDestino;

	Calendar dataDeAgendamento;

	TipoDeTransacao tipoDeTransacaoA;

	TipoDeTransacao tipoDeTransacaoB;

	TipoDeTransacao tipoDeTransacaoC;

	TipoDeTransacao tipoDeTransacaoD;

	@Before
	public void antes() {
		contaOrigemComSaldoInsuficiente = new ContaBancaria("12345-0", 50.0);
		contaDestino = new ContaBancaria("67890-1", 100.0);
		Double valorTransferido = 70.0;
		tipoDeTransacaoA = new TipoDeTransacao("A");
		agendamentoComSaldoInsuficiente = new AgendamentoTransferenciaBancaria(contaOrigemComSaldoInsuficiente,
				contaDestino, valorTransferido, dataDeAgendamento, tipoDeTransacaoA);

		contaOrigemComSaldo = new ContaBancaria("12345-0", 100.0);
		agendamentoComTransacaoTipoA = new AgendamentoTransferenciaBancaria(contaOrigemComSaldo, contaDestino,
				valorTransferido, dataDeAgendamento, tipoDeTransacaoA);

		sujeito = new AgendamentoTransferenciaBancariaController();
	}

	@Test
	public void DEVE_EXIBIR_TELA_DE_ERRO_QUANDO_SALDO_DA_CONTA_ORIGEM_FOR_INSUFICIENTE() {
		assertEquals("tela-de-erro", sujeito.realizaAgendamento(agendamentoComSaldoInsuficiente));
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_A() {
		assertEquals("/calcula-valor-com-taxa-do-tipo-A", sujeito.realizaAgendamento(agendamentoComTransacaoTipoA));
		assertEquals("tela-de-confirmacao", sujeito.calculaValorComTaxaDoTipoA(agendamentoComTransacaoTipoA));
		assertEquals(Double.valueOf(2 + 0.03 * agendamentoComTransacaoTipoA.getValorTransferido()),
				agendamentoComTransacaoTipoA.getTipoDeTransacao().getTaxa());
	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_B_COM_TAXA_DE_10_REAIS() {

	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_B_COM_TAXA_DE_8_REAIS() {

	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_12_PORCENTO() {

	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_21_PORCENTO() {

	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_43_PORCENTO() {

	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_54_PORCENTO() {

	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_67_PORCENTO() {

	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_74_PORCENTO() {

	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_C_COM_TAXA_DE_83_PORCENTO() {

	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_ATE_25000_REAIS() {

	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_DE_25001_A_120000_REAIS() {

	}

	@Test
	public void DEVE_REALIZAR_AGENDAMENTO_COM_TRANSACAO_DE_TIPO_D_COM_VALOR_MAIOR_DE_120000_REAIS() {

	}
}
