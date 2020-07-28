package com.pss.dao;

import java.util.List;

import com.pss.po.Sell;
import com.pss.po.SellDetail;

	public interface ISellDao {
		//分页查询所有商品信息
		  List<Sell> queryAll();
		  //添加商品信息的方法
		  void add(Sell s);
		  //更新商品信息的方法
		  void update(Sell s);
		  //删除商品信息的方法
		  void del(String id);
		  //按照商品编号查询商品信息的方法
		  Sell queryById(String id);
		  //按照商品名称模糊查询商品信息的方法
		  List<SellDetail> queryByName(String name,int currentPage,int pageSize);
		  //查询商品总的记录数
		  int findTotalNum(String Likekey);
		  //分页根据的方法
		  //public List<Sell> findByPage(int currentPage,int pageSize);
	}

