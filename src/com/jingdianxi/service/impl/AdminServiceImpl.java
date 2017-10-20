package com.jingdianxi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingdianxi.domain.Admin;
import com.jingdianxi.mapper.AdminMapper;
import com.jingdianxi.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper adminMapper;

	@Override
	public int login(Admin admin) {
		// TODO Auto-generated method stub
		return adminMapper.login(admin);
	}

}
