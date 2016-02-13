package br.com.viavarejo.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.viavarejo.springmvc.model.Hospede;
import br.com.viavarejo.springmvc.service.HospedeService;

@RestController
public class HospedeRestController {

	@Autowired
	HospedeService hospedeService;

	/*
	 *  Acessado atraves do GET http://localhost:8080/Spring4AngularJS/hospede/1 por exemplo
	 */
	@RequestMapping(value = "/hospede/", method = RequestMethod.GET)
	public ResponseEntity<List<Hospede>> listAllHospedes() {
		List<Hospede> hospedes = hospedeService.listar();
		if (hospedes.isEmpty()) {
			return new ResponseEntity<List<Hospede>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Hospede>>(hospedes, HttpStatus.OK);
	}

	/*
	 * Acessado atraves do GET http://localhost:8080/Spring4AngularJS/hospede/1 por exemplo
	 */
	@RequestMapping(value = "/hospede/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hospede> getHospede(@PathVariable("id") long id) {
		Hospede hospede = hospedeService.recuperaPorId(id);
		if (hospede == null) {
			return new ResponseEntity<Hospede>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Hospede>(hospede, HttpStatus.OK);
	}

	@RequestMapping(value = "/hospede/", method = RequestMethod.POST)
	public ResponseEntity<Void> createHospede(@RequestBody Hospede hospede, UriComponentsBuilder ucBuilder) {
		hospedeService.criar(hospede);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/hospede/{id}").buildAndExpand(hospede.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/hospede/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Hospede> updateHospede(@PathVariable("id") long id, @RequestBody Hospede hospede) {
		Hospede hospedeCorrente = hospedeService.recuperaPorId(id);

		if (hospedeCorrente == null) {
			return new ResponseEntity<Hospede>(HttpStatus.NOT_FOUND);
		}

		hospedeCorrente.setNome(hospede.getNome());
		hospedeCorrente.setEndereco(hospede.getEndereco());
		hospedeCorrente.setEmail(hospede.getEmail());
		hospedeCorrente.setCpf(hospede.getCpf());

		hospedeService.atualizar(hospedeCorrente);
		return new ResponseEntity<Hospede>(hospedeCorrente, HttpStatus.OK);
	}

	@RequestMapping(value = "/hospede/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Hospede> deleteHospede(@PathVariable("id") long id) {
		Hospede hospede = hospedeService.recuperaPorId(id);
		if (hospede == null) {
			return new ResponseEntity<Hospede>(HttpStatus.NOT_FOUND);
		}

		hospedeService.deletaPorId(id);
		return new ResponseEntity<Hospede>(HttpStatus.NO_CONTENT);
	}

}