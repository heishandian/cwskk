package com.zky.dao;

public interface BooKshopDao {

	public int selectBookPriceByIsbn(String isbn);
	
	//更新库存
	public void updateStockByIsbm(String isbn);
	
	//更新账户余额
	
	public void updateUserAccount(String username,int price);
	
}
