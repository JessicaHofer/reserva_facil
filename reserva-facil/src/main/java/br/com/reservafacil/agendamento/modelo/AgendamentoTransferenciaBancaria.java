package br.com.reservafacil.agendamento.modelo;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.annotations.VisibleForTesting;

@Entity
@Table(name = "AGENDAMENTO")
public class AgendamentoTransferenciaBancaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "CONTA_ORIGEM_ID")
	ContaBancaria contaOrigem;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "CONTA_DESTINO_ID")
	ContaBancaria contaDestino;

	@Column(name = "VALOR_TRANSFERIDO")
	Double valorTransferido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO")
	Calendar dataDeCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_AGENDAMENTO")
	Calendar dataDeAgendamento;

	@Embedded
	TipoDeTransacao tipoDeTransacao;

	public AgendamentoTransferenciaBancaria() {
	}

	@VisibleForTesting
	public AgendamentoTransferenciaBancaria(ContaBancaria contaOrigem, ContaBancaria contaDestino,
			Double valorTransferido, Calendar dataDeCadastro, Calendar dataDeAgendamento,
			TipoDeTransacao tipoDeTransacao) {
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valorTransferido = valorTransferido;
		this.dataDeCadastro = dataDeCadastro;
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

	public void setValorTransferido(Double valorTransferido) {
		this.valorTransferido = valorTransferido;
	}

	public Calendar getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Calendar dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
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
}
