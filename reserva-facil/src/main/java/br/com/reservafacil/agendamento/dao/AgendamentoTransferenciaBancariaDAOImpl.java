package br.com.reservafacil.agendamento.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.google.common.annotations.VisibleForTesting;

import br.com.reservafacil.agendamento.modelo.AgendamentoTransferenciaBancaria;
import br.com.reservafacil.agendamento.modelo.ContaBancaria;

@Repository
public class AgendamentoTransferenciaBancariaDAOImpl implements AgendamentoTransferenciaBancariaDAO {

	@PersistenceContext
	private EntityManager em;

	public AgendamentoTransferenciaBancariaDAOImpl() {
	}

	@VisibleForTesting
	public AgendamentoTransferenciaBancariaDAOImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public void insere(AgendamentoTransferenciaBancaria agendamento) {
		em.persist(agendamento);
	}

	@Override
	public List<AgendamentoTransferenciaBancaria> todosAgendamentos(ContaBancaria conta) {
		TypedQuery<AgendamentoTransferenciaBancaria> query = em.createQuery(
				"select a from AgendamentoTransferenciaBancaria a where a.contaOrigem =:conta ",
				AgendamentoTransferenciaBancaria.class);
		query.setParameter("conta", conta);
		return query.getResultList();
	}

}
