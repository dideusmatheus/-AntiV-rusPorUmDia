package setor;

import java.util.*;


import projetoAtivirus.inimigo.inimigo;
import projetoAtivirus.jogador.jogadorSimples;
import projetoAtivirus.jogador.jogadorSuporte;
import tabuleiro.tabuleiro;
import utilidade.Par;

public class setor {
	/*
	 * Tipo de setor normal  (0) - Sem restrições
	 * Tipo de setor oculto  (1) - inimigo pode ou não ser eliminado -- deve ser aleatorio 
	 * Tipo de setor privado (2) - Funcao procurar não existe neste tipo de setor
	 */
	private int tipoSetor;
	private ArrayList<portas> listaPortas;
	private ArrayList<inimigo> listaInimigos;
	private Par localizacao;
	
	public setor() {
		gerarTipoSetor();
		
	}
	public setor(tabuleiro tab, Par localizacao) {
		gerarTipoSetor();
		this.localizacao=localizacao;
		listaPortas=new ArrayList<portas>();
		listaInimigos=new ArrayList<inimigo>();
		gerarPortas2(tab);
		
		
	}
	public setor(Par localizacao) {
		listaPortas=new ArrayList<portas>();
		listaInimigos=new ArrayList<inimigo>();
		this.localizacao=localizacao;
		gerarTipoSetor();
		listarPortas();
		
		
	}

	public ArrayList<portas> getListaPortas() {
		return listaPortas;
	}
	public void setListaPortas(ArrayList<portas> listaPortas) {
		this.listaPortas = listaPortas;
	}
	public ArrayList<inimigo> getListaInimigos() {
		return listaInimigos;
	}
	public void setListaInimigos(ArrayList<inimigo> listaInimigos) {
		this.listaInimigos = listaInimigos;
	}
	public setor(Par localizacao,int linha, int coluna) {
		listaPortas=new ArrayList<portas>();
		this.localizacao=localizacao;
		gerarTipoSetor();
		adicionarPorta(new Par(linha,coluna));
		gerarInimigos();
		listarInimigos();
	
		
		
		
	}
	public setor(tabuleiro tab,Par localizacao,int linha, int coluna) {
		listaPortas=new ArrayList<portas>();
		this.localizacao=localizacao;
		gerarTipoSetor();
		adicionarPorta(new Par(linha,coluna));
		gerarInimigos();
		
		gerarPortas2(tab);
		
		
	}
	
	public void gerarTipoSetor() {
		Random gerador=new Random();
		this.tipoSetor=gerador.nextInt(2);
	}
	public int getTipoSetor() {
		return tipoSetor;
	}

	public void setTipoSetor(int tipoSetor) {
		this.tipoSetor = tipoSetor;
	}

