package com.imooc.o2o.dao;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.PersonInfo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonInfoDaoTest extends BaseTest{
	@Autowired
	private PersonInfoDao personInfoDao;
	
	@Test
	public void testAInsertPersonInfo() throws Exception{
		//设置新增的用户信息
		PersonInfo personInfo = new PersonInfo();
		personInfo.setName("我爱你");
		personInfo.setGender("男");
		personInfo.setUserType(1);
		personInfo.setCreateTime(new Date());
		personInfo.setLastEditTime(new Date());
		personInfo.setEnableStatus(1);
		int effectNum = personInfoDao.insertPersonInfo(personInfo);
	}
	
	@Test
	public void testBQueryInfoById( ) {
		long userId = 1;
		// 查询Id为1的用户信息
		PersonInfo person = personInfoDao.queryPersonInfoById(userId);
		System.out.println(person.getName());
	}

}































