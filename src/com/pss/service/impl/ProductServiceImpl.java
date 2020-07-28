package com.pss.service.impl;

import java.util.List;

import com.pss.dao.IProductDao;
import com.pss.dao.impl.ProductDaoImpl;
import com.pss.po.Product;
import com.pss.service.IProductService;

public class ProductServiceImpl implements IProductService {
	private IProductDao ipd = new ProductDaoImpl();
	@Override
	public List<Product> queryAll() {
		return ipd.queryAll();
	}

	@Override
	public void add(Product p) {
		ipd.add(p);
	}

	@Override
	public void update(Product p) {
		ipd.update(p);
	}

	@Override
	public void del(int id) {
		ipd.del(id);

	}

	@Override
	public Product queryById(int id) {
		return ipd.queryById(id);
	}

	@Override
	public List<Product> queryByName(String name, int currentPage, int pageSize) {
		return ipd.queryByName(name, currentPage, pageSize);
	}

	@Override
	public int findTotalNum(String keyWords) {
		return ipd.findTotalNum(keyWords);
	}

}
