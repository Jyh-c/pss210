package com.pss.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pss.dao.IProductDao;
import com.pss.po.Product;
import com.pss.util.DBUtils;

public class ProductDaoImpl implements IProductDao {
 	private QueryRunner qr=null;
 	public ProductDaoImpl() {
		qr = new QueryRunner();
	}
	@Override
	public List<Product> queryAll() {
		String sql = "select pid,name,price,store,state from t_product";
		List<Product> pList = null;
		try {
			pList = qr.query(DBUtils.getConnection(), sql,
					new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pList;
	}

	@Override
	public void add(Product p) {
		String sql = "insert into t_product (name,price,store,state) values (?,?,?,?)";
		try {
			qr.update(DBUtils.getConnection(), sql,
			p.getName(),p.getPrice(),p.getStore(),p.getState());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Product p) {
		String sql = "update t_product set name=?,price=?,store=?,state=? where pid=?";
		try {
			qr.update(DBUtils.getConnection(),sql,
					p.getName(),p.getPrice(),p.getStore(),p.getState(),p.getPid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void del(int id) {
		String sql = "delete from t_product where pid=?";
		try {
			qr.update(DBUtils.getConnection(),sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Product queryById(int id) {
		String sql = "select pid,name,price,store,state from t_product where pid=?";
		Product p = null;
		try {
			p = qr.query(DBUtils.getConnection(), sql, new BeanHandler<Product>(Product.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Product> queryByName(String name, int currentPage, int pageSize) {
		String sql = "select pid,name,price,store,state from t_product where name like ? limit ?,?";
		List<Product> pList = null;
		try {
			pList = qr.query(DBUtils.getConnection(),sql,new BeanListHandler<Product>(Product.class),
					"%"+name+"%",(currentPage-1)*pageSize,pageSize);
			//
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pList;
	}

	@Override
	public int findTotalNum(String keyWords) {
		String sql ="select count(pid) from t_product where name like ?";
		int totalNum = 0;
		try {
			Object obj=qr.query(DBUtils.getConnection(), sql,
					new ScalarHandler<Object>(),"%"+keyWords+"%");
			totalNum = Integer.parseInt(String.valueOf(obj));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalNum;
	}

}
