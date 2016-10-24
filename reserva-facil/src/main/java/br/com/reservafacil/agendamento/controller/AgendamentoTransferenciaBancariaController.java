package br.com.reservafacil.agendamento.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.annotations.VisibleForTesting;

import br.com.reservafacil.agendamento.dao.AgendamentoTransferenciaBancariaDAO;
import br.com.reservafacil.agendamento.modelo.AgendamentoTransferenciaBancaria;
import br.com.reservafacil.agendamento.modelo.TipoDeTransacao;
import br.com.reservafacil.agendamento.util.Data;

@Controller
public class AgendamentoTransferenciaBancariaController {

	Data data;

	@Autowired
	AgendamentoTransferenciaBancariaDAO agendamentoDao;

	public AgendamentoTransferenciaBancariaController() {
	}

	@VisibleForTesting
	public AgendamentoTransferenciaBancariaController(AgendamentoTransferenciaBancariaDAO agendamentoDao) {
		this.agendamentoDao = agendamentoDao;
	}

	@RequestMapping(value = "/carregaFormularioDeAgendamento", method = RequestMethod.GET)
	public String carregaFormulario() {
		return "formulario-de-agendamento";
	}

	@RequestMapping(value = "/realizaAgendamento", method = RequestMethod.GET)
	public String realizaAgendamento(AgendamentoTransferenciaBancaria agendamento) {

		if (!agendamento.getContaDestino().isNumeroDaContaNoPadrao()) {
			return "tela-de-erro";
		}

		if (agendamento.getValorTransferido() > agendamento.getContaOrigem().getSaldo()) {
			return "tela-de-erro";
		}

		if ("A".equals(agendamento.getTipoDeTransacao().getTipo())) {
			return "/calcula-valor-com-taxa-do-tipo-A";
		} else if ("B".equals(agendamento.getTipoDeTransacao().getTipo())) {
			return "/calcula-valor-com-taxa-do-tipo-B";
		} else if ("C".equals(agendamento.getTipoDeTransacao().getTipo())) {
			return "/calcula-valor-com-taxa-do-tipo-C";
		} else if ("D".equals(agendamento.getTipoDeTransacao().getTipo())) {
			return "/calcula-valor-com-taxa-do-tipo-D";
		}

		return "tela-inicial";
	}

	@RequestMapping(value = "/calcula-valor-com-taxa-do-tipo-A", method = RequestMethod.GET)
	public String calculaValorComTaxaDoTipoA(AgendamentoTransferenciaBancaria agendamento) {

		Double taxa = 2.0 + 0.03 * agendamento.getValorTransferido();

		TipoDeTransacao tipoDeTransacao = agendamento.getTipoDeTransacao();
		tipoDeTransacao.setTaxa(taxa);

		agendamentoDao.insere(agendamento);

		return "tela-de-confirmacao";
	}

	@RequestMapping(value = "/calcula-valor-com-taxa-do-tipo-B", method = RequestMethod.GET)
	public String calculaValorComTaxaDoTipoB(AgendamentoTransferenciaBancaria agendamento) {

		data = new Data(agendamento.getDataDeAgendamento());

		Calendar dataDeCadastro = agendamento.getDataDeCadastro();
		dataDeCadastro.add(Calendar.DAY_OF_MONTH, 30);

		Double taxa;

		if (data.equals(dataDeCadastro) || data.before(dataDeCadastro)) {
			taxa = 10.0;

			TipoDeTransacao tipoDeTransacao = agendamento.getTipoDeTransacao();
			tipoDeTransacao.setTaxa(taxa);
		} else {
			taxa = 8.0;

			TipoDeTransacao tipoDeTransacao = agendamento.getTipoDeTransacao();
			tipoDeTransacao.setTaxa(taxa);
		}

		agendamentoDao.insere(agendamento);

		return "tela-de-confirmacao";
	}

