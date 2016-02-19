package br.com.teste;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.util.List;

import org.junit.Test;

import br.com.controller.ControllerRanking;
import br.com.model.Informacoes;

public class TesteRanking {

	@Test
	public void gerarMapaDadosTeste() throws Exception {
		ControllerRanking controllerRanking = new ControllerRanking();
		FileReader log = new FileReader("arquivo.log");
		assertEquals(50, controllerRanking.gerarMapaDados(log).size());
	}
	@Test
	public void retornarListaInformacoesTeste() throws Exception {
		ControllerRanking controllerRanking = new ControllerRanking();
		List<Informacoes> lista = null;
		FileReader log = new FileReader("arquivo.log");
		assertEquals(50, controllerRanking.retornarListaInformacoes(controllerRanking.gerarMapaDados(log)).size());
	}
	@Test
	public void rankingUsuarios() {
		// TODO Auto-generated method stub

	}
	
}
