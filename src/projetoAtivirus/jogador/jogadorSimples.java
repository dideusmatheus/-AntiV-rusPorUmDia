package projetoAtivirus.jogador;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import projetoAtivirus.inimigo.inimigo;
import setor.setor;
import tabuleiro.tabuleiro;
import utilidade.Par;

public class jogadorSimples {
	private int ataque;
	private int defesa;
	protected Par localizacaoAtual;
	private Scanner leitura;
	
	public Par getLocalizacaoAtual() {
		return localizacaoAtual;
	}
	public void setLocalizacaoAtual(Par localizacaoAtual) {
		this.localizacaoAtual = localizacaoAtual;
	}
	public jogadorSimples() {
		
		this.ataque = 2;
		this.defesa = 6;
		leitura=new Scanner(System.in);
	}
	public int getAtaque() {
		return ataque;
	}
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	public int getDefesa() {
		return defesa;
	}
	public void setDefesa(int defesa) {
		this.defesa = defesa;
	}
	public void movimentar(tabuleiro tabuleiro) {
		char opcao;
		int linha=0,coluna=0;
		boolean portaIndisponivel=true;
		
		do {
			System.out.println("Deseja se movimetar para?\nW - Para Cima\nA - Para esquerda\nD - Para direita\nS - Para baixo ");
			opcao=leitura.next().charAt(0);
			switch(opcao) {
				case 'w':
					//teremos dois casos, o primeiro é se o setor nunca foi visitado e teremos gerar o novas portas e inimigos. O segundo se trata de um setor que ja foi visitado
					//caso 1-- primeiro verificamos se tem porta para onde o jogador deseja ir 
					for(int i=0;i<tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().size();i++) {
						//caso tenha a porta para cima
						if(tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getPrimeiro()==localizacaoAtual.getPrimeiro()-1 && tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getSegundo()==localizacaoAtual.getSegundo()) {    
							//sysout(" "+tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getPrimeiro()+" "+);
							linha=tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getPrimeiro();
							coluna=tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getSegundo();
							portaIndisponivel=false;
						}
					
					}
					//devemos verificar se o setor já nunca foi visitado
					 
					if(tabuleiro.tabuleiro[linha][coluna]==null &&  portaIndisponivel==false) {
						  
						//iniciamos o setor e geramos os inimigos
						tabuleiro.tabuleiro[linha][coluna]=new setor(tabuleiro,new Par(linha,coluna),localizacaoAtual.getPrimeiro(),localizacaoAtual.getSegundo());
						//atualizar a localizacao atual de nosso jogador
						this.localizacaoAtual.setPrimeiro(linha);
						this.localizacaoAtual.setSegundo(coluna);
					}
					//caso o setor ja tenha visitado
					else if(portaIndisponivel==false && tabuleiro.tabuleiro[linha][coluna]!=null){
						
						//atualizamos a localizacao atual de nosso jogador
						this.localizacaoAtual.setPrimeiro(linha);
						this.localizacaoAtual.setSegundo(coluna);
						
					}
					break;
				case 's':
					//teremos dois casos, o primeiro é se o setor nunca foi visitado e teremos gerar o novas portas e inimigos. O segundo se trata de um setor que ja foi visitado
					//caso 1-- primeiro verificamos se tem porta para onde o jogador deseja ir 
					for(int i=0;i<tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().size();i++) {
						//caso tenha a porta para cima
						if(tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getPrimeiro()==localizacaoAtual.getPrimeiro()+1 && tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getSegundo()==localizacaoAtual.getSegundo()) {    
							linha=tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getPrimeiro();
							linha=tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getPrimeiro();
							coluna=tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getSegundo();
							portaIndisponivel=false;
						}
					
					}
					//devemos verificar se o já nunca foi visitado
					 
					if(tabuleiro.tabuleiro[linha][coluna]==null &&  portaIndisponivel==false) {
						  
						//iniciamos o setor e geramos os inimigos
						tabuleiro.tabuleiro[linha][coluna]=new setor(tabuleiro,new Par(linha,coluna),localizacaoAtual.getPrimeiro(),localizacaoAtual.getSegundo());
						//atualizar a localizacao atual de nosso jogador
						this.localizacaoAtual.setPrimeiro(linha);
						this.localizacaoAtual.setSegundo(coluna);
					}
					//caso o setor ja tenha visitado
					else if(portaIndisponivel==false && tabuleiro.tabuleiro[linha][coluna]!=null){
					
						//atualizamos a localizacao atual de nosso jogador
						this.localizacaoAtual.setPrimeiro(linha);
						this.localizacaoAtual.setSegundo(coluna);
						
					}
					break;
				case 'a':
					//teremos dois casos, o primeiro é se o setor nunca foi visitado e teremos gerar o novas portas e inimigos. O segundo se trata de um setor que ja foi visitado
					//caso 1-- primeiro verificamos se tem porta para onde o jogador deseja ir 
					for(int i=0;i<tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().size();i++) {
						//caso tenha a porta para cima
						if(tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getPrimeiro()==localizacaoAtual.getPrimeiro() && tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getSegundo()==localizacaoAtual.getSegundo()-1) {    
							linha=tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getPrimeiro();
							coluna=tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getSegundo();
							portaIndisponivel=false;
						}
					
					}
					//devemos verificar se o já nunca foi visitado
					 
					if(tabuleiro.tabuleiro[linha][coluna]==null &&  portaIndisponivel==false) {
						  
						//iniciamos o setor e geramos os inimigos
						tabuleiro.tabuleiro[linha][coluna]=new setor(tabuleiro,new Par(linha,coluna),localizacaoAtual.getPrimeiro(),localizacaoAtual.getSegundo());
						//atualizar a localizacao atual de nosso jogador
						this.localizacaoAtual.setPrimeiro(linha);
						this.localizacaoAtual.setSegundo(coluna);
					}
					//caso o setor ja tenha visitado
					else if(portaIndisponivel==false && tabuleiro.tabuleiro[linha][coluna]!=null){
						
						//atualizamos a localizacao atual de nosso jogador
						this.localizacaoAtual.setPrimeiro(linha);
						this.localizacaoAtual.setSegundo(coluna);
						
					}
					break;
				case 'd':
					//teremos dois casos, o primeiro é se o setor nunca foi visitado e teremos gerar o novas portas e inimigos. O segundo se trata de um setor que ja foi visitado
					//caso 1-- primeiro verificamos se tem porta para onde o jogador deseja ir 
					for(int i=0;i<tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().size();i++) {
						//caso tenha a porta para cima
						if(tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getPrimeiro()==localizacaoAtual.getPrimeiro() && tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getSegundo()==localizacaoAtual.getSegundo()+1) {    
							linha=tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getPrimeiro();
							coluna=tabuleiro.tabuleiro[localizacaoAtual.getPrimeiro()][localizacaoAtual.getSegundo()].getListaPortas().get(i).getConexao().getSegundo();
							portaIndisponivel=false;
						}
					
					}
					//devemos verificar se o já nunca foi visitado
					 
					if(tabuleiro.tabuleiro[linha][coluna]==null &&  portaIndisponivel==false) {
						  
						//iniciamos o setor e geramos os inimigos
						tabuleiro.tabuleiro[linha][coluna]=new setor(tabuleiro,new Par(linha,coluna),localizacaoAtual.getPrimeiro(),localizacaoAtual.getSegundo());
						//atualizar a localizacao atual de nosso jogador
						this.localizacaoAtual.setPrimeiro(linha);
						this.localizacaoAtual.setSegundo(coluna);
					}
					//caso o setor ja tenha visitado
					else if(portaIndisponivel==false && tabuleiro.tabuleiro[linha][coluna]!=null){
						
						//atualizamos a localizacao atual de nosso jogador
						this.localizacaoAtual.setPrimeiro(linha);
						this.localizacaoAtual.setSegundo(coluna);
						
					}
					
					break;
				default:
						System.out.println("opcao invalida");
						break;
						
				
			}
		}while(portaIndisponivel);
		
		
	}
	public void atacar(int tipoSetor, ArrayList<inimigo> listaInimigos, int inimigo) {
		int defesainimigo;
		if(tipoSetor==0 || tipoSetor==2) {
			if(!listaInimigos.isEmpty()) {
				listaInimigos.get(inimigo).setDefesa(listaInimigos.get(inimigo).getDefesa()-getAtaque());
				defesainimigo=listaInimigos.get(inimigo).getDefesa();
				if(defesainimigo<=0) {
					System.out.println("Inimigo ("+inimigo+") Eliminado");
					listaInimigos.remove(inimigo);
				}
			}
				
		}else {
			//se o setor foi oculto tem-se a probabilidade de 50% de um ataque pegar
			Random gerador=new Random();
			
			if(!listaInimigos.isEmpty()) {
				float prob=gerador.nextFloat();
				if(prob>0.5){
					System.out.println("inimigo("+inimigo+") "+listaInimigos.get(inimigo).getAtaque()+"/"+listaInimigos.get(inimigo).getDefesa());
					
					listaInimigos.get(inimigo).setDefesa(listaInimigos.get(inimigo).getDefesa()-this.getAtaque());
					defesainimigo=listaInimigos.get(inimigo).getDefesa();
					if(defesainimigo<=0) {
						System.out.println("Inimigo ("+inimigo+") Eliminado");
						listaInimigos.remove(inimigo);
					}
					
					
				}
				else{
					System.out.println("tipo setor: "+tipoSetor);
					System.out.println("Missing ataque");
				}
			}
		}
		
		
	}
	public void procurar(int tipoSetor, ArrayList<inimigo> listaInimigos) {
		
		if(tipoSetor!=2) {
			Random gerador=new Random();
			int num=gerador.nextInt(6)+1;
			if(num>0 && num<=3) {
				System.out.println("Nenhum item encontrado");
			}else if(num==4) {
				System.out.println("Voce ganho +1 de defesa");
				setDefesa(getDefesa()+1);
			}else if(num==5) {
				System.out.println("Voce ganho +2 de defesa");
				setDefesa(getDefesa()+2);
			}else if(num==6) {
				System.out.println("todos inimigos perderam 1 de defesa");
				for(int i=0;i<listaInimigos.size();i++) {
				
					listaInimigos.get(i).setDefesa(listaInimigos.get(i).getDefesa()-1);
					int qtdDefesa=listaInimigos.get(i).getDefesa();
					if(qtdDefesa<=0) {
						listaInimigos.remove(i);
					}
				}
			}
			
		}
		else {
			System.out.println("Não e possivel utilizar esta magia");
		}
	}
	public void opcoesdeJogadas(tabuleiro tab) {
		int quantidade=0;
		int opcao=0;
		leitura = new Scanner(System.in);
		do {
			tab.displayJogo();
			
			if(!tab.tabuleiro[this.localizacaoAtual.getPrimeiro()][this.localizacaoAtual.getSegundo()].getListaInimigos().isEmpty()) {
				System.out.println("\nPlayer 1---------->\nSelecione uma opcao\n1- Atacar\n2-Procurar");
				
				
			}else {
				System.out.println("\nPlayer 1---------->\nSelecione uma opcao\n1- Atacar\n2-Procurar\n3- Movimentar");
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
	@Override
	
	public String toString() {
		// TODO Auto-generated method stub
		String jogador=this.ataque+"/"+this.defesa;
		return jogador;
	}
	public void Vitoria(Par localizacaoFonte) {
		if(this.getLocalizacaoAtual().getPrimeiro()==localizacaoFonte.getPrimeiro() && this.getLocalizacaoAtual().getSegundo()==localizacaoFonte.getSegundo()) {
			System.out.println("Parabéns você destruiu a fonte de infecção, você é o vencedor!");
			System.exit(1);
		}
	}
	
	
	
}
