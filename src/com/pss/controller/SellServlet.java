package com.pss.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pss.po.*;
import com.pss.service.*;
import com.pss.service.impl.*;
import com.pss.util.*;

/**
 * Servlet implementation class SellServlet
 */
@WebServlet("/SellServlet")
public class SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ISellService sellService = new SellServiceImpl();
	private IEmployeeService employeeService = new EmployeeServiceImpl();
	private IProductService productService = new ProductServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if ("add".equals(action)) {
			add(request, response);
		} else if ("update".equals(action)) {
			update(request, response);
		} else if ("del".equals(action)) {
			del(request, response);
		} else if ("deleteAll".equals(action)) {
			deleteAll(request, response);
		} else if ("queryAll".equals(action)) {
			queryAll(request, response);
		} else if ("queryByID".equals(action)) {
			queryByID(request, response);
		} else if ("findByPage".equals(action)) {
			findByPage(request, response);
		} else if ("changePageSize".equals(action)) {
			changePageSize(request, response);
		} else if ("changeLikeSell".equals(action)) {
			changeLikeSell(request, response);
		} else if ("initAdd".equals(action)) {
			initAdd(request, response);
		} else if (null == action) {
			findByPage(request, response);
		}
	}

	private void initAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Employee> employees = employeeService.queryAll();
		request.setAttribute("employees", employees);
		List<Product> products = productService.queryAll();
		request.setAttribute("products", products);
		request.getRequestDispatcher("addSell.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		int pid = Integer.valueOf(request.getParameter("pid"));
		int eid = Integer.valueOf(request.getParameter("eid"));
		int amount = Integer.valueOf(request.getParameter("amount"));
		String selldate = request.getParameter("selldate");
		sellService.add(new Sell(format.format(now), pid, eid, amount, selldate, 1));
		findByPage(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid");
		int pid = Integer.valueOf(request.getParameter("pid"));
		int eid = Integer.valueOf(request.getParameter("eid"));
		int amount = Integer.valueOf(request.getParameter("amount"));
		String selldate = request.getParameter("selldate");
		sellService.update(new Sell(sid, pid, eid, amount, selldate, 1));
		findByPage(request, response);

	}

	private void del (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid");
		sellService.del(sid);
		findByPage(request, response);
	}

	private void deleteAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkId = request.getParameter("checkId");
		for (String sid : checkId.split(",")) {
			sellService.del(sid);
		}
		findByPage(request, response);

	}

	private void queryAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Sell> sellList = sellService.queryAll();
		request.setAttribute("sellList", sellList);
		request.getRequestDispatcher("selllist.jsp").forward(request, response);

	}

	private void queryByID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("sid");
		Sell sell = sellService.queryById(sid);
		Product product = productService.queryById(sell.getPid());
		Employee employee = employeeService.queryById(sell.getEid());
		List<Employee> employees = employeeService.queryAll();
		List<Product> products = productService.queryAll();
		request.setAttribute("products", products);
		request.setAttribute("employees", employees);
		request.setAttribute("employee", employee);
		request.setAttribute("product", product);
		request.setAttribute("sell", sell);
		request.getRequestDispatcher("updateSell.jsp").forward(request, response);
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (null == session.getAttribute("likeSellKey")) {// �״η���
			session.setAttribute("likeSellKey", "");
		}
		int totalNum = sellService.findTotalNum((String) session.getAttribute("likeSellKey"));
		if (null == session.getAttribute("pageBeanSell")) {
			PageUtils<SellDetail> pageBeanSell = new PageUtils<SellDetail>(1, 10, totalNum);
			session.setAttribute("pageBeanSell", pageBeanSell);
		}
		PageUtils<SellDetail> pageBeanSell = (PageUtils<SellDetail>) session.getAttribute("pageBeanSell");
		String curPage = request.getParameter("curPage");
		if (null != curPage) {
			//
			pageBeanSell = new PageUtils<SellDetail>(Integer.valueOf(curPage), pageBeanSell.getPageSize(), totalNum);
			session.setAttribute("pageBeanSell", pageBeanSell);
		} else {
			//
			pageBeanSell = new PageUtils<SellDetail>(pageBeanSell.getCurPage(), pageBeanSell.getPageSize(), totalNum);
			session.setAttribute("pageBeanSell", pageBeanSell);
		}
		List<SellDetail> sellDetailsList = sellService.queryByName((String) session.getAttribute("likeSellKey"),
				pageBeanSell.getCurPage(), pageBeanSell.getPageSize());
		request.setAttribute("sellDetailsList", sellDetailsList);
		pageBeanSell.setList(sellDetailsList);
		request.getRequestDispatcher("selllist.jsp").forward(request, response);

	}

	private void changePageSize(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pageSize = request.getParameter("pageSize");
		PageUtils<SellDetail> pageBeanSell = (PageUtils<SellDetail>) session.getAttribute("pageBeanSell");
		if (null != pageBeanSell) {
			pageBeanSell.setPageSize(Integer.valueOf(pageSize));
		}
		findByPage(request, response);

	}

	private void changeLikeSell(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String likeSellKey = request.getParameter("likeSellKey");
		HttpSession session = request.getSession();
		if (null == session.getAttribute("likeSellKey")) {
			findByPage(request, response);
		} else {
			session.setAttribute("likeSellKey", likeSellKey);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
