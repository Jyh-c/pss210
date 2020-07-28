package com.pss.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pss.dao.ISellDao;
import com.pss.po.Sell;
import com.pss.po.SellDetail;
import com.pss.po.*;
import com.pss.util.DBUtils;

public class SellDaoImpl implements ISellDao {
	private QueryRunner qr = null;

	public SellDaoImpl() {
		qr = new QueryRunner();
	}

	@Override
	public List<Sell> queryAll() {
		// TODO Auto-generated method stub
		String sql = "select sid,pid,eid,amount,selldate,state from t_sell";
		List<Sell> sellList = null;
		try {
			sellList = qr.query(DBUtils.getConnection(), sql, new BeanListHandler<Sell>(Sell.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sellList;
	}

	@Override
	public void add(Sell s) {
		// TODO Auto-generated method stub
		String sql = "insert into t_sell (sid,pid,eid,amount,selldate,state) values (?,?,?,?,?,?)";
		try {
			qr.insert(DBUtils.getConnection(), sql, new BeanHandler<Product>(Product.class), s.getSid(), s.getPid(),
					s.getEid(), s.getAmount(), s.getSelldate(), s.getState());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void update(Sell s) {
		// TODO Auto-generated method stub
		String sql = "update t_sell set pid=?,eid=?,amount=?,selldate=?,state=? where sid=?";
		try {
			qr.update(DBUtils.getConnection(), sql, s.getPid(), s.getEid(), s.getAmount(), s.getSelldate(),
					s.getState(), s.getSid());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from t_sell where sid=? ";
		try {
			qr.update(DBUtils.getConnection(), sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Sell queryById(String id) {
		// TODO Auto-generated method stub
		String sql = "select sid,pid,eid,amount,selldate,state from t_sell where sid=?";
		Sell sell = null;
		try {
			sell = qr.query(DBUtils.getConnection(), sql, new BeanHandler<Sell>(Sell.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sell;
	}

	@Override
	public List<SellDetail> queryByName(String name, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		String sql = null;
		List<SellDetail> sellDetails = null;
		try {
			sql = "select t_sell.sid,t_product.name as pname,t_employee.name as ename,t_sell.amount,t_sell.selldate from "
					+ "t_employee inner join t_sell on t_employee.eid=t_sell.eid "
					+ "inner join t_product on t_sell.pid=t_product.pid "
					+ "where t_sell.sid like ? or t_product.name like ? or t_employee.name like ? limit ?,?";

			sellDetails = qr.query(DBUtils.getConnection(), sql, new BeanListHandler<SellDetail>(SellDetail.class),
					"%" + name + "%", "%" + name + "%", "%" + name + "%", (currentPage - 1) * pageSize, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sellDetails;
	}

	@Override
	public int findTotalNum(String likeKey) {
		// TODO Auto-generated method stub
		String sql = "select count(t_sell.sid) from " 
		+ "t_employee inner join t_sell on t_employee.eid=t_sell.eid "
				+ "inner join t_product on t_sell.pid=t_product.pid "
				+ "where t_sell.sid like ? or t_product.name like ? or t_employee.name like?";
		int totalNum = 0;
		try {
			Object object = qr.query(DBUtils.getConnection(), sql, new ScalarHandler<Object>(), "%" + likeKey + "%",
					"%" + likeKey + "%", "%" + likeKey + "%");
			totalNum = Integer.parseInt(String.valueOf(object));
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return totalNum;
	}

}
