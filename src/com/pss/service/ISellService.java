package com.pss.service;

import java.util.List;

import com.pss.po.Sell;
import com.pss.po.SellDetail;


public interface ISellService {
	//分页查询所有商品信息
	public List<Sell> queryAll();
	//添加商品信息的方法
	public void add(Sell e);
	//更新商品信息的方法
	public void update(Sell e);
	//删除商品信息的方法
	public void del(String id);
	//按照商品编号查询商品信息的方法
	public Sell queryById(String sid);
	//按照商品名称模糊查询商品信息的方法
	public List<SellDetail> queryByName(String name,int currentPage,int pageSize);
	//查询商品总的记录数
	public int findTotalNum(String likekey);

}



