package com.pss.service.impl;

import java.util.List;


import com.pss.dao.impl.EmployeeDaoImpl;
import com.pss.po.Employee;
import com.pss.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService {
	private EmployeeDaoImpl ipd1 = new EmployeeDaoImpl();
	@Override
	public List<Employee> queryAll() {
		return ipd1.queryAll();
	}

	@Override
	public void add(Employee e) {
		ipd1.add(e);
	}

	@Override
	public void update(Employee e) {
		ipd1.update(e);
	}

	@Override
	public void delete(int id) {
		ipd1.delete(id);

	}

	@Override
	public Employee queryById(int id) {
		return ipd1.queryById(id);
	}

	@Override
	public List<Employee> queryByName(String name, int currentPage, int pageSize) {
		return ipd1.queryByName(name, currentPage, pageSize);
	}

	@Override
	public int findTotalNum(String likekey) {
		return ipd1.findTotalNum(likekey);
	}




	@Override
	public List<Employee> findByPage(int currentPage, int pageSize) {
		return ipd1.findByPage(currentPage, pageSize);
	}

}