	@RequestMapping(value = "/calcula-valor-com-taxa-do-tipo-C", method = RequestMethod.GET)
	public String calculaValorComTaxaDoTipoC(AgendamentoTransferenciaBancaria agendamento) {

		data = new Data(agendamento.getDataDeAgendamento());

		// Data de cadastro até 5 dias
		Calendar dataDeCadastro = agendamento.getDataDeCadastro();
		dataDeCadastro.add(Calendar.DAY_OF_MONTH, 5);

		Double taxa = 0.0;

		if (taxa.equals(0.0) && (data.equals(dataDeCadastro) || data.before(dataDeCadastro))) {
			taxa = 0.083 * agendamento.getValorTransferido();

			TipoDeTransacao tipoDeTransacao = agendamento.getTipoDeTransacao();
			tipoDeTransacao.setTaxa(taxa);
		}

		// Data de cadastro até 10 dias
		dataDeCadastro.add(Calendar.DAY_OF_MONTH, 5);

		if (taxa.equals(0.0) && (data.equals(dataDeCadastro) || data.before(dataDeCadastro))) {
			taxa = 0.074 * agendamento.getValorTransferido();

			TipoDeTransacao tipoDeTransacao = agendamento.getTipoDeTransacao();
			tipoDeTransacao.setTaxa(taxa);
		}

		// Data de cadastro até 15 dias
		dataDeCadastro.add(Calendar.DAY_OF_MONTH, 5);

		if (taxa.equals(0.0) && (data.equals(dataDeCadastro) || data.before(dataDeCadastro))) {
			taxa = 0.067 * agendamento.getValorTransferido();

			TipoDeTransacao tipoDeTransacao = agendamento.getTipoDeTransacao();
			tipoDeTransacao.setTaxa(taxa);
		}

		// Data de cadastro até 20 dias
		dataDeCadastro.add(Calendar.DAY_OF_MONTH, 5);

		if (taxa.equals(0.0) && (data.equals(dataDeCadastro) || data.before(dataDeCadastro))) {
			taxa = 0.054 * agendamento.getValorTransferido();

			TipoDeTransacao tipoDeTransacao = agendamento.getTipoDeTransacao();
			tipoDeTransacao.setTaxa(taxa);
		}

		// Data de cadastro até 25 dias
		dataDeCadastro.add(Calendar.DAY_OF_MONTH, 5);

		if (taxa.equals(0.0) && (data.equals(dataDeCadastro) || data.before(dataDeCadastro))) {
			taxa = 0.043 * agendamento.getValorTransferido();

			TipoDeTransacao tipoDeTransacao = agendamento.getTipoDeTransacao();
			tipoDeTransacao.setTaxa(taxa);
		}

		// Data de cadastro até 30 dias
		dataDeCadastro.add(Calendar.DAY_OF_MONTH, 5);

		if (taxa.equals(0.0) && (data.equals(dataDeCadastro) || data.before(dataDeCadastro))) {
			taxa = 0.021 * agendamento.getValorTransferido();

			TipoDeTransacao tipoDeTransacao = agendamento.getTipoDeTransacao();
			tipoDeTransacao.setTaxa(taxa);
		}

		if (taxa.equals(0.0) && data.after(dataDeCadastro)) {
			taxa = 0.012 * agendamento.getValorTransferido();

			TipoDeTransacao tipoDeTransacao = agendamento.getTipoDeTransacao();
			tipoDeTransacao.setTaxa(taxa);
		}

		agendamentoDao.insere(agendamento);

		return "tela-de-confirmacao";
	}

	@RequestMapping(value = "/calcula-valor-com-taxa-do-tipo-D", method = RequestMethod.GET)
	public String calculaValorComTaxaDoTipoD(AgendamentoTransferenciaBancaria agendamento) {

		if (agendamento.getValorTransferido() <= 25000.0) {
			return calculaValorComTaxaDoTipoA(agendamento);
		} else if (agendamento.getValorTransferido() >= 25001.0 && agendamento.getValorTransferido() <= 120000.0) {
			return calculaValorComTaxaDoTipoB(agendamento);
		} else {
			return calculaValorComTaxaDoTipoC(agendamento);
		}
	}

	@RequestMapping(value = "/lista-todos-os-agendamentos", method = RequestMethod.GET)
	public String listaTodosOsAgendamentos(AgendamentoTransferenciaBancaria agendamento, Model model) {

		List<AgendamentoTransferenciaBancaria> agendamentos = agendamentoDao
				.todosAgendamentos(agendamento.getContaOrigem());
		model.addAttribute(agendamentos);

		return "lista-de-agendamentos";
	}

}
