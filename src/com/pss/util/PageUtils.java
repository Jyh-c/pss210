package com.pss.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtils<T> {
	//�ܼ�¼��
	private int totalNum;
	//��ǰҳ
	private int curPage;
	//ÿҳ��ʾ��������ÿҳ��С��
	private int pageSize;
	//��ҳ
	private int firstPage =1;
	//βҳ
	private int lastPage;
	//��ҳ��
	private int totalPage;
	//��һҳ
	private int prev;
	//��һҳ
	private int next;
	//ҳ�������ʾ����ʼλ��
	private int startNum;
	//ҳ����ʾ����-��ʼҳ��
	private int start;
	//ҳ����ʾ����-����ҳ��
	private int end;
	//��ʾҳ�����-����ʾҳ�루��ֹҳ����࣬ҳ����ʾӵ���ѿ���
	private int count =10;
	//����
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
