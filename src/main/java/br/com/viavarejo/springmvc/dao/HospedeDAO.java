package br.com.viavarejo.springmvc.dao;

import java.util.List;

import br.com.viavarejo.springmvc.model.Hospede;

public interface HospedeDAO {
	
	Hospede recuperaPorId(long id);
	
	Hospede recuperaPorNome(String nome);
	
	void criar(Hospede hospede);
	
	void atualizar(Hospede hospede);
	
	void deletaPeloId(long id);

	List<Hospede> listar(); 
	
	boolean isExistente(Hospede hospede);

}
