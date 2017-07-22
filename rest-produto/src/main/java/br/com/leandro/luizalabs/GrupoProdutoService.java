package br.com.leandro.luizalabs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ReverseComparator;

import com.google.common.base.Strings;

import info.debatty.java.stringsimilarity.NormalizedLevenshtein;

public class GrupoProdutoService {
	private static final String DESC = "DESC";
	private static final Double C_DISTANCIA = 0.45D;
        
	public List<GrupoProduto> obterAgrupamento(List<Produto> produtos, String filter, String orderby) {
		List<GrupoProduto> grupos = new ArrayList<GrupoProduto>();

		produtos = getFilter(produtos, filter);

		getAgruparPorHierarquia(produtos, grupos);

		gteOrderByProdutos(grupos, orderby);

		grupos.sort(Comparator.comparing(GrupoProduto::getDescrition));

		return grupos;

	}

	private List<Produto> getFilter(List<Produto> produtos, String filter) {
		List<Produto> listaFiltrada = new ArrayList();
		if (!Strings.isNullOrEmpty(filter)) {
			listaFiltrada = produtos.stream().filter(p -> p.getFiltro(filter)).collect(Collectors.toList());
		}else {
			listaFiltrada = produtos;
		}

		return listaFiltrada;
	}

	private void getAgruparPorHierarquia(List<Produto> produtos, List<GrupoProduto> grupos) {

		getAgruparPorEANS(produtos, grupos);

		getAgruparPorTitleSimilar(produtos, grupos);

		getAgruparPorMarca(produtos, grupos);
	}

	private void getAgruparPorEANS(List<Produto> produtos, List<GrupoProduto> grupos) {
		Map<String, List<Produto>> agruparEAN = produtos.stream().collect(Collectors.groupingBy(Produto::getEan));
		agruparEAN.forEach((k, v) -> {
			if (v.size() > 1) {
				grupos.add(new GrupoProduto(k, v, false));
				produtos.removeAll(v);
			}
		});
	}

	private void getAgruparPorTitleSimilar(List<Produto> produtos, List<GrupoProduto> grupos) {
		Map<String, List<Produto>> agruparSimilar = new HashMap<>();
		List<String> adicionados = new ArrayList<>();

		produtos.forEach(p -> {
			if (adicionados.isEmpty() || !adicionados.contains(p.getId())) {
				produtos.forEach(paux -> {
					NormalizedLevenshtein normalizacao = new NormalizedLevenshtein();
					Double distancia = normalizacao.distance(p.getTitle().toUpperCase(), paux.getTitle().toUpperCase());
					if (distancia <= C_DISTANCIA) {
						if (!agruparSimilar.containsKey(p.getTitle())) {
							agruparSimilar.put(p.getTitle(), new ArrayList<Produto>());
						}
						agruparSimilar.get(p.getTitle()).add(paux);
						adicionados.add(paux.getId());
					}
				});
			}
		});

		agruparSimilar.forEach((k, v) -> {
			if (v.size() > 1) {
				grupos.add(new GrupoProduto(k, v, false));
				produtos.removeAll(v);
			}
		});
	}

	private void getAgruparPorMarca(List<Produto> produtos, List<GrupoProduto> grupos) {
		Map<String, List<Produto>> agruparMarca = produtos.stream().collect(Collectors.groupingBy(Produto::getBrand));
		agruparMarca.forEach((k, v) -> grupos.add(new GrupoProduto(k, v, true)));
	}

	private void gteOrderByProdutos(List<GrupoProduto> grupos, String orderby) {
		grupos.forEach(g -> {
			if (orderby == null) {
				g.getItems().sort(Comparator.comparing(Produto::getStock).reversed().thenComparing(Produto::getPrice));
			} else {
				String[] orderbySplit = orderby.split(":");

				if (orderbySplit.length == 2) {
					String campo = orderbySplit[0];
					String ordem = orderbySplit[1];

					Comparator comparator = ordenar(g.getItems(), campo, DESC.equals(ordem.toUpperCase()));
					g.getItems().sort(comparator);
				}
			}

			if (g.isBrand()) {
				g.setDescrition(g.getItems().get(0).getBrand());
			} else {
				g.setDescrition(g.getItems().get(0).getTitle());
			}
		});
	}

	@SuppressWarnings("unchecked")
	protected Comparator ordenar(List lista, String campo, boolean descending) {
		Comparator comparator = new BeanComparator(campo);
		if (descending) {
			comparator = new ReverseComparator(comparator);
		}
		return comparator;
	}
}
