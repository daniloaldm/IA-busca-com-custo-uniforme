package Topologias;

public class Lista<tipo> {
	
	private No<tipo> header;
	private No<tipo> trailer;
	
	private int tamanho;
	
	public Lista() {
		header = new No<tipo>(null, null, null);
		trailer = new No<tipo>(null, header, null);
		header.setProximo(trailer);
	}
	
	
	/* ######################### Metodos de Insercao ######################### */
	public void insereOrdenado(No<tipo> novoNo) {
		insereInicio(novoNo);
		ordenarLista();
	}
	
	public void insereInicio(No<tipo> novoNo) {
		No<tipo> primeiroNo = header.getProximo();
		
		header.setProximo(novoNo);
		
		novoNo.setAnterior(header);
		novoNo.setProximo(primeiroNo);
		
		primeiroNo.setAnterior(novoNo);
		
		tamanho++;
	}
	
	public void insereFim(No<tipo> novoNo) {
		
		trailer.getAnterior().setProximo(novoNo);
		
		novoNo.setAnterior(trailer.getAnterior());
		novoNo.setProximo(trailer);
		
		trailer.setAnterior(novoNo);
		
		tamanho++;
	}
	
	public void ordenarLista() {
		No<tipo> aux = header.getProximo();
		for(int j = 0; j < tamanho - 1; j++) {
			if(aux.getCusto() > aux.getProximo().getCusto())
				inverteNos(aux, aux.getProximo());
			else // se inverter n�o h� getProximo devido j� ter invertido as posi��es dentro da lista
				aux = aux.getProximo();
		}
	}
	public void inverteNos(No<tipo> no1, No<tipo> no2) {
		No<tipo> aux1 = no1.getAnterior();
		No<tipo> aux2 = no2.getProximo();
		
		no1.setAnterior(no2);
		no1.setProximo(aux2);
		
		no2.setAnterior(aux1);
		no2.setProximo(no1);
		
		aux1.setProximo(no2);
		aux2.setAnterior(no1);
	}
	
	public void inserePosicao(No<tipo> novoNo, int pos) {
		if(tamanho < pos)
			return;
		
		No<tipo> aux = header;
		
		for(int k = 0; k < pos; k++)
			aux = aux.getProximo();
		
		aux.getProximo().setAnterior(novoNo);
		
		novoNo.setAnterior(aux);
		novoNo.setProximo(aux.getProximo());
		
		aux.setProximo(novoNo);
		
		tamanho++;
	}
	
	/* ######################### Metodos de Remocao ######################### */
	public tipo removeInicio() {
		if(tamanho == 0) {
			System.out.println("Nao ha estados!");
			return null;
		}

		tipo valor = header.getProximo().getEstado();
		header.setProximo(header.getProximo().getProximo());
		
		header.getProximo().getAnterior().setAnterior(null);
		header.getProximo().getAnterior().setProximo(null);
		
		header.getProximo().setAnterior(header);
		
		tamanho--;
		return valor;
	}
	
	public tipo removePosicao(int pos) {
		if(tamanho < pos) {
			System.out.println("Excede ao limite!");
			return null;
		}
		
		No<tipo> aux = header;
		
		for(int k = 0; k < pos; k++)
			aux = aux.getProximo();
		
		
		tipo valor = aux.getProximo().getEstado();
		aux.setProximo(aux.getProximo().getProximo());
		
		aux.getProximo().getAnterior().setAnterior(null);
		aux.getProximo().getAnterior().setProximo(null);
		
		aux.getProximo().setAnterior(aux);
		
		tamanho--;
		return valor;
	}
	
	public tipo removeFim() {
		if(tamanho == 0) {
			System.out.println("Nao ha estados!");
			return null;
		}
		
		tipo valor = trailer.getAnterior().getEstado();
		trailer.setAnterior(trailer.getAnterior().getAnterior());
		
		trailer.getAnterior().getProximo().setAnterior(null);
		trailer.getAnterior().getProximo().setProximo(null);
		
		trailer.getAnterior().setProximo(trailer);
		
		tamanho--;
		return valor;
	}
	
	
	/* ######################### Auxiliares ######################### */
	public void mostrarLista() {
		if(tamanho == 0)
			System.out.println("Lista Vazia!\n");
		else {
			No<tipo> aux = header.getProximo();
			
			System.out.print("Lista: ");
			while(aux.getEstado() != null) {
				System.out.print(aux.getEstado() + "\t");
				aux = aux.getProximo();
			}
			
			System.out.println("\n");
		}
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
	
	
	
	// ############################## M�TODOS DA PROVA PASSADA ##############################
	public void addAntes(No<tipo> novoNo, int pos) {
		if(pos - 1 < 0) {
			System.out.println("Extrapola o limite!");
			return;
		}
		inserePosicao(novoNo, pos - 1);
	}
	
	public void addApos(No<tipo> novoNo, int pos) {
		if(tamanho <= pos + 1) {
			System.out.println("Extrapola o limite!");
			return;
		}
		inserePosicao(novoNo, pos + 1);
	}

	public tipo removeEstado(tipo estado) {
		int pos = 0;
		boolean naoExiste = true;
		No<tipo> aux = header;
		
		for(int k = 0; k < tamanho; k++) {
			aux = aux.getProximo();
			if(aux.getEstado() == estado) {
				naoExiste = false;
				pos = k;
				break;
			}
		}
		
		if(naoExiste) {
			System.out.println("N�o existe esse estado");
			return null;
		}
		
		return removePosicao(pos);
	}
	
	public tipo first() {
		if(tamanho == 0)
			return null;
		
		return header.getProximo().getEstado();
	}
	
	public tipo last() {
		if(tamanho == 0)
			return null;
		
		return trailer.getAnterior() .getEstado();
	}
	
	public tipo anterior(int pos) {
		if(pos - 1 < 0 || tamanho < pos)
			return null;
		
		No<tipo> aux = header;
		for(int k = 0; k < pos; k++)
			aux = aux.getProximo();
		
		return aux.getEstado();
	}
	
	public tipo posterior(int pos) {
		if(tamanho <= pos + 1 || tamanho <= pos)
			return null;
			
		No<tipo> aux = header;
		for(int k = 0; k < pos + 2; k++)
			aux = aux.getProximo();
		
		return aux.getEstado();
	}
	
	
	// ############################## MÉTODOS COMPLEMENTARES ##############################
		public int index(tipo estado) {
			int pos = 0;
			boolean naoExiste = true;
			No<tipo> aux = header;
			
			for(int k = 0; k < tamanho; k++) {
				aux = aux.getProximo();
				if(aux.getEstado() == estado) {
					naoExiste = false;
					pos = k;
					break;
				}
			}
			
			if(naoExiste) {
				System.out.println("N�o existe esse estado");
				return -1;
			}
			
			return pos;
		}
		
		public tipo getEstado(int pos) {
			No<tipo> aux = header;
			
			for(int k = -1; k < pos; k++)
				aux = aux.getProximo();
			
			return aux.getEstado();
		}
		
		public void setEstado(tipo estado, int pos) {
			No<tipo> aux = header;
			
			for(int k = -1; k < pos; k++)
				aux = aux.getProximo();
			
			aux.setEstado(estado);
		}
		
}
