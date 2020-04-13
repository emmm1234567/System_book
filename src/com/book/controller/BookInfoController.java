package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.book.pojo.BookCategory;
import com.book.pojo.BookInfo;
import com.book.pojo.Pager;
import com.book.service.BookInfoService;
import com.book.service.BookInfoServiceImpl;





/**
 * Servlet implementation class BookInfoController
 */
@WebServlet("/BookInfoController")
public class BookInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private BookInfoService bf=new BookInfoServiceImpl();
       private List<BookInfo> list;
   	private boolean isPass;
   	private BookInfo infos;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("Utf-8");
		String op=req.getParameter("op");
		 if("info".equals(op)) {
			info(req,resp);
		}else if("findbyid".equals(op)) {
			findbyid(req,resp);
		}else if("show".equals(op)) {
			show(req,resp);
		}else if("delete".equals(op)) {
			delete(req,resp);
		}else if("findupdate".equals(op)) {
			findupdate(req,resp);
		}else if("update".equals(op)) {
			update(req,resp);
		}else if("add".equals(op)) {
			add(req,resp);
		}
	}
	

	private void add(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String bookName=null; 
			String author=null;
			String categoryId=null;
			String publisher=null; 
			String price=null;
			String photo=null;
			// 设置上传的文件路径
			String filePath = this.getServletContext().getRealPath("/static/file");
			// 验证表单是否是采用的Multipart/form-data的格式进行文件上传 enctype的值
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			// 判断是否是采用的二进制文件流的形式做文件上传
			if(isMultipart) {
				// 创建一个用于文件上传的工厂对象
				FileItemFactory fac = new DiskFileItemFactory();
				// 利用工厂对象创建一个用于解析文件上传的对象
				ServletFileUpload upload = new ServletFileUpload(fac);
				try {
					// 使用文件上传对象来获得表单中的所有请求
					List<FileItem> items = upload.parseRequest(req);
					// 遍历整个集合 Iterator
					Iterator<FileItem> it = items.iterator();
					// 遍历整个的items集合
					while (it.hasNext()) {// 集合中是否有元素
						// 获得表单中的元素
						FileItem item = it.next();// 取出集合中元素
						// getFieldName() 获得表单的name值
						//System.out.println(item.getFieldName());
						// 判断比表单中的元素是上传元素表单还是普通文本表单
						if(item.isFormField()) {// 它是一个普通表单
							String name = item.getFieldName();// 得到表单的name值
							// 根据name值，为上面的变量赋值
							switch (name) {
							case "bookName":
								bookName = item.getString("UTF-8");
								break;
							case "author":
								author = item.getString("UTF-8");
								break;
							case "categoryId":
								categoryId = item.getString("UTF-8");
								break;
							case "publisher":
								publisher = item.getString("UTF-8");
								break;
							case "price":
								price = item.getString("UTF-8");
								break;
							}
						}else {
							// 它是上传元素表单
							// 保存上传文件的名称
							photo = item.getName();
							// 生成一个随机的唯一标识值
							UUID rand = UUID.randomUUID();
							photo=rand+photo;
							// 创建一个文件对象，来保存这个要上传的文件内容
							File saveFile = new File(filePath, photo);
							// 做文件上传
							// 调用item对象的write方法，将文件写入到服务器
							item.write(saveFile);
							photo="static/file/"+photo;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				infos=new BookInfo(bookName, author, Integer.parseInt(categoryId), publisher, Double.parseDouble(price), photo);
			    isPass=bf.addInfo(infos);
			    if(isPass) {
			    	//添加成功
			    	resp.sendRedirect("BookInfoController?op=show");
			    }else {
			    	resp.sendRedirect("admin/admin-home.jsp");
			    }
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String id=null;
			String bookName=null; 
			String author=null;
			String categoryId=null;
			String publisher=null; 
			String price=null;
			String photo=null;
			// 设置上传的文件路径
			String filePath = this.getServletContext().getRealPath("/static/file");
			// 验证表单是否是采用的Multipart/form-data的格式进行文件上传 enctype的值
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			// 判断是否是采用的二进制文件流的形式做文件上传
			if(isMultipart) {
				// 创建一个用于文件上传的工厂对象
				FileItemFactory fac = new DiskFileItemFactory();
				// 利用工厂对象创建一个用于解析文件上传的对象
				ServletFileUpload upload = new ServletFileUpload(fac);
				try {
					// 使用文件上传对象来获得表单中的所有请求
					List<FileItem> items = upload.parseRequest(req);
					// 遍历整个集合 Iterator
					Iterator<FileItem> it = items.iterator();
					// 遍历整个的items集合
					while (it.hasNext()) {// 集合中是否有元素
						// 获得表单中的元素
						FileItem item = it.next();// 取出集合中元素
						// getFieldName() 获得表单的name值
						//System.out.println(item.getFieldName());
						// 判断比表单中的元素是上传元素表单还是普通文本表单
						if(item.isFormField()) {// 它是一个普通表单
							String name = item.getFieldName();// 得到表单的name值
							// 根据name值，为上面的变量赋值
							switch (name) {
							case "id":
								id = item.getString("UTF-8");
								break;
							case "bookName":
								bookName = item.getString("UTF-8");
								break;
							case "author":
								author = item.getString("UTF-8");
								break;
							case "categoryId":
								categoryId = item.getString("UTF-8");
								break;
							case "publisher":
								publisher = item.getString("UTF-8");
								break;
							case "price":
								price = item.getString("UTF-8");
								break;
							}
						}else {
							// 它是上传元素表单
							// 保存上传文件的名称
							photo = item.getName();
							// 生成一个随机的唯一标识值
							UUID rand = UUID.randomUUID();
							photo=rand+photo;
							// 创建一个文件对象，来保存这个要上传的文件内容
							File saveFile = new File(filePath, photo);
							// 做文件上传
							// 调用item对象的write方法，将文件写入到服务器
							item.write(saveFile);
							photo="static/file/"+photo;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				infos=new BookInfo(Integer.parseInt(id),bookName, author, Integer.parseInt(categoryId), publisher, Double.parseDouble(price), photo);
			    isPass=bf.updateInfo(infos);
			    if(isPass) {
			    	//添加成功
			    	resp.sendRedirect("BookInfoController?op=show");
			    }else {
			    	resp.sendRedirect("admin/admin-edit.jsp");
			    }
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		


	private void findupdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		List<BookInfo> ulist=bf.findById(id);
		req.getSession().setAttribute("ulist", ulist);
		resp.sendRedirect("admin/book-edit.jsp");
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		boolean isOK=bf.del(id);
		
		if(isOK) {
			//ɾ���ɹ�
			resp.sendRedirect("BookInfoController?op=show");
		}else {
			//ɾ��ʧ��
			resp.sendRedirect("admin/admin-home.jsp");
		}
	}

	private void show(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<BookInfo> ilist=bf.getList();
		req.getSession().setAttribute("ilist", ilist);
		resp.sendRedirect("admin/book-mgr.jsp");
		
	}

	private void findbyid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		List<BookInfo> list=bf.findId(id);
		Pager pg=new Pager();
		List pageLists=bf.findId(id);
		pg.setPageLists(pageLists);
		if(list!=null) {
			req.getSession().setAttribute("pg", pg);
			resp.sendRedirect("user/index.jsp");
		}
		
	}

	@SuppressWarnings("unchecked")
	private void info(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡҳ��
		String pageIndex=req.getParameter("pageIndex");
		String bookName = req.getParameter("bookName");
		int currpage=0; 
		//����һ����ҳ����
		Pager pg=new Pager();
		//��ȡ����������
		int totalCount=bf.countInfo(bookName);
		pg.setTotalCount(totalCount);
		if(pageIndex==null || "".equals(pageIndex)) {
			currpage=1;
		}else {
			int num=Integer.parseInt(pageIndex);
			if(num<=0) {
				currpage=1;
			}else if(num>=pg.getTotalPages()) {
				currpage=pg.getTotalPages();
			}else {
				currpage=num;
			}
		}
		pg.setCurrPage(currpage);
		int form=(currpage-1)*pg.getTotalPages();
		@SuppressWarnings("rawtypes")
		List lists=bf.getPageLists(bookName,form, pg.getPageSize());
		pg.setPageLists(lists);
		req.getSession().setAttribute("pg", pg);
		req.getRequestDispatcher("user/index.jsp").forward(req, resp);
	}

	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
