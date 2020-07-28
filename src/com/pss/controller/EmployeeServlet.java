package com.pss.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pss.po.Employee;
import com.pss.po.Product;
import com.pss.service.IEmployeeService;
import com.pss.service.impl.EmployeeServiceImpl;
import com.pss.util.PageUtils;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IEmployeeService ies = new EmployeeServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		}else if("changeLikeEmployee".equals(action)) {	
			changeLikeEmployee(request,response);
		}else if(null==action) {
			findByPage(request,response);
		}
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		Employee emp =new Employee(0,name,sex,birthday,phone,1);
		ies.add(emp);
		findByPage(request,response);
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eid = Integer.valueOf(request.getParameter("eid"));
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		Employee emp =new Employee(eid,name,sex,birthday,phone,1);
		ies.update(emp);
		findByPage(request,response);
	}
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eid = Integer.valueOf(request.getParameter("id"));
		ies.delete(eid);
		findByPage(request,response);
	}
	protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String checkId = request.getParameter("checkId");
		for(String id:checkId.split(",")) {
			ies.delete(Integer.valueOf(id));
		}
		findByPage(request,response);
	}
	protected void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> empList = ies.queryAll();
		request.setAttribute("empList", empList);
		request.getRequestDispatcher("emplist.jsp").forward(request, response);
	}
	protected void queryByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eid = Integer.valueOf(request.getParameter("id"));
		Employee emp = ies.queryById(eid);
		request.setAttribute("emp", emp);
		request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
	}
	protected void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(null==session.getAttribute("likeEmployeeKey")) {
			session.setAttribute("likeEmployeeKey", "");
		}
		int totalNum = ies.findTotalNum((String)session.getAttribute("likeEmployeeKey"));
		if(null==session.getAttribute("pageBeanEmployee")) {
			PageUtils<Employee> pageBeanEmployee = new PageUtils<Employee>(1,10,totalNum);
			session.setAttribute("pageBeanEmployee",pageBeanEmployee);
		}
		PageUtils<Employee> pageBeanEmployee =
				(PageUtils<Employee>)session.getAttribute("pageBeanEmployee");
		String curPage = request.getParameter("curPage");
		if(null!=curPage) {
			//
			pageBeanEmployee = new PageUtils<Employee>(Integer.valueOf(curPage),
					pageBeanEmployee.getPageSize(),totalNum);
			session.setAttribute("pageBeanEmployee",pageBeanEmployee);
		}else {
			//
			pageBeanEmployee = new PageUtils<Employee>(pageBeanEmployee.getCurPage(),
					pageBeanEmployee.getPageSize(),totalNum);
			session.setAttribute("pageBeanEmployee",pageBeanEmployee);
		}
		List<Employee> empList = ies.queryByName((String)session.getAttribute("likeEmployeeKey"), 
				pageBeanEmployee.getCurPage(), pageBeanEmployee.getPageSize());
		request.setAttribute("empList", empList);
		request.getRequestDispatcher("emplist.jsp").forward(request, response);
	}

	protected void changePageSize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pageSize = request.getParameter("pageSize");
		PageUtils<Employee> pageBeanEmployee = (PageUtils<Employee>)session.getAttribute("pageBeanEmployee");
		if(null!=pageBeanEmployee) {
			pageBeanEmployee.setPageSize(Integer.valueOf(pageSize));
		}
		findByPage(request,response);
	}
	
	protected void changeLikeEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String likeEmployeeKey = request.getParameter("likeEmployeeKey");
		HttpSession session = request.getSession();
		if(null==session.getAttribute("likeEmployeeKey")) {
			findByPage(request,response);
		}else {
			session.setAttribute("likeEmployeeKey", likeEmployeeKey);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
