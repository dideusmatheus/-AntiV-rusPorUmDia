package projetoAtivirus.inimigo;
import java.util.Random;

import projetoAtivirus.jogador.jogadorSimples;
import projetoAtivirus.jogador.jogadorSuporte;

import java.util.ArrayList;
public class inimigo {
	private int ataque;
	private int defesa;
	private Random gerador;
	
	public inimigo(int valor) {
		this.ataque=valor;
		this.defesa=valor;
		this.gerador=new Random();
		
	}
	public boolean atacar(jogadorSimples jogador1) {
		
		int defesaJogador;
		int num=gerador.nextInt(6);
			if(num % 2 ==0)
				jogador1.setDefesa(jogador1.getDefesa()-this.ataque);
				defesaJogador=jogador1.getDefesa();
				if(defesaJogador<=0) {
					System.out.println("Você foi morto, GAME OVER!!!");
					return true;
					
				}
			
				
				return false;
	}
	public boolean atacar(jogadorSuporte jogador2) {
		
		int defesaJogador;
		int num=gerador.nextInt(6);
			if(num % 2 ==0)
				jogador2.setDefesa(jogador2.getDefesa()-this.ataque);
				defesaJogador=jogador2.getDefesa();
				if(defesaJogador<=0) {
					System.out.println("Você foi morto, GAME OVER!!!");
					return true;
					//System.exit(1);
				}
				return false;
			
		
	}
	public Random getGerador() {
		return gerador;
	}
	public void setGerador(Random gerador) {
		this.gerador = gerador;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String inimigo=this.ataque+"/"+this.defesa;
		return inimigo;
	}
	

}
