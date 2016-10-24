package br.com.reservafacil.agendamento.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

public abstract class BaseIntegrationTest {

	private EntityManagerFactory factory;

	protected EntityManager manager;

	@Before
	public final void setUp() {
		factory = Persistence.createEntityManagerFactory(getPersistenceUnit());
		manager = factory.createEntityManager();
	}

	public String getPersistenceUnit() {
		return "integration-tests-h2";
	}

	@After
	public final void tearDown() {
		manager.close();
		factory.close();
	}

}