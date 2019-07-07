package Topologias;

public class Arvore<tipo> {

	No<tipo> raiz = new No<tipo>(null, null, 0);

	public Arvore(tipo elemento) {
		raiz.setElemento(elemento);
	}

	public No<tipo> inserirNo(tipo elemento, No<tipo> pai) {
		No<tipo> node = new No<tipo>(elemento, pai, 0);
		pai.inserir(node); // inserindo nos filhos

		return node;
	}

	public tipo getRaiz() {
		return raiz.getElemento();
	}
}
