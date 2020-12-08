package projetoAtivirus.jogador;

import java.util.Scanner;

import tabuleiro.tabuleiro;

public class jogadorSuporte extends jogadorSimples implements habilidadesEspeciais{

	private Scanner leitura;
	public jogadorSuporte() {
		this.setAtaque(1);
		this.setDefesa(7);
	}

	@Override
	public void curarDefesa(int opcao,jogadorSimples jogador) {
		if(opcao==1 && jogador.getLocalizacaoAtual().getPrimeiro()==this.localizacaoAtual.getPrimeiro() && jogador.getLocalizacaoAtual().getSegundo()==this.localizacaoAtual.getSegundo()) {
			jogador.setDefesa(jogador.getDefesa()+2);
		}
		else if(opcao==0){
			this.setDefesa(this.getDefesa()+2);
		}else {
			System.out.println("Impossivel curar player 1, voces nao estao no mesmo setor");
		}
		
	}

	public void opcoesdeJogadas(tabuleiro tab,jogadorSimples jogador) {
		int quantidade=0;
		int opcao=0;
		leitura = new Scanner(System.in);
		do {
			tab.displayJogo();
			
			if(!tab.tabuleiro[this.localizacaoAtual.getPrimeiro()][this.localizacaoAtual.getSegundo()].getListaInimigos().isEmpty()) {
				System.out.println("\nPlayer 2---------->\nSelecione uma opcao\n1- Atacar\n2-Procurar\n3- Curar defesa");
				
				
			}else {
				System.out.println("\nPlayer 2---------->\nSelecione uma opcao\n1- Atacar\n2-Procurar\n3- Curar defesa\n4- Movimentar");
			}
			
			opcao=leitura.nextInt();
			switch(opcao) {
				case 1:
					int qtdInimigo=tab.tabuleiro[this.localizacaoAtual.getPrimeiro()][this.localizacaoAtual.getSegundo()].getListaInimigos().size();
					if(qtdInimigo>0) {
						System.out.println("Você deseja atacar?");
						tab.tabuleiro[this.localizacaoAtual.getPrimeiro()][this.localizacaoAtual.getSegundo()].listarInimigos();
						this.atacar(tab.tabuleiro[this.localizacaoAtual.getPrimeiro()][this.localizacaoAtual.getSegundo()].getTipoSetor(), tab.tabuleiro[this.localizacaoAtual.getPrimeiro()][this.localizacaoAtual.getSegundo()].getListaInimigos(), leitura.nextInt());
					}else {
						System.out.println("Não tem inimigos nesse setor");
					}
					
					break;
				case 2:
					this.procurar(tab.tabuleiro[this.localizacaoAtual.getPrimeiro()][this.localizacaoAtual.getSegundo()].getTipoSetor(), tab.tabuleiro[this.localizacaoAtual.getPrimeiro()][this.localizacaoAtual.getSegundo()].getListaInimigos());
					break;
				case 3:
					System.out.println("Deseja curar o Player 1 ou a voce mesmo?\n Selecione: \n0- Para voce mesmo\n1 - Player 1");
					int  cura=leitura.nextInt();
					this.curarDefesa(cura, jogador);
					break;
				case 4:
					if(tab.tabuleiro[this.localizacaoAtual.getPrimeiro()][this.localizacaoAtual.getSegundo()].getListaInimigos().isEmpty()) {
						this.movimentar(tab);
						this.Vitoria(tab.getLocalizacaoFontedeVirus());
					}else {
						System.out.println("Você não pode se movimentar, ainda restam inimigos no setor");
					}
				
					break;
			}
			
			quantidade++;
			
		}while(quantidade<2);
	}

}
