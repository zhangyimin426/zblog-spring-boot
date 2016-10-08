package com.zym.common.base.jdbc;

public class OrderByDto  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//排序数据表字段名称
	private String[] orderColumn;
	//排序方式，升序或者降序
	private OrderType orderType;
	
	
	public enum OrderType{
		DESC("desc"),
		ASC("asc"),
		;
		
		private final String value;
		private OrderType(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}


	public String[] getOrderColumn() {
		return orderColumn;
	}


	public void setOrderColumn(String[] orderColumn) {
		this.orderColumn = orderColumn;
	}


	public String getOrderType() {
		return orderType.getValue();
	}


	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
}
