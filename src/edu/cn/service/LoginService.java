package edu.cn.service;

import edu.cn.domain.User;

public interface LoginService {
	public String userLogin(User user);//根据用户名和密码查询数据库返回用户类型

}
