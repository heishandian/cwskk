package com.zky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zky.dao.BooKshopDao;
import com.zky.service.IBookShopService;

@Service("bookShopService")
public class BookShopServiceImpl implements IBookShopService {
	@Autowired
	private BooKshopDao bookShopDao;//用接口编程
	
	@Override
	public void purchase(String username, String isbn) {
		
		// 获取数的单价
		int price = bookShopDao.selectBookPriceByIsbn(isbn);
		
		//更新库存
		bookShopDao.updateStockByIsbm(isbn);
		
		//更新用户账户余额
		bookShopDao.updateUserAccount("kk", price);
		
	}

}
