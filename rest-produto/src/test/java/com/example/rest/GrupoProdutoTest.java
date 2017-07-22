package com.example.rest;

import br.com.leandro.luizalabs.GrupoProduto;
import br.com.leandro.luizalabs.GrupoProdutoService;
import br.com.leandro.luizalabs.Produto;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class GrupoProdutoTest {

    
    private GrupoProdutoService service; 
            
            
    @Before
    public void setUp(){
        service = new GrupoProdutoService();
    }
    @Test
    public void deveRetornarProdutosAgrupados() {
        List<GrupoProduto> grupos = service.obterAgrupamento(obterProdutos(), null, "");
        assertNotNull(grupos);
        assertEquals("LIVRO JAVA SE",grupos.get(0).getDescrition());
    }
    
    @Test
    public void deveRetornarProdutosAgrupadosEFiltradosPorEAN1() {
        String filtro = "ean:1";
        List<GrupoProduto> grupos = service.obterAgrupamento(obterProdutos(), filtro, "");
        assertNotNull(grupos);
        
    }
    @Test
    public void naoDeveRetornarProdutosAgrupadosEFiltradosPorEAN2233() {
        String filtro = "ean:3456TESTE";
        List<GrupoProduto> grupos = service.obterAgrupamento(obterProdutos(), filtro, "");
        assertEquals(0, grupos.size());
    }
    
    @Test
    public void deveRetornarProdutosAgrupadosEOrdenadoPorTitleDESC() {
        String filtro = "ean:2233";
        String orderby = "title:desc";
        List<GrupoProduto> grupos = service.obterAgrupamento(obterProdutos(), filtro, orderby);
        assertNotNull(grupos);
        assertEquals("PYTHON", grupos.get(0).getDescrition());
    }
    
    public List<Produto> obterProdutos(){
        List<Produto> produtos = new ArrayList<Produto>();
        Produto p1 = new Produto();
        p1.setId("1");
        p1.setTitle("TITULO C");
        p1.setEan("1");
        p1.setPrice(10.0D);
        p1.setStock(3);
        p1.setBrand("MARCA");
        produtos.add(p1);
        
        Produto p2 = new Produto();
        p2.setId("2");
        p2.setTitle("TITULO A");
        p2.setEan("1");
        p2.setPrice(15.0D);
        p2.setStock(1);
        p2.setBrand("MARCA 2");
        produtos.add(p2);
        
        Produto p3 = new Produto();
        p3.setId("3");
        p3.setTitle("LIVRO JAVA SE");
        p3.setEan("2233");
        p3.setPrice(0.33D);
        p3.setStock(65);
        p3.setBrand("NOVA MARCA 2");
        produtos.add(p3);
        
        Produto p4 = new Produto();
        p4.setId("4");
        p4.setTitle("PYTHON");
        p4.setEan("2233");
        p4.setPrice(985.11D);
        p4.setStock(12);
        p4.setBrand("MARCA 2");
        produtos.add(p4);
        
        Produto p5 = new Produto();
        p5.setId("5");
        p5.setTitle("CERTIFICACAO JAVA 8");
        p5.setEan("2233");
        p5.setPrice(15.43D);
        p5.setStock(2);
        p5.setBrand("OLD MARCA");
        produtos.add(p5);
        
        return produtos;
    }
}
