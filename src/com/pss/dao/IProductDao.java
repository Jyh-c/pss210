package com.pss.dao;

import java.util.List;

import com.pss.po.Product;

public interface IProductDao {
	//分页查询所有商品信息
	  List<Product> queryAll();
	  //添加商品信息的方法
	  void add(Product p);
	  //更新商品信息的方法
	  void update(Product p);
	  //删除商品信息的方法
	  void del(int id);
	  //按照商品编号查询商品信息的方法
	  Product queryById(int id);
	  //按照商品名称模糊查询商品信息的方法
	  List<Product> queryByName(String name,int currentPage,int pageSize);
	  //查询商品总的记录数
	  int findTotalNum(String keyWords);
}
