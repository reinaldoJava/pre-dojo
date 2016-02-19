package br.com.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
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
			listInfomacoes.add(informacoes);
		}

		return listInfomacoes;
	}

	public static void main(String[] args) throws FileNotFoundException {
		ControllerRanking controllerRanking = new ControllerRanking();
		List<Informacoes> info = controllerRanking
				.retornarListaInformacoes(controllerRanking
						.gerarMapaDados(new FileReader("arquivo.log")));

		Map<String, Integer> dadosUsuarios = new ControllerRanking()
				.rankingUsuarios(info);
		List<Integer> sortedKeys = new ArrayList<Integer>(
				dadosUsuarios.values());
		Collections.reverse(sortedKeys);
		for (Integer x : sortedKeys) {
			System.out.println(x);
		}
	}

	public Map<String, Integer> rankingUsuarios(List<Informacoes> list) {
		Map<String, Integer> dadosUsuarios = new HashMap<String, Integer>();
		int index = 1;
		Informacoes usuarioAnterior = null;
		Informacoes usuarioAtual = null;
		
		for (Informacoes informacoes : list) {
				usuarioAnterior = informacoes;
				Integer quant = usuarioAnterior.getQuantidade();
				String nomeUsuarioAnterior = usuarioAnterior.getUsuario(); 
				if(dadosUsuarios.isEmpty()){
					usuarioAnterior.setQuantidade(index);
					dadosUsuarios.put(nomeUsuarioAnterior, usuarioAnterior.getQuantidade());
					usuarioAnterior = usuarioAtual;
				}else if (usuarioAnterior.equals(usuarioAtual)){
					usuarioAnterior.setQuantidade(quant+index);
					index++;
				}else if(dadosUsuarios.containsKey(nomeUsuarioAnterior)){
					usuarioAnterior.setQuantidade(quant+index);
					dadosUsuarios.put(nomeUsuarioAnterior, usuarioAnterior.getQuantidade());
				}
				else{
					dadosUsuarios.put(nomeUsuarioAnterior, quant);
					usuarioAnterior = usuarioAtual;
				}
			}
		
		
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//		for (Informacoes info : list) {
//			usuarioAtual = info.getUsuario();
//			if (usuarioAnterior.equalsIgnoreCase(usuarioAtual)) {
//				index++;
//				continue;
//			} else if (dadosUsuarios.containsKey(usuarioAtual)) {
//				Integer tempIndex = dadosUsuarios.get(usuarioAtual);
//				dadosUsuarios.put(usuarioAtual, ++tempIndex);
//
//				if (dadosUsuarios.containsKey(usuarioAnterior)) {
//					Integer tempIndex2 = dadosUsuarios.get(usuarioAnterior);
//					dadosUsuarios.put(usuarioAnterior, ++tempIndex2);
//				} else {
//					dadosUsuarios.put(usuarioAnterior, index);
//				}
//				usuarioAnterior = usuarioAtual;
//				index = 1;
//				continue;
//			}
//			dadosUsuarios.put(usuarioAtual, index);
//			usuarioAnterior = usuarioAtual;
//			index = 1;
//		}

		return dadosUsuarios;
	}

}
