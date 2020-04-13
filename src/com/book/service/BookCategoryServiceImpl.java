package com.book.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MybatisUtils;
import com.book.dao.category.BookCategoryMapper;
import com.book.dao.user.BookUserMapper;
import com.book.pojo.BookCategory;
import com.book.pojo.BookInfo;






public class BookCategoryServiceImpl implements BookcategoryService{
private SqlSession sqlSession;
	@Override
	public List<BookCategory> getList() {
		sqlSession=MybatisUtils.createSqlSession();
		List<BookCategory> list=sqlSession.getMapper(BookCategoryMapper.class).getList();
		MybatisUtils.closeSqlSession(sqlSession);
		return list;
	}
	@Override
	public boolean delcategory(int id) {
		sqlSession=MybatisUtils.createSqlSession();
		int result=sqlSession.getMapper(BookCategoryMapper.class).delete(id);
		if(result>0) {
			sqlSession.commit();
			return true;
		}
		MybatisUtils.closeSqlSession(sqlSession);
			return false;
	}
	@Override
	public boolean savecategory(BookCategory cg) {

		sqlSession=MybatisUtils.createSqlSession();
		BookCategoryMapper mapper=sqlSession.getMapper(BookCategoryMapper.class);
		int result=mapper.savecategory(cg);
		if(result>0) {
			sqlSession.commit();
			MybatisUtils.closeSqlSession(sqlSession);
			return true;
		}else {
			sqlSession.rollback();
			MybatisUtils.closeSqlSession(sqlSession);
			return false;
		}
	}


}
