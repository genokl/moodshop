package jm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jm.basic.bean.User;
import jm.basic.mapper.UserMapper;
import jm.service.UserService;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource(name="userMapper")
	private UserMapper userMapper;
	

	@Override
	public User getInstanceByID(int userId) {
		//  根据会员ID 查询一个会员
		return userMapper.findById(userId);
	}


		
	
}
