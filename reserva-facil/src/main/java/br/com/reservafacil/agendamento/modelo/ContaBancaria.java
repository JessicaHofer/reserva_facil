package br.com.reservafacil.agendamento.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.annotations.VisibleForTesting;

@Entity
@Table(name = "CONTA_BANCARIA")
public class ContaBancaria {

	public ContaBancaria() {
	}

	@VisibleForTesting
	public ContaBancaria(String numeroDaConta, Double saldo) {
		this.numeroDaConta = numeroDaConta;
		this.saldo = saldo;
	}

	@Id
	@Column(name = "ID_CONTA")
	private Long id;

	@Column(name = "NUMERO")
	private String numeroDaConta;

	@Column(name = "SALDO")
	private Double saldo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	boolean isNumeroDaContaNoPadrao() {

		if (this.numeroDaConta.contains("-")) {
			String[] partes = this.numeroDaConta.split("-");
			String numero = partes[0];
			String digito = partes[1];

			if (numero.length() < 5) {
				return false;
			}

			if (digito.length() != 1) {
				return false;
			}

		} else {
			return false;
		}

		return true;
	}
}
