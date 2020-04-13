package com.book.service;

import java.util.List;

import com.book.pojo.BookCategory;

public interface BookcategoryService {
	List<BookCategory> getList();

	boolean delcategory(int id);

	boolean savecategory(BookCategory cg);


}
