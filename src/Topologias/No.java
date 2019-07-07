package Topologias;

import java.util.ArrayList;

public class No<tipo> {

	private tipo estado;
	//================================================================================================================================
	// Atributos
	private No<tipo> proximo;
	private No<tipo> anterior;
	private int custo = 0;

	// Construtores
	public No(tipo estado, No<tipo> proximo) {
		this.estado = estado;
		this.proximo = proximo;
	}

	public No(tipo estado, No<tipo> anterior, No<tipo> proximo) {
		this.estado = estado;
		this.anterior = anterior;
		this.proximo = proximo;
	}

	// Métodos get e set
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

	//================================================================================================================================
	// Atributos Arvore
	private No<tipo> pai = null;
	private ArrayList<No<tipo>> filhos = new ArrayList<No<tipo>>();

	// Construtor Arvore
	public No(tipo estado, No<tipo> noPai, int modelo) {
		this.estado = estado;
		this.pai = noPai;
	}

	//Métodos get e set e auxiliares
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

	public tipo getEstado() {
		return estado;
	}

	public void setEstado(tipo estado) {
		this.estado = estado;
	}
}
