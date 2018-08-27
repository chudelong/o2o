package com.imooc.o2o.dto;

import java.util.List;

import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;

public class ShopException {

	// 结果状态
	private int state;

	// 状态标识
	private String statiInfo;

	// 店铺数量
	private int count;

	// 操作的shop（增删改店铺的时候用到）
	private Shop shop;

	// shop列表（查询店铺列表的时候使用）
	private List<Shop> shopList;

	
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStatiInfo() {
		return statiInfo;
	}

	public void setStatiInfo(String statiInfo) {
		this.statiInfo = statiInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}

	public ShopException() {

	}

	// 店铺操作失败的时候使用的构造器
	public ShopException(ShopStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.statiInfo = stateEnum.getStateInfo();
	}

	// 店铺操作成功的时候使用的构造器
	public ShopException(ShopStateEnum stateEnum, Shop shop) {
		this.state = stateEnum.getState();
		this.statiInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}

	// 店铺操作成功的时候使用的构造器
	public ShopException(ShopStateEnum stateEnum, List<Shop> shopList) {
		this.state = stateEnum.getState();
		this.statiInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
	}

}
