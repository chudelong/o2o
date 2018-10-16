package com.imooc.o2o.web.frontend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.HttpServletRequestUtil;

public class ProductDetailController {

	@Autowired
	private ProductService productService;
	
	private Map<String, Object> listProductDetailPageInfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<>();
		//获取前台传过来的productId
		long productId = HttpServletRequestUtil.getLong(request, "productId");
		Product product = null;
		//空值判断
		if(productId != -1) {
			//根据productId获取商品信息，包含商品详情图列表
			product = productService.getProductById(productId);
			modelMap.put("product", product);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty productId");
		}
		return modelMap;
	}
}
