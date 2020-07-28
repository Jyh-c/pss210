package com.pss.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.pss.po.Employee;
import com.pss.service.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {
   EmployeeServiceImpl ies = new EmployeeServiceImpl();
	@Test
	public void testQueryAll() {
		List<Employee> empList = ies.queryAll();
		for(Employee e:empList){
			System.out.println(e);
		}
		Assert.assertEquals(6, empList.size());
	}

	@Test
	public void testAdd() {
		Employee e = new Employee(0,"1","mshuai","2011-01-09","12",1);
		ies.add(e);
	}

	@Test
	public void testUpdate() {
		Employee e = new Employee(2,"1","mshuai","2011-01-09","12",1);
		ies.update(e);
	}

	@Test
	public void testDel() {
		ies.delete(7);
	}

	@Test
	public void testQueryById() {
		ies.queryById(6);
	}

	@Test
	public void testQueryByName() {
		int total = ies.findTotalNum("");
		Assert.assertEquals(6, total);
	}

	@Test
	public void testFindTotalNum() {
		List<Employee> emplist = ies.findByPage(2, 3);
		for(Employee e:emplist){
			System.out.println(e);
		}
		Assert.assertEquals(3, emplist.size());
	}

}
