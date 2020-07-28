package com.pss.po;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	private int pid;
	private String name;
	private int price;
	private int store;
	private int state;
	
	public Product() {
		super();
	}
	public Product(int pid, String name, int price, int store, int state) {
		super();
		this.pid = pid;
		this.name = name;
		this.price = price;
		this.store = store;
		this.state = state;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStore() {
		return store;
	}
	public void setStore(int store) {
		this.store = store;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pid;
		result = prime * result + price;
		result = prime * result + state;
		result = prime * result + store;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pid != other.pid)
			return false;
		if (price != other.price)
			return false;
		if (state != other.state)
			return false;
		if (store != other.store)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", name=" + name + ", price=" + price + ", store=" + store + ", state=" + state
				+ "]";
	}
	
}
