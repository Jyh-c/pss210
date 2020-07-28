package com.pss.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pss.dao.IEmployeeDao;
import com.pss.po.Employee;
import com.pss.util.DBUtils;

public class EmployeeDaoImpl implements IEmployeeDao {
	private QueryRunner qr = null;
	public EmployeeDaoImpl() {
		qr = new QueryRunner();
	}
	@Override
	public List<Employee> queryAll() {
		String sql="select eid,name,sex,birthday,phone,state from t_employee";
		List<Employee> empList = null;
		try {
			empList = qr.query(DBUtils.getConnection(), sql, 
					new BeanListHandler<Employee>(Employee.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public void add(Employee e) {
		String sql="insert into t_employee (name,sex,birthday,phone,state) value (?,?,?,?,?)";
		try {
			qr.insert(DBUtils.getConnection(), sql, new BeanHandler<Employee>(Employee.class),
					e.getName(),e.getSex(),e.getBirthday(),e.getPhone(),e.getState());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void update(Employee e) {
		String sql ="update t_employee set name=?,sex=?,birthday=?,phone=?,state=? where eid =?";
		try {
			qr.update(DBUtils.getConnection(), sql, e.getName(),e.getSex(),
					e.getBirthday(),e.getPhone(),e.getState(),e.getEid());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql ="delete from t_employee where eid=?";
		try {
			qr.update(DBUtils.getConnection(), sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Employee queryById(int id) {
		String sql ="select eid,name,sex,birthday,phone,state from t_employee where eid=?";
		Employee emp =null;
		try {
			emp = qr.query(DBUtils.getConnection(), sql, new BeanHandler<Employee>(Employee.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public List<Employee> queryByName(String name, int currentPage, int pageSize) {
		String sql =null;
		List<Employee> empList =null;
		try {
			Pattern pattern = Pattern.compile("[0-9]*");
			if(null==name||name.trim().length()==0){
				sql ="select eid,name,sex,birthday,phone,state from t_employee limit ?,?";
				empList =qr.query(DBUtils.getConnection(), sql,	
			         new BeanListHandler<Employee>(Employee.class),(currentPage-1)*pageSize,pageSize);
				return empList;
			}else if(pattern.matcher(name).matches()==true){
				sql ="select eid,name,sex,birthday,phone,state from t_employee where phone like ? limit ?,?";
			}else if("ÄÐ".equals(name)||"Å®".equals(name)){
				sql ="select eid,name,sex,birthday,phone,state from t_employee where sex like ? limit ?,?";
			}else{
				sql ="select eid,name,sex,birthday,phone,state from t_employee where name like ? limit ?,?";
			}
			
			empList =qr.query(DBUtils.getConnection(), sql,	
			       new BeanListHandler<Employee>(Employee.class),"%"+name+"%",
			       (currentPage-1)*pageSize,pageSize);
			   
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}


	@Override
	public int findTotalNum(String likeKey) {
		String sql ="select count(eid) from t_employee where name like ? or sex like ? or phone like ? ";
		int total = 0;
		try {
			Object obj = qr.query(DBUtils.getConnection(), sql, 
					new ScalarHandler<Object>(),"%"+likeKey+"%","%"+likeKey+"%","%"+likeKey+"%");
			total = Integer.parseInt(String.valueOf(obj));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public List<Employee> findByPage(int currentPage, int pageSize) {
		String sql ="select eid ,name,sex,birthday,phone,state from t_employee limit ?,?";
		List<Employee> empList =null;
		try {
			empList = qr.query(DBUtils.getConnection(), sql, new BeanListHandler<Employee>(Employee.class),
					(currentPage-1)*pageSize,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}

}
