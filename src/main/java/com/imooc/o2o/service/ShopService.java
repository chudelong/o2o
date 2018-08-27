package com.imooc.o2o.service;

import java.io.File;
import java.io.InputStream;

import com.imooc.o2o.dto.ShopException;
import com.imooc.o2o.entity.Shop;

import exceptions.ShopOperationException;

public interface ShopService {

	ShopException addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
