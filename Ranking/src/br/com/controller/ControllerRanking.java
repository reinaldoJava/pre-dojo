package br.com.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.com.model.Informacoes;

public class ControllerRanking {

	
	
	
	public Map<String, String> gerarMapaDados(FileReader log)
			throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(log).useDelimiter("[-] |\\n");
		Map<String, String> dados = new HashMap<String, String>();

		while (scanner.hasNext()) {
			String data = scanner.next();
			String info = scanner.next();
			dados.put(data, info);
		}
		return dados;
	}

	public List<Informacoes> retornarListaInformacoes(Map<String, String> mapa) {
		List<Informacoes> listInfomacoes = new ArrayList<Informacoes>();
		for (String dados : mapa.values()) {
			String[] tempArray = dados.split(" ");
			Informacoes informacoes = new Informacoes();
			informacoes.setUsuario(tempArray[0]);
			informacoes.setMorte(tempArray[1]);
			informacoes.setOutroUsuario(tempArray[2]);
			informacoes.setArma(tempArray[3]);
			listInfomacoes.add(informacoes);

		}
		return listInfomacoes;
	}

}
