package br.com.reservafacil.agendamento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.reservafacil.agendamento.modelo.AgendamentoTransferenciaBancaria;
import br.com.reservafacil.agendamento.modelo.TipoDeTransacao;

@Controller
public class AgendamentoTransferenciaBancariaController {

	@RequestMapping(value = "/realizaAgendamento", method = RequestMethod.GET)
	public String realizaAgendamento(AgendamentoTransferenciaBancaria agendamento) {

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

		return "tela-de-confirmacao";
	}

	@RequestMapping(value = "/calcula-valor-com-taxa-do-tipo-B", method = RequestMethod.GET)
	public String calculaValorComTaxaDoTipoB(AgendamentoTransferenciaBancaria agendamento) {

		// TODO

		return "tela-de-confirmacao";
	}

	@RequestMapping(value = "/calcula-valor-com-taxa-do-tipo-C", method = RequestMethod.GET)
	public String calculaValorComTaxaDoTipoC(AgendamentoTransferenciaBancaria agendamento) {

		// TODO

		return "tela-de-confirmacao";
	}

	@RequestMapping(value = "/calcula-valor-com-taxa-do-tipo-D", method = RequestMethod.GET)
	public String calculaValorComTaxaDoTipoD(AgendamentoTransferenciaBancaria agendamento) {

		// TODO

		return "tela-de-confirmacao";
	}

}
