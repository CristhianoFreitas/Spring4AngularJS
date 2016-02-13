package br.com.viavarejo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viavarejo.springmvc.dao.HospedeDAO;
import br.com.viavarejo.springmvc.model.Hospede;

@Service("hospedeService")
@Transactional
public class HospedeServiceImpl implements HospedeService {

	@Autowired
	private HospedeDAO dao;

	public List<Hospede> listar() {
		return dao.listar();
	}

	public Hospede recuperaPorId(long id) {
		return dao.recuperaPorId(id);
	}

	public Hospede recuperaPorNome(String nome) {
		return dao.recuperaPorNome(nome);
	}

	public void criar(Hospede hospede) {
		dao.criar(hospede);
	}

	public void atualizar(Hospede hospede) {
		dao.atualizar(hospede);
	}

	public void deletaPorId(long id) {
		dao.deletaPeloId(id);
	}

	public boolean isExistente(Hospede hospede) {
		return recuperaPorNome(hospede.getNome()) != null;
	}

}
