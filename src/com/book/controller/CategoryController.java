package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.book.pojo.BookCategory;
import com.book.pojo.BookInfo;
import com.book.pojo.BookUser;
import com.book.pojo.Pager;
import com.book.service.BookCategoryServiceImpl;
import com.book.service.BookcategoryService;



/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private BookcategoryService bc=new BookCategoryServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("Utf-8");
		String op=req.getParameter("op");
		if("categorylist".equals(op)) {
			category(req,resp);
		}else if("dellete".equals(op)) {
			delete(req,resp);
		}else if("addcategory".equals(op)){
			add(req,resp);
		}else if("show".equals(op)) {
			show(req,resp);
		}
	}

	private void show(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//创建保存数据的集合
		List<BookCategory> list = bc.getList();
		
		req.getSession().setAttribute("list", list);
		resp.sendRedirect("admin/category-mgr.jsp");
		
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String category=req.getParameter("category");
		BookCategory cg = new BookCategory(category);
		boolean isOk = bc.savecategory(cg);
		if (isOk) {
			// 去登录
			resp.sendRedirect("CategoryController?op=show");
		}else {
			resp.sendRedirect("admin/admin-home.jsp");
		}
		
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		int id= Integer.parseInt(req.getParameter("id"));
		boolean isOK=bc.delcategory(id);
	
		if(isOK) {
			//ɾ���ɹ�
			resp.sendRedirect("CategoryController?op=show");
		}else {
			//ɾ��ʧ��
			resp.sendRedirect("admin/admin-home.jsp");
		}
		
		
	}

	private void category(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//创建保存数据的集合
				List<BookCategory> list = bc.getList();
				
				req.getSession().setAttribute("list", list);
				resp.sendRedirect("user/index.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
