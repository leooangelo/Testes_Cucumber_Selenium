package cucumber.locadora.steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import cucumber.locadora.entity.Filme;
import cucumber.locadora.entity.NotaAluguel;
import cucumber.locadora.entity.TipoAluguel;
import cucumber.locadora.services.AluguelService;
import cucumber.locadora.utils.DateUtils;
import junit.framework.Assert;

public class LocadoraSteps {
	private Filme filme;
	private AluguelService aluguelService = new AluguelService();
	private NotaAluguel notaAluguel;
	private String erro;
	private TipoAluguel tipoAluguel;

	@Dado("^um filme com estoque de (\\d+) unidades$")
	public void umFilmeComEstoqueDeUnidades(int arg1) throws Throwable {
		filme = new Filme();
		filme.setEstoque(arg1);
	}

	@Dado("^que o preço de aluguel seja R\\$ (\\d+)$")
	public void queOPreçoDeAluguelSejaR$(int arg1) throws Throwable {
		filme.setAluguel(arg1);
	}

	@Dado("^um filme$")
	public void umFilme(DataTable table) throws Throwable {
		Map<String, String> map = table.asMap(String.class, String.class);
		filme = new Filme();
		filme.setEstoque(Integer.parseInt(map.get("estoque")));
		filme.setAluguel(Integer.parseInt(map.get("preço")));
		String tipo = map.get("tipo");
		tipoAluguel = tipo.equals("semanal") ? TipoAluguel.SEMANAL
				: tipo.equals("extendido") ? TipoAluguel.EXTENDIDO : TipoAluguel.COMUM;
	}

	@Quando("^alugar$")
	public void alugar() throws Throwable {
		try {
			notaAluguel = aluguelService.alugar(filme, tipoAluguel);
		} catch (RuntimeException e) {
			erro = e.getMessage();
		}
	}

	@Então("^o preço do aluguel será R\\$ (\\d+)$")
	public void oPreçoDoAluguelSeráR$(int arg1) throws Throwable {
		Assert.assertEquals(arg1, notaAluguel.getPreço());
	}

	@Então("^o estoque do filme será (\\d+) unidade$")
	public void oEstoqueDoFilmeSeráUnidade(int arg1) throws Throwable {
		Assert.assertEquals(arg1, filme.getEstoque());
	}

	@Então("^não será possível  por falta de estoque$")
	public void nãoSeráPossívelPorFaltaDeEstoque() throws Throwable {
		Assert.assertEquals("Filme sem estoque", erro);
	}

	@Dado("^que o tipo de aluguel seja (.*)$")
	public void queOTipoDeAluguelSejaExtendido(String tipo) throws Throwable {
		tipoAluguel = tipo.equals("semanal") ? TipoAluguel.SEMANAL
				: tipo.equals("extendido") ? TipoAluguel.EXTENDIDO : TipoAluguel.COMUM;
	}

	@Então("^a data de entrega será em (\\d+) dias?$")
	public void aDataDeEntregaSeráEmDias(int arg1) throws Throwable {
		Date dataEsperada = DateUtils.obterDataDiferencaDias(arg1);
		Date dataReal = notaAluguel.getDataEntrega();
		DateFormat format = new SimpleDateFormat("##/##/####");

		Assert.assertEquals(format.format(dataEsperada), format.format(dataReal));
	}

	@Então("^a pontuação recebida será de (\\d+) pontos?$")
	public void aPontuaçãoRecebidaSeráDePontos(int arg1) throws Throwable {
		Assert.assertEquals(arg1, notaAluguel.getPontuacao());
	}

}
