package Buscas;

import java.util.ArrayList;
import Topologias.*;
import Problemas.Problema;

public class CustoUniforme implements Busca{
	Lista<No<Problema>> borda = new Lista<No<Problema>>();
	Arvore<Problema> arvore = null;
	public No<Problema> nodeFinal = null;

	// Contrutor
	public CustoUniforme(Problema no) {
		System.out.println("\n===Busca de Custo Uniforme===");
		No<Problema> node = new No<Problema>(no, null, 0);
		borda.insereOrdenado(new No<No<Problema>>(node, null, null));
		this.arvore = new Arvore<Problema>(no);
	}

	// Método de Solução
	public boolean executar(boolean mostre) {
		if (borda.getTamanho() == 0) {
			System.out.println("\nFalha na busca!");
			return false;
		}

		No<Problema> node = borda.removeInicio();
		Problema no = node.getElemento();

		if (mostre)
			no.mostrar();

		if (no.testeObjetivo()) {
			this.nodeFinal = node;
			System.out.print("\nEstatísticas da Simulação:");
			System.out.println("\nProfundidade: " + no.getProfundidade());
			return false;
		}

		// Acrescenta os nos na borda e na arvore
		ArrayList<Problema> lista = no.funcaoSucessora(1);
		for (int i = 0; i < lista.size(); i++) {
			No<Problema> novoNode = arvore.inserirNo(lista.get(i), node);
			No<No<Problema>> nodeLista = new No<No<Problema>>(novoNode, null, null);
			nodeLista.setCusto(lista.get(i).getCusto());
			borda.insereOrdenado(nodeLista);
		}

		return true;
	}

	public void resposta() {
		ArrayList<Problema> resposta = new ArrayList<Problema>();
		No<Problema> node = nodeFinal;

		if (node != null) {
			System.out.println("Custo total: " + node.getElemento().getCusto());

			while (node != null) {
				resposta.add(node.getElemento());
				node = node.getPai();
			}

			System.out.print("\nSequência de Ações:");
			for (int i = resposta.size() - 1; 0 <= i; i--)
				resposta.get(i).mostrar();
		}
	}
}
