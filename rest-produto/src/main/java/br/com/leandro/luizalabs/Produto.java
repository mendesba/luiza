package br.com.leandro.luizalabs;

public class Produto {
	private String id;
	private String ean;
	private String title;
	private String brand;
	private Double price;
	private Integer stock;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public boolean getFiltro(String filtro) {
		String[] filtroSplit = filtro.split(":");
		
		if (filtroSplit.length == 2) {
			String campo = filtroSplit[0];
			String valor = filtroSplit[1];

			if (campo.toUpperCase().equals("ID")) {
				return id.equals((String) valor);
			} else if (campo.toUpperCase().equals("EAN")) {
				return ean.equals((String) valor);
			} else if (campo.toUpperCase().equals("TITLE")) {
				return title.equals((String) valor);
			} else if (campo.toUpperCase().equals("BRAND")) {
				return brand.equals((String) valor);
			} else if (campo.toUpperCase().equals("PRICE")) {
				return price == (Double.parseDouble(valor));
			} else if (campo.toUpperCase().equals("STOCK")) {
				return stock == (Integer.parseInt(valor));
			}
		}
		return true;
	}

}
