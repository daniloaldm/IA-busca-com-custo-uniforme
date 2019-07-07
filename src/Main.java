import java.util.Scanner;
import Buscas.*;
import Problemas.*;

public class Main {

	private static Scanner in;

	public static void main(String[] args) {

		in = new Scanner(System.in);
		Problema problemSol = null;
		Busca solucao = null;
		System.out.println("=====================");
		System.out.println("1 - Oito Peças");
		System.out.println("2 - Oita Rainhas");
		System.out.print("Escolha o problema: ");
		int problema = in.nextInt();
		boolean opcao = true;

		while (opcao) {
			System.out.println("=====================");
			System.out.println("[0]Finalizar programa");
			System.out.println("[1]Custo Uniforme");
			System.out.print("Qual busca deseja utilizar?");
			int busca = in.nextInt();
			System.out.println();

			// problemas
			if (problema == 1) {
				int[] t = { 1, 4, 2,
							3, 0, 5,
							6, 7, 8 };
				problemSol = new OitoPecas(t);
				System.out.println("problema das Oito Peças");
				System.out.print("\nProblema:");
				problemSol.mostrar();
			} else if (problema == 2) {
				problemSol = new OitoRainhas();
				System.out.print("Oito Rainhas");
				problemSol.mostrar();
			}

			// Buscas
			if (busca == 0) {
				opcao = false;
			} else if (busca == 1) {
				solucao = new CustoUniforme(problemSol);
			}

			// Solução
			long inicio = System.currentTimeMillis();
			int cont = 0;
			while (solucao.executar(false)) {
				cont++;
			}
			long fim = System.currentTimeMillis();
			System.out.println("Tempo: " + (fim - inicio) / 1000.0 + " segundos");
			System.out.println("Passos: " + cont);
			solucao.resposta();
		}

	}

}
