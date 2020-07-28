package com.pss.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtils<T> {
	//总记录数
	private int totalNum;
	//当前页
	private int curPage;
	//每页显示多少条（每页大小）
	private int pageSize;
	//首页
	private int firstPage =1;
	//尾页
	private int lastPage;
	//总页数
	private int totalPage;
	//上一页
	private int prev;
	//下一页
	private int next;
	//页面序号显示的起始位置
	private int startNum;
	//页面显示控制-开始页码
	private int start;
	//页面显示控制-结束页码
	private int end;
	//显示页码控制-总显示页码（防止页码过多，页码显示拥挤难看）
	private int count =10;
	//数据
	private List<T> list =new ArrayList<T>();
	
	
	public PageUtils(int curPage, int pageSize, int totalNum) {
		super();
		this.totalNum = totalNum;
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.totalPage = (int)Math.ceil((double)totalNum/pageSize);
		this.lastPage = (int)Math.ceil((double)totalNum/pageSize);
		this.curPage = Math.max(this.curPage, 1);
		this.curPage = Math.min(this.totalPage, this.curPage);
		this.prev = Math.max(this.curPage-1, 1);
		this.next = Math.min(this.curPage+1, this.totalPage);
		this.startNum = (this.curPage-1)*pageSize;
		this.start = Math.max(this.curPage-this.count/2, 1);
		this.end = Math.min(this.start+this.count, this.totalPage);
		
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPrev() {
		return prev;
	}
	public void setPrev(int prev) {
		this.prev = prev;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
