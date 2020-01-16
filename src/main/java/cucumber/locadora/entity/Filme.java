package cucumber.locadora.entity;

import lombok.Getter;


public class Filme {
	@Getter
	private int estoque;
	@Getter
	private int aluguel;
	

	public void setEstoque(int arg1) {
		this.estoque = arg1;
	}
	public void setAluguel(int arg1) {
		this.aluguel = arg1;
	}
	
	
	
}
