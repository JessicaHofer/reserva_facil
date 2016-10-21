package br.com.reservafacil.agendamento.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.annotations.VisibleForTesting;

@Embeddable
public class TipoDeTransacao {

	@Column(name = "TIPO_TRANSACAO")
	private String tipo;

	@Column(name = "TAXA_TRANSACAO")
	private Double taxa;

	public TipoDeTransacao() {
	}

	@VisibleForTesting
	public TipoDeTransacao(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
}
