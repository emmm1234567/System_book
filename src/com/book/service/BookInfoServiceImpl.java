package com.book.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MybatisUtils;
import com.book.dao.category.BookCategoryMapper;
import com.book.dao.info.BookInfoMapper;
import com.book.pojo.BookInfo;






public class BookInfoServiceImpl implements BookInfoService {
private SqlSession sqlSession;
private BookInfoMapper mapper;
private int result;
	@Override
	public List<BookInfo> getList() {
		sqlSession=MybatisUtils.createSqlSession();
		List<BookInfo> ilist=sqlSession.getMapper(BookInfoMapper.class).getList();
		MybatisUtils.closeSqlSession(sqlSession);
		return ilist;
	}
	
	@Override
	public int countInfo(String bookName) {
		sqlSession=MybatisUtils.createSqlSession();
		//���mapper����
		mapper=sqlSession.getMapper(BookInfoMapper.class);
		result=mapper.countInfo(bookName);
		MybatisUtils.closeSqlSession(sqlSession);
		return result;
	}
	@Override
	public List getPageLists(String bookName,int form, int pageSize) {
		sqlSession=MybatisUtils.createSqlSession();
		//���mapper����
		mapper=sqlSession.getMapper(BookInfoMapper.class);
		List<BookInfo> list=mapper.getPageLists(bookName,form, pageSize);
		MybatisUtils.closeSqlSession(sqlSession);
		return list;
	}
	@Override
	public List<BookInfo> findId(int id) {
		sqlSession=MybatisUtils.createSqlSession();
		List<BookInfo> list=sqlSession.getMapper(BookInfoMapper.class).findById(id);
		MybatisUtils.closeSqlSession(sqlSession);
		return list;
	}
	@Override
	public List<BookInfo> findById(int id) {
		sqlSession=MybatisUtils.createSqlSession();
		List<BookInfo> ulist=sqlSession.getMapper(BookInfoMapper.class).findupdate(id);
		MybatisUtils.closeSqlSession(sqlSession);
		return ulist;
	}
	@Override
	public boolean del(int id) {
		sqlSession=MybatisUtils.createSqlSession();
		int result=sqlSession.getMapper(BookInfoMapper.class).delete(id);
		if(result>0) {
			sqlSession.commit();
			return true;
		}
		MybatisUtils.closeSqlSession(sqlSession);
			return false;
	}
	@Override
	public boolean updateInfo(BookInfo info) {
		sqlSession=MybatisUtils.createSqlSession();
		mapper=sqlSession.getMapper(BookInfoMapper.class);
		result=mapper.updateInfo(info);
		if (result>0) {
			sqlSession.commit();
			MybatisUtils.closeSqlSession(sqlSession);
			return true;
		}else {
			return false;
		  }
	}
	@Override
	public boolean addInfo(BookInfo info) {
		sqlSession=MybatisUtils.createSqlSession();
		mapper=sqlSession.getMapper(BookInfoMapper.class);
		result=mapper.addInfo(info);
		if (result>0) {
			sqlSession.commit();
			MybatisUtils.closeSqlSession(sqlSession);
			return true;
		}else {
			return false;
		  }
	}

	@Override
	public List<BookInfo> findByname(String bookName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
