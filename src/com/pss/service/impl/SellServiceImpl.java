package com.pss.service.impl;

import java.util.List;

import com.pss.dao.ISellDao;
import com.pss.dao.impl.SellDaoImpl;
import com.pss.po.Sell;
import com.pss.po.SellDetail;
import com.pss.service.ISellService;

public class SellServiceImpl implements ISellService {
	ISellDao isd  = new SellDaoImpl();
	@Override
	public List<Sell> queryAll() {
		return isd.queryAll();
	}

	@Override
	public void add(Sell s) {
		isd.add(s);
	}


	@Override
	public void update(Sell s) {
		isd.update(s);
	}
	@Override
	public void del(String id) {
		isd.del(id);

	}
	@Override
	public Sell queryById(String id) {
		return isd.queryById(id);
	}
	@Override
	public List<SellDetail> queryByName(String name, int currentPage, int pageSize) {
		return isd.queryByName(name, currentPage, pageSize);
	}

	@Override
	public int findTotalNum(String likekey) {
		return isd.findTotalNum(likekey);
	}



}

