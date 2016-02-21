package br.com.teste;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import br.com.controller.ControllerRanking;
import br.com.model.Informacoes;

public class TesteRanking {

	@Test
	public void gerarMapaDadosTeste() throws Exception {
		ControllerRanking controllerRanking = new ControllerRanking();
		FileReader log = new FileReader("arquivo.log");
		assertEquals(48, controllerRanking.gerarMapaDados(log).size());
	}
	@Test
	public void retornarListaInformacoesTeste() throws Exception {
		ControllerRanking controllerRanking = new ControllerRanking();
		FileReader log = new FileReader("arquivo.log");
		assertEquals(44, controllerRanking.retornarListaInformacoes(controllerRanking.gerarMapaDados(log)).size());
	}
	@Test
	public void rankingUsuariosTest() throws FileNotFoundException {
		Map<String, List<Integer>> rankingList = new LinkedHashMap<String, List<Integer>>();
		 ArrayList<Integer> arrayList = new ArrayList<Integer>();
		 arrayList.add(19);
		 arrayList.add(1);
		 ArrayList<Integer> arrayList1 = new ArrayList<Integer>();
		 arrayList1.add(9);
		 arrayList1.add(0);
		 ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
		 arrayList2.add(7);
		 arrayList2.add(3);
		 ArrayList<Integer> arrayList3 = new ArrayList<Integer>();
		 arrayList3.add(4);
		 arrayList3.add(40);
		 ArrayList<Integer> arrayList4 = new ArrayList<Integer>();
		 arrayList4.add(5);
		 arrayList4.add(0);
		 
		rankingList.put("Roman",arrayList);
		rankingList.put("<WORLD>", arrayList1);
		rankingList.put("Z99ptrz99", arrayList2);
		rankingList.put("Natan", arrayList4);
		rankingList.put("Nick", arrayList3);
		FileReader log = new FileReader("arquivo.log");
		ControllerRanking controllerRanking = new ControllerRanking();
		List<Informacoes> list = controllerRanking.retornarListaInformacoes(controllerRanking.gerarMapaDados(log));
		assertEquals(rankingList, controllerRanking.gerarRankingUsuarios(list));
	}
	@Test
	public void armaPreferidaVencedorTest() throws FileNotFoundException {
		ControllerRanking controllerRanking = new ControllerRanking();
		FileReader log = new FileReader("arquivo.log");
		List<Informacoes> list = controllerRanking.retornarListaInformacoes(controllerRanking.gerarMapaDados(log));
		assertEquals("M16", controllerRanking.IndentificarArmaPreferida("Natan", list));
	}
	
}
