package io.spring.rabbitmq.rmqorderservice.dto;

public class OrderDTO {
	
	private String skuCode;
	private int quantity;
	
	public OrderDTO() {
		super();
	}

	public OrderDTO(String skuCode, int quantity) {
		super();
//		this.id = id;
//		this.orderNumber = orderNumber;
		this.skuCode = skuCode;
		this.quantity = quantity;
	}

	

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Order [skuCode=" + skuCode + ", quantity=" + quantity + "]";
	}



	

}
