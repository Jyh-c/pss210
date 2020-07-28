package com.pss.service;

import java.util.List;

import com.pss.po.Product;

public interface IProductService {
	//分页查询所有商品信息
	public List<Product> queryAll();
	//添加商品信息的方法
	public void add(Product p);
	//更新商品信息的方法
	public void update(Product p);
	//删除商品信息的方法
	public void del(int id);
	//按照商品编号查询商品信息的方法
	public Product queryById(int id);
	//按照商品名称模糊查询商品信息的方法
	public List<Product> queryByName(String name,int currentPage,int pageSize);
	//查询商品总的记录数
	public int findTotalNum(String keyWords);
}
