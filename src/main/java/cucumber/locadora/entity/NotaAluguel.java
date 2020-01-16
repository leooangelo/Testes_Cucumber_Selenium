package cucumber.locadora.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaAluguel {
	
	private int preço;
	
	private Date dataEntrega;
	
	private int pontuacao;
	
	

}
