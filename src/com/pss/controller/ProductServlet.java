package com.pss.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pss.po.Product;
import com.pss.service.IProductService;
import com.pss.service.impl.ProductServiceImpl;
import com.pss.util.PageUtils;
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IProductService ips = new ProductServiceImpl();
    public ProductServlet() {
        super();
    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if("add".equals(action)) {
			add(request,response);
		}else if("update".equals(action)) {	
			update(request,response);
		}else if("delete".equals(action)) {
			delete(request,response);
		}else if("deleteAll".equals(action)) {
			deleteAll(request,response);
		}else if("queryAll".equals(action)) {
			queryAll(request,response);
		}else if("queryByID".equals(action)) {	
			queryByID(request,response);
		}else if("findByPage".equals(action)) {		
			findByPage(request,response);
		}else if("changePageSize".equals(action)) {	
			changePageSize(request,response);
		}else if("changeLikeProduct".equals(action)) {	
			changeLikeProduct(request,response);
		}else if(null==action) {
			findByPage(request,response);
		}
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String store = request.getParameter("store");
		Product p = new Product(0,name,Integer.valueOf(price),Integer.valueOf(store),1);
		ips.add(p);
		findByPage(request,response);
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String store = request.getParameter("store");
		Product p = new Product(Integer.valueOf(pid),name,Integer.valueOf(price),Integer.valueOf(store),1);
		ips.update(p);
		findByPage(request,response);
	}
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("id");
		ips.del(Integer.valueOf(pid));
		findByPage(request,response);
	}
	protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String checkId = request.getParameter("checkId");
		for(String id:checkId.split(",")) {
			ips.del(Integer.valueOf(id));
		}
		findByPage(request,response);
	}
	protected void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> pList = ips.queryAll();
		request.setAttribute("pList", pList);
		request.getRequestDispatcher("productlist.jsp").forward(request, response);
	}
	protected void queryByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.valueOf(request.getParameter("id"));
		Product p = ips.queryById(pid);
		request.setAttribute("p", p);
		request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
	}
	protected void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(null==session.getAttribute("likeProductKey")) {//�״η���
			session.setAttribute("likeProductKey","");
		}
		int totalNum = ips.findTotalNum((String)session.getAttribute("likeProductKey"));
		if(null==session.getAttribute("pageBeanProduct")) {
			PageUtils<Product> pageBeanProduct = new PageUtils<Product>(1,10,totalNum);
			session.setAttribute("pageBeanProduct",pageBeanProduct);
		}
		PageUtils<Product> pageBeanProduct = 
				(PageUtils<Product>)session.getAttribute("pageBeanProduct");
		String curPage = request.getParameter("curPage");
		if(null!=curPage) {
			//��ҳ
			pageBeanProduct =new PageUtils<Product>(Integer.valueOf(curPage),
					pageBeanProduct.getPageSize(),totalNum);
			session.setAttribute("pageBeanProduct", pageBeanProduct);
		}else {
			//��Ӻ�ɾ��
			pageBeanProduct =new PageUtils<Product>(pageBeanProduct.getCurPage(),
					pageBeanProduct.getPageSize(),totalNum);
			session.setAttribute("pageBeanProduct", pageBeanProduct);
		}
		List<Product> pList = ips.queryByName((String)session.getAttribute("likeProductKey"),
				pageBeanProduct.getCurPage(), pageBeanProduct.getPageSize());
		request.setAttribute("pList", pList);
		pageBeanProduct.setList(pList);
		request.getRequestDispatcher("productlist.jsp").forward(request, response);
		
	}
	protected void changePageSize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pageSize = request.getParameter("pageSize");
		PageUtils<Product> pageBeanProduct = (PageUtils<Product>)session.getAttribute("pageBeanProduct");
		if(null!=pageBeanProduct) {
			pageBeanProduct.setPageSize(Integer.valueOf(pageSize));
		}
		findByPage(request,response);
	}
	protected void changeLikeProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String likeProductKey = request.getParameter("likeProductKey");
		HttpSession session = request.getSession();
		if(null==session.getAttribute("likeProductKey")) {
			findByPage(request,response);
		}else {
			session.setAttribute("likeProductKey", likeProductKey);
		}
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
