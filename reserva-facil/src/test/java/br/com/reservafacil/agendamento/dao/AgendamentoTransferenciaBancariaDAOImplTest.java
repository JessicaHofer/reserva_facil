package br.com.reservafacil.agendamento.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import br.com.reservafacil.agendamento.modelo.AgendamentoTransferenciaBancaria;
import br.com.reservafacil.agendamento.modelo.ContaBancaria;
import br.com.reservafacil.agendamento.modelo.TipoDeTransacao;

public class AgendamentoTransferenciaBancariaDAOImplTest extends BaseIntegrationTest {

	AgendamentoTransferenciaBancariaDAOImpl sujeito;

	AgendamentoTransferenciaBancaria agendamento1;

	AgendamentoTransferenciaBancaria agendamento2;

	AgendamentoTransferenciaBancaria agendamento3;

	ContaBancaria contaOrigem1;

	ContaBancaria contaOrigem2;

	ContaBancaria contaDestino;

	Calendar dataDeCadastro;

	Calendar dataDeAgendamento;

	TipoDeTransacao tipoDeTransacaoA;

	TipoDeTransacao tipoDeTransacaoB;

	@Before
	public void antes() {

		contaOrigem1 = new ContaBancaria("12345-0", 130000.0);
		contaOrigem2 = new ContaBancaria("54321-0", 30000.0);
		contaDestino = new ContaBancaria("67890-1", 100.0);
		dataDeCadastro = new GregorianCalendar(2016, 9, 19);
		tipoDeTransacaoA = new TipoDeTransacao("A");
		tipoDeTransacaoA = new TipoDeTransacao("B");

		agendamento1 = new AgendamentoTransferenciaBancaria(contaOrigem1, contaDestino, 100.0, dataDeCadastro,
				dataDeAgendamento, tipoDeTransacaoA);

		agendamento2 = new AgendamentoTransferenciaBancaria(contaOrigem1, contaDestino, 500.0, dataDeCadastro,
				dataDeAgendamento, tipoDeTransacaoB);

		agendamento3 = new AgendamentoTransferenciaBancaria(contaOrigem2, contaDestino, 300.0, dataDeCadastro,
				dataDeAgendamento, tipoDeTransacaoB);

		manager.getTransaction().begin();
		manager.persist(agendamento1);
		manager.persist(agendamento2);
		manager.persist(agendamento3);

		sujeito = new AgendamentoTransferenciaBancariaDAOImpl(manager);
		sujeito.insere(agendamento1);
		sujeito.insere(agendamento2);
		sujeito.insere(agendamento3);
	}

	@Test
	public void DEVE_LISTAR_AGENDAMENTOS_PARA_CONTA_DE_ORIGEM_1() {
		assertEquals(Arrays.asList(agendamento1, agendamento2), sujeito.todosAgendamentos(contaOrigem1));
		assertEquals(2, sujeito.todosAgendamentos(contaOrigem1).size());
	}

	@Test
	public void DEVE_LISTAR_AGENDAMENTOS_PARA_CONTA_DE_ORIGEM_2() {
		assertEquals(Arrays.asList(agendamento3), sujeito.todosAgendamentos(contaOrigem2));
		assertEquals(1, sujeito.todosAgendamentos(contaOrigem2).size());
	}

}
