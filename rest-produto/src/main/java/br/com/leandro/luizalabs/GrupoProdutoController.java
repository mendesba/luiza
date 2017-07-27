package br.com.leandro.luizalabs;

import java.util.List;
import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path(value = "/produto")
public class GrupoProdutoController {

	private GrupoProdutoService service= new GrupoProdutoService();

	@POST
	@Path(value="/agrupar")
	@Produces("application/json")
	@Consumes("application/json")
	public List<GrupoProduto> obterAgrupamento(List<Produto> produtos, @QueryParam("filter") String filter, @QueryParam("order_by") String orderby){
		return service.obterAgrupamento(produtos, filter, orderby);
	}
}
