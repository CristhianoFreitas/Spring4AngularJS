package br.com.viavarejo.springmvc.service;

import java.util.List;

import br.com.viavarejo.springmvc.model.Hospede;

public interface HospedeService {

	Hospede recuperaPorId(long id);

	Hospede recuperaPorNome(String nome);

	void criar(Hospede hospede);

	void atualizar(Hospede hospede);

	void deletaPorId(long id);

	List<Hospede> listar();

	boolean isExistente(Hospede hospede);

}
