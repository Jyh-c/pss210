package com.pss.dao;

import java.util.List;

import com.pss.po.Employee;

public interface IEmployeeDao {
	//分页查询所有销售员信息
	  List<Employee> queryAll();
	  //添加销售员信息的方法
	  void add(Employee e);
	  //更新销售员信息的方法
	  void update(Employee e);
	  //删除销售员信息的方法
	  void delete(int id);
	  //按照销售员编号查询商品信息的方法
	  Employee queryById(int id);
	  //按照销售员名称、性别、电话模糊查询商品信息的方法
	  List<Employee> queryByName(String name,int currentPage,int pageSize);
	  //计算获得销售员总的记录数
	  int findTotalNum(String Likekey);
	  //分页根据的方法
	  List<Employee> findByPage(int currentPage,int pageSize);
}

