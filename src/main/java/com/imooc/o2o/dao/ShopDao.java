package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {

	/**
	 * 分页查询店铺，可输入的条件有：店铺名（模糊），店铺转态，店铺类别，区域Id,owner
	 * @param shopCondition
	 * @param rowIndex
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	/**
	 * 返回queryShopList总数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	
	/**
	 * 通过shop id查询店铺
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);
	
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	/**
	 * 更新店铺信息
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
