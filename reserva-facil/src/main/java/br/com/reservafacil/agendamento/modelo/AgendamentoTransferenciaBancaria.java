package br.com.reservafacil.agendamento.modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.annotations.VisibleForTesting;

@Entity
@Table(name = "AGENDAMENTO")
public class AgendamentoTransferenciaBancaria {

	@OneToOne
	@Column(name = "CONTA_ORIGEM")
	ContaBancaria contaOrigem;

	@OneToOne
	@Column(name = "CONTA_DESTINO")
	ContaBancaria contaDestino;

	@Column(name = "VALOR_TRANSFERIDO")
	Double valorTransferido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_AGENDAMENTO")
	Calendar dataDeAgendamento;

	@Embedded
	TipoDeTransacao tipoDeTransacao;

	public AgendamentoTransferenciaBancaria() {
	}

	@VisibleForTesting
	public AgendamentoTransferenciaBancaria(ContaBancaria contaOrigem, ContaBancaria contaDestino,
			Double valorTransferido, Calendar dataDeAgendamento, TipoDeTransacao tipoDeTransacao) {
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valorTransferido = valorTransferido;
		this.dataDeAgendamento = dataDeAgendamento;
		this.tipoDeTransacao = tipoDeTransacao;
	}

	public ContaBancaria getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(ContaBancaria contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public ContaBancaria getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(ContaBancaria contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Double getValorTransferido() {
		return valorTransferido;
	}

	public Calendar getDataDeAgendamento() {
		return dataDeAgendamento;
	}

	public void setDataDeAgendamento(Calendar dataDeAgendamento) {
		this.dataDeAgendamento = dataDeAgendamento;
	}

	public TipoDeTransacao getTipoDeTransacao() {
		return tipoDeTransacao;
	}

	public void setTipoDeTransacao(TipoDeTransacao tipoDeTransacao) {
		this.tipoDeTransacao = tipoDeTransacao;
	}

	public void setValorTransferido(Double valorTransferido) {
		this.valorTransferido = valorTransferido;
	}
}
