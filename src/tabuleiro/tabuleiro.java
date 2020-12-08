package tabuleiro;

import java.util.Random;

import setor.setor;
import utilidade.Par;

public class tabuleiro {
	public setor[][] tabuleiro;
	private Par localizacaoFontedeVirus;
	public Par getLocalizacaoFontedeVirus() {
		return localizacaoFontedeVirus;
	}
	public void setLocalizacaoFontedeVirus(Par localizacaoFontedeVirus) {
		this.localizacaoFontedeVirus = localizacaoFontedeVirus;
	}
	public String[][] interfaceGrafica;
	public tabuleiro() {
		this.tabuleiro= new setor[5][5];
		this.interfaceGrafica=new String[11][6];
		this.criarMatriz();
		Random gerador=new Random();
		int linha=0,coluna=0;
		do {
			linha=gerador.nextInt(4);
			coluna=gerador.nextInt(4);
		}while(linha==2 &&  coluna==2);
		localizacaoFontedeVirus=new Par(linha, coluna);
		
		
	}
	public void criarMatriz() {
		
		for(int i =0;i<11;i++) {
			for(int j=0;j<6;j++) {
				if(i<11 && j==0) {
					this.interfaceGrafica[i][j]="|";
				}
				if(i % 2==0 &&  j>0) {
					this.interfaceGrafica[i][j]="---|";
				}
				if(i % 2 != 0) {
					this.interfaceGrafica[i][j]="|   ";
					
				}
				
			}
			
		}
	
	}
	public void displayJogo() {
		for(int i =0;i<11;i++) {
			System.out.println();
			for(int j=0;j<6;j++) {
				System.out.print(this.interfaceGrafica[i][j]);
			}
		}
	}
}
