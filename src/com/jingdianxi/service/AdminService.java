package com.jingdianxi.service;

import com.jingdianxi.domain.Admin;

public interface AdminService {
	// 管理员登录，返回管理员id
	int login(Admin admin);
}