	public Par getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Par localizacao) {
		this.localizacao = localizacao;
	}

	public void adicionarPorta(Par porta) {
		portas novaporta=new portas(porta);
		listaPortas.add(novaporta);
	}
	public void listarPortas() {
		for (int i=0;i<listaPortas.size();i++) {
			System.out.println("Porta com setor: "+listaPortas.get(i).getConexao().getPrimeiro()+"/"+listaPortas.get(i).getConexao().getSegundo());
		}
	}
	public  void listarInimigos() {
		for (int i=0;i<listaInimigos.size();i++) {
			System.out.println("Inimigo ("+i+") "+listaInimigos.get(i).getAtaque()+"/"+listaInimigos.get(i).getDefesa());
		}
	}
	public void gerarInimigos() {
		listaInimigos=new ArrayList<inimigo>();
		Random gerador=new Random();
		int qtdInimigos=gerador.nextInt(5);
		for(int i=0;i<qtdInimigos;i++) {
			int atk=gerador.nextInt(3);
			inimigo novoInimigo= new inimigo(atk+1);
			listaInimigos.add(novoInimigo);
		}
	}
	
	
	public void  gerarPortas2(tabuleiro tab) {
		Random gerador= new Random();
		//caso dos setores que tem quatro portas
		if((localizacao.getPrimeiro()>0  && localizacao.getPrimeiro()<4) &&(  localizacao.getSegundo()>0 && localizacao.getSegundo()<4)) {
			//verificar se ja tem alguma porta
			if(!listaPortas.isEmpty()) {
				int qtdPortas=4-listaPortas.size();
				//verifico com que setor ja temos uma porta, se a com o de baixo ou cima, esquerdo ou direito
				
				//verificando com o setor a baixo
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()+1) &&listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())  ) {
					
					int probabilidade=gerador.nextInt(10)+1;
					//caso a porta da esquerda seja gerada
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()-1));//porta a esquerda
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()]="*   ";
						listaPortas.add(p1);
						
						
					}
					//caso a porta da direita seja gerada
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()+1));//porta a direita
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()+1]="*   ";
						listaPortas.add(p2);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p3=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()));//porta acima
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p3);
						
						
					}
					
					
					
					
					
				}
				//verificando com o setor a esquerda
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) &&listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()-1)){ 
					
					
					
					int probabilidade=gerador.nextInt(10)+1;
					//caso a porta da esquerda seja gerada
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro()+1,localizacao.getSegundo()));//porta a abaixo
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+2][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p1);
						
						
					}
					//caso a porta da direita seja gerada
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()+1));//porta a direita
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()+1]="*   ";
						listaPortas.add(p2);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p3=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()));//porta acima
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p3);
						
						
					}
					
				}
				//verificando com o setor a direita
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) &&listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()+1)){ 
					
					
					int probabilidade=gerador.nextInt(10)+1;
					//caso a porta da esquerda seja gerada
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro()+1,localizacao.getSegundo()));//porta a abaixo
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+2][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p1);
						
						
					}
					//caso a porta da direita seja gerada
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()-1));//porta a esquerda
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()]="*   ";
						listaPortas.add(p2);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p3=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()));//porta acima
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p3);
						
						
					}
					
				}
				//verificando com o setor a acima
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()-1) &&listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())){ 
					
				
					
					//adicionamos em um vetor auxiliar as possiveis portas para que seja embaralhados e definido aleatoriamente quais serao geradas
					int probabilidade=gerador.nextInt(10)+1;
					//caso a porta da esquerda seja gerada
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro()+1,localizacao.getSegundo()));//porta a abaixo
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+2][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p1);
						
						
					}
					//caso a porta da direita seja gerada
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()-1));//porta a esquerda
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()]="*   ";
						listaPortas.add(p2);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p3=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()+1));//porta direita
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()+1]="*   ";
						listaPortas.add(p3);
						
						
					}
					
				}
				
				
			}
		}
		//JA CORRIGIDO 
		//caso onde tem-se 3 portas, sejam elas da linha 1/coluna 1/linha 4/coluna 4
		
		//primeiro caso é se os setores estão na linha 1-- podem ter apenas portas a direita, esquerda e abaixo
		if(localizacao.getPrimeiro()==0 && (localizacao.getSegundo()>0 && localizacao.getSegundo()<4)) {
			if(!listaPortas.isEmpty()) {
				int qtdPortas=3-listaPortas.size();
				//caso ele venha da esquerda
				
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()-1)) {
					
					
					int probabilidade=gerador.nextInt(10)+1;
					//caso a porta da esquerda seja gerada
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro()+1,localizacao.getSegundo()));//porta a abaixo
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+2][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()+1));//porta a direita
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()+1]="*   ";
						listaPortas.add(p2);
						
						
					}
					
				}
				//caso ele venha da direita
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()+1)) {
					
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro()+1,localizacao.getSegundo()));//porta a abaixo
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+2][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()-1));//porta a esquerda
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()]="*   ";
						listaPortas.add(p2);
						
						
					}
					
				}
				//caso ele venha de baixo
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()+1) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())) {
					
					
					
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()+1));//porta a direita
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()+1]="*   ";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()-1));//porta a esquerda
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()]="*   ";
						listaPortas.add(p2);
						
						
					}
					
				}
			}
		}
		//segundo caso é se os setores estão na coluna 1-- podem ter apenas portas a direita, acima e abaixo
		if(localizacao.getSegundo()==0 && (localizacao.getPrimeiro()>0 && localizacao.getPrimeiro()<4)) {
			if(!listaPortas.isEmpty()) {
				int qtdPortas=3-listaPortas.size();
				//caso ele venha de acima
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()-1) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())){     
				
					
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()+1));//porta a direita
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()+1]="*   ";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro()+1,localizacao.getSegundo()));//porta a abaixo
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+2][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p2);
						
						
					}
					
				}
				//caso ele venha de baixo
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()+1) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())) {
					

					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()+1));//porta a direita
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()+1]="*   ";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()));//porta a acima
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p2);
						
						
					}
					
					}
				//caso ele venha da direita
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()+1)) {
					
					
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro()+1,localizacao.getSegundo()));//porta a abaixo
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+2][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()));//porta a acima
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p2);
						
						
					}
					
				}
			}
		}
		/// JA CORRIGIDO 
		//Terceiro caso é se os setores estão na linha 4-- podem ter apenas portas a direita, acima e abaixo
		if(localizacao.getPrimeiro()==4 && (localizacao.getSegundo()>0 && localizacao.getSegundo()<4)) {
			if(!listaPortas.isEmpty()) {
				int qtdPortas=3-listaPortas.size();
				//caso ele venha de acima
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()-1) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())){     
				
					
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()-1));//porta a esquerda
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()]="*   ";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()+1));//porta a direita
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()+1]="*   ";
						listaPortas.add(p2);
						
						
					}
					
				}
				//caso ele venha de direita
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()+1)) {
					
					
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()-1));//porta a esquerda
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()]="*   ";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()));//porta a acima
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p2);
						
						
					}
					
					}
				//caso ele venha da esquerda
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()-1)) {
				
					
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()+1));//porta a direita
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()+1]="*   ";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()));//porta a acima
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p2);
						
						
					}
					
				}
			}
		}
		//JA CORRIGIDO
		//Terceiro caso é se os setores estão na coluna 4-- podem ter apenas portas a direita, acima e abaixo
		if(localizacao.getSegundo()==4 && (localizacao.getPrimeiro()>0 && localizacao.getPrimeiro()<4)) {
			if(!listaPortas.isEmpty()) {
				int qtdPortas=3-listaPortas.size();
				//caso ele venha de acima
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()-1) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())){     
					
					
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()-1));//porta a esquerda
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()]="*   ";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro()+1,localizacao.getSegundo()));//porta a abaixo
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+2][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p2);
						
						
					}
					
				}
				//caso ele venha de baixo
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()+1) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())) {
					
				
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()-1));//porta a esquerda
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()]="*   ";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()));//porta a acima
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p2);
						
						
					}
					
					}
				//caso ele venha da esquerda
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()-1)) {
					
					
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro()+1,localizacao.getSegundo()));//porta a abaixo
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+2][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p1);
						
						
					}
					probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p2=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()));//porta a acima
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p2);
						
						
					}
					
				}
			}
		}
		
		// nas extremidades	tem-se quatro casos
		// o primeiro é na linha 0 coluna 0
		if(localizacao.getPrimeiro()==0  && localizacao.getSegundo()==0) {
			int qtdPortas=2-listaPortas.size();
			//testamos para ver se a lista de portas n esta vazia
			if(!listaPortas.isEmpty()) {
				//caso venha da direita
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()+1)){
					
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro()+1,localizacao.getSegundo()));//porta a abaixo
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+2][localizacao.getSegundo()+1]="-*-|";
						listaPortas.add(p1);
						
						
					}
				}
				//caso venha de baixo
				if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()+1) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())){
					
	
					int probabilidade=gerador.nextInt(10)+1;
					if(probabilidade>3) {
						portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()+1));//porta a direita
						tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()+1]="*   ";
						listaPortas.add(p1);
					}
				}
		
			}
		}
		// o segundo é na linha 0 coluna 4
		if(localizacao.getPrimeiro()==0  && localizacao.getSegundo()==4) {
				int qtdPortas=2-listaPortas.size();
				//testamos para ver se a lista de portas n esta vazia
				if(!listaPortas.isEmpty()) {
					//caso venha da esquerda
					if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()-1)){
						
					
						int probabilidade=gerador.nextInt(10)+1;
						if(probabilidade>3) {
							portas p1=new portas(new Par(localizacao.getPrimeiro()+1,localizacao.getSegundo()));//porta a abaixo
							tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+2][localizacao.getSegundo()+1]="-*-|";
							listaPortas.add(p1);
						}
					}
					//caso venha de baixo
					if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()+1) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())){
						
						//como devemos ter ou n mais essa porta vamos sortear um valor boolean
						int probabilidade=gerador.nextInt(10)+1;
						if(probabilidade>3) {
							portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()-1));//porta a esquerda
							tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()]="*   ";
							listaPortas.add(p1);
						}
					}
				}
			
		}
		// o terceiro é na linha 4 coluna 0
		if(localizacao.getPrimeiro()==4  && localizacao.getSegundo()==0) {
				int qtdPortas=2-listaPortas.size();
				//testamos para ver se a lista de portas n esta vazia
				if(!listaPortas.isEmpty()) {
					//caso venha da acima
					if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()-1) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())){
						
						//como devemos ter ou n mais essa porta vamos sortear um valor boolean
						int probabilidade=gerador.nextInt(10)+1;
						if(probabilidade>3) {
							portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()+1));//porta a direita
							tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()+1]="*   ";
							listaPortas.add(p1);
						}
					}
					//caso venha de direita
					if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()+1)){
						
						//como devemos ter ou n mais essa porta vamos sortear um valor boolean
						
						int probabilidade=gerador.nextInt(10)+1;
						if(probabilidade>3) {
							portas p1=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()));//porta a acima
							tab.interfaceGrafica[(localizacao.getPrimeiro()*2)][localizacao.getSegundo()+1]="-*-|";
							listaPortas.add(p1);
						}
					}
				}
			
		}
		// o quarto e ultimo caso de extremidade  é na linha 4 coluna 4
		if(localizacao.getPrimeiro()==4  && localizacao.getSegundo()==4) {
				int qtdPortas=2-listaPortas.size();
				//testamos para ver se a lista de portas n esta vazia
				if(!listaPortas.isEmpty()) {
					//caso venha da acima
					if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()-1) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo())){
						
						//como devemos ter ou n mais essa porta vamos sortear um valor boolean
						int probabilidade=gerador.nextInt(10)+1;
						if(probabilidade>3) {
							portas p1=new portas(new Par(localizacao.getPrimeiro(),localizacao.getSegundo()-1));//porta a esquerda
							tab.interfaceGrafica[(localizacao.getPrimeiro()*2)+1][localizacao.getSegundo()]="*   ";
							listaPortas.add(p1);
						}
					}
					//caso venha de esquerda
					if(listaPortas.get(0).getConexao().getPrimeiro()==(localizacao.getPrimeiro()) && listaPortas.get(0).getConexao().getSegundo()==(localizacao.getSegundo()+1)){
						
						//como devemos ter ou n mais essa porta vamos sortear um valor boolean
						int probabilidade=gerador.nextInt(10)+1;
						if(probabilidade>3) {
							portas p1=new portas(new Par(localizacao.getPrimeiro()-1,localizacao.getSegundo()));//porta a acima
							tab.interfaceGrafica[(localizacao.getPrimeiro()*2)][localizacao.getSegundo()+1]="-*-|";
							listaPortas.add(p1);
						}
					}
				}
			
		}
		//gerando as portas da posição inicial
		if(localizacao.getPrimeiro()==2  && localizacao.getSegundo()==2) {
			//adicionando porta acima
			portas porta1=new portas(new Par(1,2));
			tab.interfaceGrafica[localizacao.getPrimeiro()+2][localizacao.getSegundo()+1]="-*-|";
			//adicionando porta a esquerda
			portas porta2=new portas(new Par(2,1));
			tab.interfaceGrafica[localizacao.getPrimeiro()+3][localizacao.getSegundo()]="*   ";
			//adicionando porta a direita
			portas porta3=new portas(new Par(2,3));
			tab.interfaceGrafica[localizacao.getPrimeiro()+3][localizacao.getSegundo()+1]="*   ";
			//adicionando porta a baixo 
			portas porta4=new portas(new Par(3,2));
			tab.interfaceGrafica[localizacao.getPrimeiro()+4][localizacao.getSegundo()+1]="-*-|";
			listaPortas.add(porta1);
			listaPortas.add(porta2);
			listaPortas.add(porta3);
			listaPortas.add(porta4);
			
				
				
		}
		
	}	
}