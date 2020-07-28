package com.pss.service;

import java.util.List;

import com.pss.po.Employee;

public interface IEmployeeService {
		//分页查询所有商品信息
		List<Employee> queryAll();
		//添加商品信息的方法
		void add(Employee e);
		//更新商品信息的方法
		void update(Employee e);
		//删除商品信息的方法
		void delete(int id);
		//按照商品编号查询商品信息的方法
		Employee queryById(int id);
		//按照商品名称模糊查询商品信息的方法
		List<Employee> queryByName(String name,int currentPage,int pageSize);
		//查询商品总的记录数
		int findTotalNum(String likeKey);
		List<Employee> findByPage(int currentPage,int pageSize);
	}


