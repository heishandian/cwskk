package com.zky.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zky.dao.BooKshopDao;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BooKshopDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int selectBookPriceByIsbn(String isbn) {
		String sql = "select price from bookshop where isbn = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class,isbn);
	}

	@Override
	public void updateStockByIsbm(String isbn) {
		String sql = "update bookshop set amount = amount - 1 where isbn = ?";
		jdbcTemplate.update(sql,isbn);
	}

	@Override
	public void updateUserAccount(String username, int price) {
		String sql = "update account set acount = acount - 1 where name = ?";
		jdbcTemplate.update(sql,price);

	}

}
