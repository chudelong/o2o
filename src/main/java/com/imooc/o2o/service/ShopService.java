package com.imooc.o2o.service;

import java.io.File;
import java.io.InputStream;

import com.imooc.o2o.dto.ShopException;
import com.imooc.o2o.entity.Shop;

import exceptions.ShopOperationException;

public interface ShopService {
	/**
	 * 通过店铺Id获取店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * 更新店铺信息，包括对图片的处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopException modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
	
	ShopException addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
