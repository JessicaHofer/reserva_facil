package br.com.reservafacil.agendamento.dao;

import java.util.List;

import br.com.reservafacil.agendamento.modelo.AgendamentoTransferenciaBancaria;
import br.com.reservafacil.agendamento.modelo.ContaBancaria;

public interface AgendamentoTransferenciaBancariaDAO {

	void insere(AgendamentoTransferenciaBancaria agendamento);

	List<AgendamentoTransferenciaBancaria> todosAgendamentos(ContaBancaria conta);
}
