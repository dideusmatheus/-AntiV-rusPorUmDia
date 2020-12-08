package gameStart;



import projetoAtivirus.jogador.jogadorSimples;
import projetoAtivirus.jogador.jogadorSuporte;
import setor.setor;
import tabuleiro.tabuleiro;
import utilidade.Par;

public class jogar {
	jogadorSimples jogador1;
	jogadorSuporte jogador2;
	tabuleiro tabuleiro;
	public int quantidadeTurnos;
	public int qtdJogador;
	public boolean p1Morto,p2Morto;

	public jogar(int qtdJogador) {
		this.tabuleiro = new tabuleiro();

		this.qtdJogador = qtdJogador;
		this.quantidadeTurnos = 25;
		if (this.qtdJogador == 1) {

			this.jogador1 = new jogadorSimples();
			
			this.jogador1.setLocalizacaoAtual(new Par(2, 2));
		
			
			
			this.tabuleiro.tabuleiro[this.jogador1.getLocalizacaoAtual().getPrimeiro()][this.jogador1.getLocalizacaoAtual().getSegundo()] = new setor(this.tabuleiro,new Par(this.jogador1.getLocalizacaoAtual().getPrimeiro(),this.jogador1.getLocalizacaoAtual().getSegundo()));

		

			for (int i = 0; i < quantidadeTurnos; i++) {
				System.out.println("Turno: " + (i + 1));
				this.mostrarSetor();
				jogador1.opcoesdeJogadas(this.tabuleiro);
				if (!this.tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
						.getLocalizacaoAtual().getSegundo()].getListaInimigos().isEmpty()) {
					int qtdInimigos = this.tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
							.getLocalizacaoAtual().getSegundo()].getListaInimigos().size();
					System.out.println("Inimigos ao Ataque");
					for (int j = 0; j < qtdInimigos; j++) {
						boolean jogadorMorto = this.tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual()
								.getPrimeiro()][jogador1.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(j)
										.atacar(jogador1);
						if (jogadorMorto) {
							System.exit(1);
						}
					}
				}

			}
			System.out.println("Gamer Over!!");
		}
		//AQUI JOGADOR DE 2
		if(this.qtdJogador==2){
			this.jogador1 = new jogadorSimples();
			this.jogador2=new jogadorSuporte();
			this.jogador1.setLocalizacaoAtual(new Par(2, 2));
			this.jogador2.setLocalizacaoAtual(new Par(2, 2));
			this.p1Morto=false;
			this.p2Morto=false;
			//setamos que os jogadores estão no setor inicial
			this.tabuleiro.tabuleiro[this.jogador1.getLocalizacaoAtual().getPrimeiro()][this.jogador1.getLocalizacaoAtual().getSegundo()] = new setor(this.tabuleiro,new Par(this.jogador1.getLocalizacaoAtual().getPrimeiro(),this.jogador1.getLocalizacaoAtual().getSegundo()));
			//o jogo sempre tem o tempo maximo de 25 turnos 
			for(int i =0;i<quantidadeTurnos;i++) {
				//teremos 3 fluxos, caso 1:  2 jogadores vivos, caso 2: jogador 1 vivo e jogador 2 morto, caso 3: jogador 1 morto e jogador 2 vivo 
				//Fluxo 1
				System.out.println("Turno: " + (i + 1));
				if(!this.p1Morto && !this.p2Morto) {
					
					this.mostrarSetor();
					//turno jogador 1
					jogador1.opcoesdeJogadas(this.tabuleiro);
					//turno jogador 2
					jogador2.opcoesdeJogadas(this.tabuleiro, this.jogador1);
					//turno inimigos
					//caso tenha inimigos no setor
					if (!this.tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1.getLocalizacaoAtual().getSegundo()].getListaInimigos().isEmpty()) {
						//pegamos a quantidade de inimigos
						int qtdInimigos = this.tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1.getLocalizacaoAtual().getSegundo()].getListaInimigos().size();
						//inimigos vao atacar priro p1
						for (int j = 0; j < qtdInimigos; j++) {
							this.p1Morto=this.tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual()
									.getPrimeiro()][jogador1.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(j)
											.atacar(jogador1);
						}
						//inimigos atacam p2
						for (int j = 0; j < qtdInimigos; j++) {
							this.p2Morto=this.tabuleiro.tabuleiro[jogador2.getLocalizacaoAtual()
									.getPrimeiro()][jogador2.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(j)
											.atacar(jogador2);
						}
					}
				}
				//caso so p1 tenha sobrevivido
				if(!this.p1Morto && this.p2Morto) {
					this.qtdJogador=1;
					this.mostrarSetor();
					//turno jogador 1
					jogador1.opcoesdeJogadas(this.tabuleiro);
					//caso tenha inimigos no setor
					if (!this.tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1.getLocalizacaoAtual().getSegundo()].getListaInimigos().isEmpty()) {
						//pegamos a quantidade de inimigos
						int qtdInimigos = this.tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1.getLocalizacaoAtual().getSegundo()].getListaInimigos().size();
						//inimigos vao atacar priro p1
						for (int j = 0; j < qtdInimigos; j++) {
							this.p1Morto=this.tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual()
									.getPrimeiro()][jogador1.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(j)
											.atacar(jogador1);
						}
					}
				}
				//caso so p2 tenha sobrevivido
				if(this.p1Morto && !this.p2Morto) {
					this.qtdJogador=1;
					this.mostrarSetor();
					//turno jogador 2
					jogador2.opcoesdeJogadas(this.tabuleiro, this.jogador1);
					//caso tenha inimigos no setor
					if (!this.tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1.getLocalizacaoAtual().getSegundo()].getListaInimigos().isEmpty()) {
						//pegamos a quantidade de inimigos
						int qtdInimigos = this.tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1.getLocalizacaoAtual().getSegundo()].getListaInimigos().size();
							for (int j = 0; j < qtdInimigos; j++) {
								this.p2Morto=this.tabuleiro.tabuleiro[jogador2.getLocalizacaoAtual()
										.getPrimeiro()][jogador2.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(j)
												.atacar(jogador2);
							}
					}
				}
				if(this.p1Morto && this.p2Morto) {
					System.out.println("GAME OVER!");
					System.exit(1);
				}
				
			}
			System.out.println("Gamer Over!!");
		}//end jogando de 2

	}
	public String prepararPrint(jogadorSimples jogador1) {
		int qtdInimigo = 0;
		String setor = null;
		
			

			qtdInimigo = tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
					.getLocalizacaoAtual().getSegundo()].getListaInimigos().size();
			if (qtdInimigo == 0) {
				setor="Setor [" + jogador1.getLocalizacaoAtual().getPrimeiro() + ","
						+ jogador1.getLocalizacaoAtual().getSegundo() + "]\n"+"|------*------|\n"+"|             |\n"+"|             |\n"+"*             *\n"+"|P1           |\n"+"|" + jogador1.toString() + "          |\n"+"|------*------|";
				return setor;
			} 
			else if (qtdInimigo <= 4) {
				String vazio = "";
				String inimigos = "|";
				String inimigos2 = "|";
				String fimString = " |";
				switch (qtdInimigo) {
				case 1:
					vazio = "         ";
					for (int i = 0; i < qtdInimigo; i++) {
						inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
								.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
					}
					inimigos += fimString + "\n|             |";
					break;
				case 2:
					vazio = "     ";
					for (int i = 0; i < qtdInimigo; i++) {
						inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
								.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
					}
					inimigos += fimString + "\n|             |";
					break;
				case 3:
					vazio = " ";
					for (int i = 0; i < qtdInimigo; i++) {
						inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
								.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
					}
					inimigos += fimString + "\n|             |";
					break;
				case 4:
					vazio = " ";
					for (int i = 0; i < 3; i++) {
						inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
								.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
					}
					inimigos += fimString + "\n";
					for (int i = 3; i < 4; i++) {
						inimigos2 += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
								.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
					}
					inimigos2 += "         |";
					inimigos += inimigos2;
					break;
				case 5:
					vazio = " ";
					for (int i = 0; i < 3; i++) {
						inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
								.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
					}
					inimigos += fimString + "\n";
					for (int i = 3; i < 4; i++) {
						inimigos2 += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
								.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
					}
					inimigos2 += "      |";
					inimigos += inimigos2;
					break;

				}
				setor="Setor [" + jogador1.getLocalizacaoAtual().getPrimeiro() + ","
						+ jogador1.getLocalizacaoAtual().getSegundo() + "]\n"+"|------*------|\n"+inimigos+"\n*             *\n"+"|P1           |\n"+"|" + jogador1.toString() + "          |\n"+"|------*------|";
				
			

		}
		return setor;

	}
	public String prepararPrint(jogadorSuporte jogador1) {
		int qtdInimigo = 0;
		String setor = null;
		
			qtdInimigo = tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1.getLocalizacaoAtual().getSegundo()].getListaInimigos().size();
	
				
				if (qtdInimigo == 0) {
					setor="Setor [" + jogador1.getLocalizacaoAtual().getPrimeiro() + ","
							+ jogador1.getLocalizacaoAtual().getSegundo() + "]\n"+"|------*------|\n"+"|             |\n"+"|             |\n"+"*             *\n"+"|P2           |\n"+"|" + jogador1.toString() + "          |\n"+"|------*------|";
					return setor;
				} 
				else if (qtdInimigo <= 4) {
					String vazio = "";
					String inimigos = "|";
					String inimigos2 = "|";
					String fimString = " |";
					switch (qtdInimigo) {
					case 1:
						vazio = "         ";
						for (int i = 0; i < qtdInimigo; i++) {
							inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
									.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
						}
						inimigos += fimString + "\n|             |";
						break;
					case 2:
						vazio = "     ";
						for (int i = 0; i < qtdInimigo; i++) {
							inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
									.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
						}
						inimigos += fimString + "\n|             |";
						break;
					case 3:
						vazio = " ";
						for (int i = 0; i < qtdInimigo; i++) {
							inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
									.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
						}
						inimigos += fimString + "\n|             |";
						break;
					case 4:
						vazio = " ";
						for (int i = 0; i < 3; i++) {
							inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
									.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
						}
						inimigos += fimString + "\n";
						for (int i = 3; i < 4; i++) {
							inimigos2 += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
									.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
						}
						inimigos2 += "         |";
						inimigos += inimigos2;
						break;
					case 5:
						vazio = " ";
						for (int i = 0; i < 3; i++) {
							inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
									.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
						}
						inimigos += fimString + "\n";
						for (int i = 3; i < 4; i++) {
							inimigos2 += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
									.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
						}
						inimigos2 += "      |";
						inimigos += inimigos2;
						break;
	
					}
					setor="Setor [" + jogador1.getLocalizacaoAtual().getPrimeiro() + ","
							+ jogador1.getLocalizacaoAtual().getSegundo() + "]\n"+"|------*------|\n"+inimigos+"\n*             *\n"+"|P2           |\n"+"|" + jogador1.toString() + "          |\n"+"|------*------|";
					
				
	
			}
			return setor;
			

	}

	public void mostrarSetor() {
		String printFinal;
		if(this.qtdJogador==1) {
			printFinal=prepararPrint(this.jogador1);
			System.out.println(printFinal);
			
		}
		else if(this.jogador1.getLocalizacaoAtual().getPrimeiro()==this.jogador2.getLocalizacaoAtual().getPrimeiro() && this.jogador1.getLocalizacaoAtual().getSegundo()==this.jogador2.getLocalizacaoAtual().getSegundo()) {
			int qtdInimigo = 0;
			String setor = null;
			
				qtdInimigo = tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1.getLocalizacaoAtual().getSegundo()].getListaInimigos().size();
		
					
					if (qtdInimigo == 0) {
						setor="Setor [" + jogador1.getLocalizacaoAtual().getPrimeiro() + ","
								+ jogador1.getLocalizacaoAtual().getSegundo() + "]\n"+"|------*------|\n"+"|             |\n"+"|             |\n"+"*             *\n"+"|P1    P2     |\n"+"|" + jogador1.toString() + "    "+jogador2.toString()+"   |\n"+"|------*------|";
					
					} 
					else if (qtdInimigo <= 4) {
						String vazio = "";
						String inimigos = "|";
						String inimigos2 = "|";
						String fimString = " |";
						switch (qtdInimigo) {
						case 1:
							vazio = "         ";
							for (int i = 0; i < qtdInimigo; i++) {
								inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
										.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
							}
							inimigos += fimString + "\n|             |";
							break;
						case 2:
							vazio = "     ";
							for (int i = 0; i < qtdInimigo; i++) {
								inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
										.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
							}
							inimigos += fimString + "\n|             |";
							break;
						case 3:
							vazio = " ";
							for (int i = 0; i < qtdInimigo; i++) {
								inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
										.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
							}
							inimigos += fimString + "\n|             |";
							break;
						case 4:
							vazio = " ";
							for (int i = 0; i < 3; i++) {
								inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
										.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
							}
							inimigos += fimString + "\n";
							for (int i = 3; i < 4; i++) {
								inimigos2 += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
										.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
							}
							inimigos2 += "         |";
							inimigos += inimigos2;
							break;
						case 5:
							vazio = " ";
							for (int i = 0; i < 3; i++) {
								inimigos += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
										.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
							}
							inimigos += fimString + "\n";
							for (int i = 3; i < 4; i++) {
								inimigos2 += tabuleiro.tabuleiro[jogador1.getLocalizacaoAtual().getPrimeiro()][jogador1
										.getLocalizacaoAtual().getSegundo()].getListaInimigos().get(i).toString() + vazio;
							}
							inimigos2 += "      |";
							inimigos += inimigos2;
							break;
		
						}
						setor="Setor [" + jogador1.getLocalizacaoAtual().getPrimeiro() + ","
								+ jogador1.getLocalizacaoAtual().getSegundo() + "]\n"+"|------*------|\n"+inimigos+"\n*             *\n"+"|P1    P2     |\n"+"|" + jogador1.toString() + "    "+jogador2.toString()+"   |\n"+"|------*------|";
						
						
		
				}
				System.out.println(setor);
		}
		else if(this.p1Morto==true && this.qtdJogador==1) {
			printFinal=prepararPrint(this.jogador1)+"\n"+prepararPrint(this.jogador2);
			System.out.println(printFinal);
		}
		else {
			
			printFinal=prepararPrint(this.jogador1)+"\n"+prepararPrint(this.jogador2);
			System.out.println(printFinal);
		}
		
	}
}
