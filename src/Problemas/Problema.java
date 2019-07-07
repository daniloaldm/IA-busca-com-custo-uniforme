package Problemas;

import java.util.ArrayList;

public interface Problema {
	public ArrayList<Problema> funcaoSucessora(int op);
	public boolean testeObjetivo();
	public int funcaoCusto(int a, int b);
	
	public void mostrar();
	public int getProfundidade();
	public int getCusto();
}
