package jm.basic.mapper;

import jm.basic.bean.User;

public interface UserMapper {
	
	/**
	 * 添加追加up币记录
	 * @param activityReward
	 * @return
	 * @author sxl
	 * @Time 2017年8月28日 下午1:59:45
	 */
	User findById(Integer userId);
}
