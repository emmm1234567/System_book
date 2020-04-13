package com.book.dao.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.pojo.BookCategory;
import com.book.pojo.BookInfo;

public interface BookCategoryMapper {
	List<BookCategory> getList();

	int delete(@Param("id")int id);

	int savecategory(BookCategory cg);

}
