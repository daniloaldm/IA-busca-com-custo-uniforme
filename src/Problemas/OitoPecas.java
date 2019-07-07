package Problemas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class OitoPecas implements Problema {
	
	Random num = new Random();
	
	// Movimentos poss�veis (definidos por index)
	int[][] mov = {{3,4},{2,3,4},{2,4},{1,3,4},{1,2,3,4},{1,2,4},{1,3},{1,2,3},{1,2}};
	// Objetivo
	int[] resposta = {0,1,2,3,4,5,6,7,8};
	
	// Vari�veis do Objeto
	public int[] tabuleiro = {0,1,2,3,4,5,6,7,8};
	public int profundidade = 0;
	public int custo = 0;
	
	// Construtores
	public OitoPecas() {
		int limite = num.nextInt(20) + 20; // Movimentos entre 20 e 40
		System.out.println("Movimentos: " + limite);
		
		for (int k = 0; k < limite; k++) {
			int index = localizacao();
			int[] mov = this.mov[index];
			int pos = num.nextInt(mov.length);
			movimentacao(mov[pos],index);
		}
	}
	public OitoPecas(int[] lista) {
		this.tabuleiro = lista;
	}
	
	// M�todos
	@Override
	public ArrayList<Problema> funcaoSucessora(int op) {
		
		ArrayList<Problema> lista = new ArrayList<Problema>();
		ArrayList<int[]> visitados = new ArrayList<int[]>();
		int index = localizacao();
		
		int[] movimentacoesLista = mov[index];
		
		// Cria��o de um ArrayList a partir da lista retornada para dar um shuffle
		ArrayList<Integer> movimentacoes = new ArrayList<Integer>();
		for (int k = 0; k < movimentacoesLista.length; k++) 
			movimentacoes.add(movimentacoesLista[k]);
		Collections.shuffle(movimentacoes); // embaralha as posicoes
		
		// Movimentos
		for (int i = 0; i < movimentacoes.size(); i++) {
			// Cria o novo objeto
			OitoPecas novoNo = new OitoPecas(copiaLista(tabuleiro));
			novoNo.profundidade = profundidade + 1;
			novoNo.custo = custo + funcaoCusto(movimentacoes.get(i),index);
			novoNo.movimentacao(movimentacoes.get(i), index);
			
			switch(op) {
			// Demais m�todos
			case 1:
				lista.add(novoNo);
				break;
			// Profundidade com Lista
			case 2:
				// Observa todos os elementos da lista de visitados
				int cont = 0;
				for (int j = 0; j < visitados.size(); j++) {
					if(novoNo.igualdade(visitados.get(j))) {
						cont++;
						break;
					}
				}
				if(cont == 0) {
					// Adiciona na lista
					lista.add(novoNo);
					// Guarda na lista de visitados
					visitados.add(copiaLista(novoNo.tabuleiro));
				}
				break;
			}
		}
		return lista;
	}
	
	@Override
	public boolean testeObjetivo() {
		int cont = 0;
		for (int i = 0; i < tabuleiro.length; i++) {
			if(tabuleiro[i] == resposta[i])
				cont++;
		}
		if(cont == 9)
			return true;
		else
			return false;
	}
	
	@Override
	public void mostrar() {
		for (int k = 0; k < 9; k++) {
			if((k+1)%3 == 1)
				System.out.println();
			System.out.print(tabuleiro[k] + " ");
		}
		System.out.println();
	}
	
	@Override
	public int getProfundidade() {
		return this.profundidade;
	}
	
	@Override
	public int getCusto() {
		return this.custo;
	}

	@Override
	public int funcaoCusto(int orientacao, int index) {
		int[][] custos = {{0,0,1,1},{0,1,1,1},{0,1,0,1},{1,0,1,1},{1,1,1,1},{1,1,0,1},{1,0,1,0},{1,1,1,0},{1,1,0,0}};
		return custos[index][orientacao-1];
	}
	
	
	
	// Metodos Auxiliares
	// Localiza o 0 no vetor
	public int localizacao() { 
		int pos = 0;
		for (int k = 0; k < 9; k++) {
			if(tabuleiro[k] == 0)
				return k;
		}
		return pos;
	}
	// Movimenta o 0 de acordo com a oriena��o passada
	public void movimentacao(int orientacao, int index) {
		int indexTroca = 0;
		if(orientacao == 1) {
			indexTroca = index - 3;
		} else if(orientacao == 2) {
			indexTroca = index - 1;
		} else if(orientacao == 3) {
			indexTroca = index + 1;
		} else if(orientacao == 4) {
			indexTroca = index + 3;
		}
		int aux = tabuleiro[index];
		tabuleiro[index] = tabuleiro[indexTroca];
		tabuleiro[indexTroca] = aux;
	}
	// Verifica igualdade com outra lista
	public boolean igualdade(int[] lista) {
		int cont = 0;
		for (int i = 0; i < tabuleiro.length; i++) {
			if(tabuleiro[i] == lista[i])
				cont++;
		}
		if(cont == 9)
			return true;
		else
			return false;
	}
	// Necessario copiar o vetor para se ter um novo enderecamento de memoria
	public int[] copiaLista(int[] lista) {
		int[] novaLista = new int[lista.length];
		for (int k = 0; k < lista.length; k++)
			novaLista[k] = lista[k];
		return novaLista;
	}
	
}
