package Topologias;

import java.util.ArrayList;

public class No<tipo> {

	private tipo elemento;

	// Atributos Pilha, Fila e Lista
	private No<tipo> proximo;
	private No<tipo> anterior;
	private int custo = 0;

	// Construtor Pilha e Fila
	public No(tipo elemento, No<tipo> proximo) {
		this.elemento = elemento;
		this.proximo = proximo;
	}

	public No(tipo elemento, No<tipo> anterior, No<tipo> proximo) {
		this.elemento = elemento;
		this.anterior = anterior;
		this.proximo = proximo;
	}

	// M�todos
	public No<tipo> getProximo() {
		return proximo;
	}

	public void setProximo(No<tipo> proximo) {
		this.proximo = proximo;
	}

	public No<tipo> getAnterior() {
		return anterior;
	}

	public void setAnterior(No<tipo> anterior) {
		this.anterior = anterior;
	}

	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}

	// Atributos Arvore
	private No<tipo> pai = null;
	private ArrayList<No<tipo>> filhos = new ArrayList<No<tipo>>();

	// Construtor Arvore
	public No(tipo elemento, No<tipo> noPai, int modelo) {
		this.elemento = elemento;
		this.pai = noPai;
	}

	// M�todos
	public No<tipo> getPai() {
		return this.pai;
	}

	public int getQtdFilhos() {
		return this.filhos.size();
	}

	public void inserir(No<tipo> filho) {
		this.filhos.add(filho);
	}

	public No<tipo> no(int pos) {
		return this.filhos.get(pos);
	}

	public tipo getElemento() {
		return elemento;
	}

	public void setElemento(tipo elemento) {
		this.elemento = elemento;
	}
}
