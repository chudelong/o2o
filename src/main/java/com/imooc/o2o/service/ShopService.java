package com.imooc.o2o.service;

import java.io.File;

import com.imooc.o2o.dto.ShopException;
import com.imooc.o2o.entity.Shop;

public interface ShopService {

	ShopException addShop(Shop shop, File shopImg);
}
