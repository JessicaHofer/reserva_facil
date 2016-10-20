package br.com.reservafacil.agendamento.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTA_BANCARIA")
public class ContaBancaria {

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
}
