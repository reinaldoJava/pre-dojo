package br.com.teste;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
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
		Map<String, Integer> rankingList = new HashMap<String, Integer>();
		rankingList.put("Roman", 19);
		rankingList.put("<WORLD>", 9);
		rankingList.put("Z99ptrz99", 7);
		rankingList.put("Nick", 4);
		rankingList.put("Natan", 5);
		FileReader log = new FileReader("arquivo.log");
		ControllerRanking controllerRanking = new ControllerRanking();
		List<Informacoes> list = controllerRanking.retornarListaInformacoes(controllerRanking.gerarMapaDados(log));
		assertEquals(rankingList, controllerRanking.gerarRankingUsuarios(list));
	}
	@Test
	public void armaPreferidaVencedorTest() throws FileNotFoundException {

		assertEquals("M16", new ControllerRanking().armaPreferidaVencedor("Natan"));
	}
	
}
