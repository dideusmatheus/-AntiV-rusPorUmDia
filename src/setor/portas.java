package setor;

import utilidade.Par;

public class portas {
	//porta com o setor
	 private Par conexao;

	public Par getConexao() {
		return conexao;
	}

	public portas(Par conexao) {
		
		this.conexao = conexao;
	}

	public void setConexao(Par conexao) {
		this.conexao = conexao;
	}
	 
}
