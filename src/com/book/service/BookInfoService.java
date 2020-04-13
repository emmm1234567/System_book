package com.book.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.pojo.BookInfo;

public interface BookInfoService {
	List<BookInfo> getList();
	List<BookInfo> findByname(String bookName);
	int countInfo(String bookName);
	List getPageLists(String bookName, int form,int pageSize);
	List<BookInfo> findId(int id);
	List<BookInfo> findById(int id);
	boolean del(int id);
	boolean updateInfo(BookInfo info);
	boolean addInfo(BookInfo info);
}
