package jm.service;

import jm.basic.bean.User;

public interface UserService {

	/**
	 * 通过id查找对象
	 * @param memberId
	 * @return
	 */
	User getInstanceByID(int userId);

//	User getInstanceByUserName(String UserName);



}
