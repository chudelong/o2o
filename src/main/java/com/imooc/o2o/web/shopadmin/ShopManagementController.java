package com.imooc.o2o.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ShopException;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;

import exceptions.ShopOperationException;

/**
 * 注册店铺操作
 * @author chudelong
 *
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<>();
		//1.接收并转化相应的参数，包括店铺信息以及图片
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		// 店铺门面图片地址
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg 
			= (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		
		//2.注册店铺
		if(shop != null && shopImg != null) {
			PersonInfo owner = new PersonInfo();
			// Session TODO
			owner.setUserId(1l);
			shop.setOwner(owner);
			
//			File shopImgFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
//			try {
//				shopImgFile.createNewFile();
//			} catch (IOException e) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", e.getMessage());
//				return modelMap;
//			}
//			try {
//				inputStreamToFile(shopImg.getInputStream(), shopImgFile);
//			} catch (IOException e) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", "请输入店铺信息");
//				return modelMap;
//			}
			ShopException se;
			try {
				se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
				if(se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStatiInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}
	
//	//将CommonsMultipartFile转换成File
//	private static void inputStreamToFile(InputStream ins, File file) {
//		FileOutputStream os = null;
//		try {
//			os = new FileOutputStream(file);
//			int bytesRead = 0;
//			byte[] buffer = new byte[1024];
//			while((bytesRead = ins.read(buffer)) != -1) {
//				os.write(buffer, 0, bytesRead);
//			}
//			
//		} catch(Exception e) {
//			throw new RuntimeException("调用inputStreamToFile产生异常" + e.getMessage());
//		} finally {
//			try {
//				if(os != null) {
//					os.close();
//				}
//				if(ins != null) {
//					ins.close();
//				}
//			} catch(IOException e) {
//				throw new RuntimeException("inputStreamToFile关闭io产生异常"+e.getMessage());
//			}
//		}
//	}
}