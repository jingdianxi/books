package com.jingdianxi.mapper;

import com.jingdianxi.domain.Admin;

public interface AdminMapper {
	// 管理员登录，返回管理员id
	int login(Admin admin);
}
