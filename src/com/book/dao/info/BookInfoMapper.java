package com.book.dao.info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.pojo.BookInfo;

public interface BookInfoMapper {
	//获取表里全部数据
	List<BookInfo> getList();
	//模糊查询
	
	List<BookInfo> getPageLists(@Param("bookName") String bookName,@Param("form") int form,@Param("pageSize") int pageSize);
	int countInfo(@Param("bookName") String bookName);
	List<BookInfo> findById(@Param("id") int id);
	List<BookInfo> findupdate(@Param("id") int id);
	int delete(@Param("id") int id);
	int updateInfo(BookInfo info);
	int addInfo(BookInfo info);
}
