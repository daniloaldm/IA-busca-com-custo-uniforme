package Problemas;

import java.util.ArrayList;
import java.util.Collections;

public class OitoRainhas implements Problema {
	
	int[][] tabuleiro = new int[8][8];
	int qtdRainhas = 0;
	int profundidade = 0;
	int custo = 0;
	
	ArrayList<Integer> bloqI = new ArrayList<Integer>();
	ArrayList<Integer> bloqJ = new ArrayList<Integer>();
	
	// Construtores
	public OitoRainhas() {

	}
	public OitoRainhas(int[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	
	
	@Override
	public ArrayList<Problema> funcaoSucessora(int op) {
		ArrayList<Problema> lista = new ArrayList<Problema>();
		ArrayList<int[][]> visitados = new ArrayList<int[][]>();
		
		ArrayList<Integer> possibilidades = possibilidades();
		Collections.shuffle(possibilidades); // embaralha as posicoes
				
		// Jogadas Poss�veis
		for (int i = 0; i < possibilidades.size(); i++) {
			int linha = linha(possibilidades.get(i));
			int coluna = possibilidades.get(i);
			if(linha == 9 )
				break; // sem alternativas
			// Cria o novo objeto
			OitoRainhas novoNo = new OitoRainhas(copiaMatriz(tabuleiro));
			novoNo.qtdRainhas = qtdRainhas;
			novoNo.profundidade = profundidade + 1;
			novoNo.custo = custo + funcaoCusto(linha,coluna);
			novoNo.inserirRainha(linha,coluna);
			
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
					visitados.add(copiaMatriz(novoNo.tabuleiro));
				}
				break;
			}
		}
		return lista;
	}
	
	@Override
	public boolean testeObjetivo() {
		if(this.qtdRainhas == 8)
			return true;
		return false;
	}

	@Override
	public void mostrar() {
		System.out.println();
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				int x;
				if(tabuleiro[i][j] == 9)
					x = 1;
				else
					x = 0;
				System.out.print(x + "  ");
			}
			System.out.println();
		}
		
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
	public int funcaoCusto(int linha, int coluna) {
		int[][] custos = {{2,2,2,1,2,2,2,2},
						  {1,1,1,1,1,1,1,1},
						  {1,1,1,1,1,1,1,1},
						  {1,1,1,1,1,1,1,1},
						  {1,1,1,1,1,1,1,1},
						  {1,1,1,1,1,1,1,1},
						  {1,1,1,1,1,1,1,1},
						  {1,1,1,1,1,1,1,1}};
		return custos[linha][coluna];
	}
	
	
	
	// Metodos Auxiliares
	ArrayList<Integer> possibilidades() {
		ArrayList<Integer> colunasPossiveis = new ArrayList<Integer>();
		for (int i = 0; i < tabuleiro.length; i++) {
			int cont = 0;
			for (int j = 0; j < tabuleiro.length; j++) {
				if(tabuleiro[j][i] == 9)
					cont++;
			}
			if(cont == 0)
				colunasPossiveis.add(i);
		}
		return colunasPossiveis;
	}
	
	// Identifica uma linha livre
	int linha(int coluna) {
		for (int i = 0; i < tabuleiro.length; i++) {
			if(tabuleiro[i][coluna] == 0)
				return i;
		}
		return 9;
	}
	
	void inserirRainha(int linha, int coluna) {
		qtdRainhas++;
		// Preenche Coluna
		for (int i = 0; i < tabuleiro.length; i++) {
			tabuleiro[i][coluna] = qtdRainhas;
		}
		// Preenche Linha
		for (int i = 0; i < tabuleiro.length; i++) {
			tabuleiro[linha][i] = qtdRainhas;
		}
		// Preenche Diagonal Principal
		int linhaAJD = linha; // Linha ajustada para uma posicao mais no topo
		int colunaAJD = coluna; // Coluna ajustada
		while (linhaAJD != 0 && colunaAJD != 0)  {
			linhaAJD--;
			colunaAJD--;
		}
		for (int i = 0; i < tabuleiro.length; i++) {
			if((linhaAJD+i) == 8 || (colunaAJD+i) == 8)
				break;
			tabuleiro[linhaAJD+i][colunaAJD+i] = qtdRainhas;
		}
		// Preenche Diagonal Secund�ria
		linhaAJD = linha; // Linha ajustada para uma posicao mais no topo
		colunaAJD = coluna; // Coluna ajustada
		while (linhaAJD != 0 && colunaAJD != 7)  {
			linhaAJD--;
			colunaAJD++;
		}
		for (int i = 0; i < tabuleiro.length; i++) {
			if((linhaAJD+i) == 8 || (colunaAJD-i) == -1)
				break;
			tabuleiro[linhaAJD+i][colunaAJD-i] = qtdRainhas;
		}
		
		tabuleiro[linha][coluna] = 9;
	}

	// Verifica igualdade com outra lista
	public boolean igualdade(int[][] lista) {
		int cont = 0;
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				if(tabuleiro[i] == lista[i])
					cont++;
			}
		}
		if(cont == 64)
			return true;
		else
			return false;
	}
	// Necessario copiar o vetor para se ter um novo enderecamento de memoria
	public int[][] copiaMatriz(int[][] lista) {
		int[][] novaLista = new int[lista.length][lista.length];
		for (int i = 0; i < lista.length; i++) {
			for (int j = 0; j < lista.length; j++) {
				novaLista[i][j] = lista[i][j];
			}
		}
		return novaLista;
	}
	
	
}
