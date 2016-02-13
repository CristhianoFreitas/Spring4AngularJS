package br.com.viavarejo.springmvc.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import br.com.viavarejo.springmvc.model.Hospede;

/**
 * Classe que simula um cliente chamando através do Rest o servico de hospedes
 *
 */
public class HospedeCliente {

	private static final String APLICACAO = "Spring4AngularJS/";

	private String dominio;

	public HospedeCliente(String dominio) {
		this.dominio = dominio;
	}
	
	public void criar(Hospede hospede) {
		String url = this.dominio + APLICACAO + "hospede/";

		RestTemplate rest = new RestTemplate();
		rest.postForObject(url, hospede, Hospede.class);
	}

	public List<Hospede> listar() {
		String url = this.dominio + APLICACAO + "hospede/";

		RestTemplate rest = new RestTemplate();
		Hospede[] hospedes = rest.getForObject(url, Hospede[].class);

		return Arrays.asList(hospedes);
	}

	public Hospede recuperar(long id) {
		String url = this.dominio + APLICACAO + "hospede/" + id;

		RestTemplate rest = new RestTemplate();
		Hospede hospede = rest.getForObject(url, Hospede.class);

		return hospede;
	}

	public static void main(String[] args) {
		HospedeCliente cliente = new HospedeCliente("http://localhost:8080/");
		
		Hospede hospede1 = new Hospede("Hospede Um", "Endereco Um", "hospede@um.com", "123456789-01");
		Hospede hospede2 = new Hospede("Hospede Dois", "Endereco Dois", "hospede@dois.com", "123456789-02");
		
		cliente.criar(hospede1);
		cliente.criar(hospede2);

		System.out.println("Listando todos os hospedes cadastrados:");
		for (Hospede hospede : cliente.listar()) {
			System.out.println(hospede.toString());
		}

		System.out.println("\nExibindo hospede de id '1':");
		System.out.println(cliente.recuperar(1).toString());
	}

}
