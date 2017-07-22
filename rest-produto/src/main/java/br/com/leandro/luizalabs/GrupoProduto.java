package br.com.leandro.luizalabs;

import java.util.ArrayList;
import java.util.List;

public class GrupoProduto {
	private String descrition;
	private List<Produto> items = new ArrayList<Produto>();
	private boolean brand;
	
	public GrupoProduto() {}
	
	public GrupoProduto(String description, List<Produto> items, boolean brand) {
		this.descrition = description;
		this.items = items;
		this.brand = brand;
	}
	
	public String getDescrition() {
		return descrition;
	}
	
	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}
	
	public List<Produto> getItems() {
		return items;
	}
	
	public void setItems(List<Produto> items) {
		this.items = items;
	}

	public boolean isBrand() {
		return brand;
	}

	public void setBrand(boolean brand) {
		this.brand = brand;
	}
	
}
