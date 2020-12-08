import java.util.Scanner;

import gameStart.jogar;
import projetoAtivirus.jogador.jogadorSimples;
import setor.setor;
import tabuleiro.tabuleiro;
import utilidade.Par;

public class main {

	public static void main(String[] args) {
			System.out.println("Bem Vindo ao 1 Antivirus por dia\n Selecione\n 1- jogar de 1\n 2 - jogar de 2");
			Scanner leitura=new Scanner(System.in);
			jogar game1=new jogar(leitura.nextInt());

			
	}

}
