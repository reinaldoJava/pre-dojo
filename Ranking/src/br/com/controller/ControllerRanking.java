package br.com.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

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

	public SortedSet<Entry<String, List<Integer>>> gerarRankingUsuarios(List<Informacoes> list) {
		SortedMap<String, List<Integer>> dadosUsuarios = new TreeMap<String, List<Integer>>();
	
		for (int i = 0; i < list.size(); i++) {	
			Integer	index = 0;
			Integer morte = 0;
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			for (int j = 0; j < list.size(); j++)  {
				if(list.get(i).getUsuario().equalsIgnoreCase(list.get(j).getUsuario())){
					index++;
				}
				if(list.get(i).getUsuario().equalsIgnoreCase(list.get(j).getOutroUsuario())){
					morte++;
				}
			}
			if(index >= 0 && morte >= 0){
				if(dadosUsuarios.containsKey(list.get(i).getUsuario())){
					continue;
				}
				arrayList.add(index);
				arrayList.add(morte);
				dadosUsuarios.put(list.get(i).getUsuario(), arrayList);		
			}			
		}
		//Ordena mapa em ordem do Maior para o menor
		SortedSet<Entry<String, List<Integer>>> retorno =  ordenaListaPorQtdAssasinato(dadosUsuarios);
		return retorno;
	}
	
	private SortedSet<Entry<String, List<Integer>>> ordenaListaPorQtdAssasinato(SortedMap<String, List<Integer>> map){
		
		SortedSet<Map.Entry<String, List<Integer>>> sortedset = new TreeSet<Map.Entry<String, List<Integer>>>(
	            new Comparator<Map.Entry<String, List<Integer>>>() {
	                @Override
	                public int compare(Map.Entry<String, List<Integer>> e1,
	                        Map.Entry<String, List<Integer>> e2) {
	                    return e2.getValue().get(0).compareTo(e1.getValue().get(0));
	                }
	            });
		sortedset.addAll(map.entrySet());
	  return sortedset;
	}

	public String IndentificarArmaPreferida(String nome, List<Informacoes> listInfomacoes) {
		Integer anterior = 0;
		Integer atual = 0;
		String armaPreferida = "";
		for (Informacoes informacoes : listInfomacoes) {
			
			if(informacoes.getUsuario().equalsIgnoreCase(nome)){
				String arma = informacoes.getArma();
				for (Informacoes informacoes2 : listInfomacoes) {
					if(arma.equalsIgnoreCase(informacoes2.getArma())){
						atual++;
						if(anterior < atual){
							atual = anterior;
							armaPreferida = informacoes2.getArma();
						}
						armaPreferida = arma;
					}
				}
			}
		}
		return armaPreferida;
	}

	
}
