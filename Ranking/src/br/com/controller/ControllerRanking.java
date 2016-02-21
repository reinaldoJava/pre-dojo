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
			if (info.startsWith("New match") || info.endsWith("has ended"))
				continue;
			dados.put(data, info);
		}
		return dados;
	}

	public List<Informacoes> retornarListaInformacoes(Map<String, String> mapa) {
		List<Informacoes> listInfomacoes = new ArrayList<Informacoes>();
		for (String dados : mapa.values()) {
			String[] tempArray = dados.split(" ");
			if (tempArray[2].equalsIgnoreCase("<WORLD>")) {
				continue;
			}
			Informacoes informacoes = new Informacoes();
			informacoes.setUsuario(tempArray[0]);
			informacoes.setMorte(tempArray[1]);
			informacoes.setOutroUsuario(tempArray[2]);
			informacoes.setArma(tempArray[3]);
			informacoes.setArma(tempArray[4]);
			listInfomacoes.add(informacoes);
		}
		return listInfomacoes;
	}

	public Map<String, Integer> gerarRankingUsuarios(List<Informacoes> list) {
		Map<String, Integer> dadosUsuarios = new HashMap<String, Integer>();
		Integer index = 0;
		for (int i = 0; i < list.size(); i++) {	
			index = 0;
			for (int j = 0; j < list.size(); j++)  {
				if(list.get(i).getUsuario().equalsIgnoreCase(list.get(j).getUsuario())){
					index++;
				}
			}
			if(index > 0){
				if(dadosUsuarios.containsKey(list.get(i).getUsuario())){
					continue;
				}
				dadosUsuarios.put(list.get(i).getUsuario(), index);		
			}			
		}
		//Ordena mapa em ordem do Maior para o menor
		
		return dadosUsuarios;
	}

	public String armaPreferidaVencedor(String nome) throws FileNotFoundException {
		Integer anterior = 0;
		Integer atual = 0;
		String armaPreferida = "";

		Map<String, String> dados = gerarMapaDados(new FileReader("arquivo.log"));
		retornarListaInformacoes(dados);
		List<Informacoes> listaTemp = new ArrayList<Informacoes>();
		//Lista Virá do metodo retornarListaInformacoes
		List<Informacoes> listInfomacoes = null;
		for (Informacoes informacoes : listInfomacoes) {	
			if(informacoes.getUsuario().equalsIgnoreCase("Natan")){
				listaTemp.add(informacoes);
			}
		}	
		for (Informacoes informacoes2 : listaTemp) {
			atual = 0;
			String tempArma = informacoes2.getArma();
			for (Informacoes informacoes : listaTemp) {
				if(tempArma.equalsIgnoreCase(informacoes.getArma())){
					atual++;
				}
			}
			if(atual > anterior){
				anterior = atual;
				armaPreferida = tempArma;
			}
		}
		return armaPreferida;
	}
}
