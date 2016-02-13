package br.com.viavarejo.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.viavarejo.springmvc.model.Hospede;

@Component
public class HospedeDAOImpl implements HospedeDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void criar(Hospede hospede) {
		em.persist(hospede);
	}

	@Transactional
	public void atualizar(Hospede hospede) {
		em.merge(hospede);
	}

	public void deletaPeloId(long id) {
		Hospede hos = recuperaPorId(id);
		if (hos != null) {
			em.remove(hos);
		}
	}

	public Hospede recuperaPorId(long id) {
		return em.find(Hospede.class, id);
	}

	public Hospede recuperaPorNome(String name) {
		Query query = em.createQuery("SELECT e FROM Hospede e where e.nome = :nome");
		query.setParameter("nome", name);
		return (Hospede) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Hospede> listar() {
		Query query = em.createQuery("SELECT e FROM Hospede e");
		return (List<Hospede>) query.getResultList();
	}

	@Transactional
	public boolean isExistente(Hospede hospede) {
		return em.contains(hospede);
	}

}
