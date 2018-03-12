package jm.basic.bean;

import java.util.Date;

/**
 * 用户基本信息
 * 
 * @author admin
 *
 */
public class User {

	private Integer id;// 用户ID
	private String userName;// 用户手机号
	private Integer sex;// 性别 0:女,1:男,2:保密
	private String userImg;// 用户头像地址
	private String personnelNumber;// 人员编号
	private Integer mobileValue;// 手机号
	private String mailValue;// 邮箱
	private String idcardValue;// 身份证
	private String loginPasswordValue;//登录密码
	private Integer userRoleId;// 用户权限id
	private String authCodeValue;// 授权码
	private Date recentLandingDate;// 最近登录日期
	private Integer operatorUserInfoId;// 操作人:用户id
	private Integer merchantBaseInfoId;// 所属商户：商户id
	private Integer delFlag;// 状态(Def 0，0:正常,1:删除)
	private Integer userStatus;// 人员状态(Def 0，0:正常,1:关闭)
	private Date createdTime;// 创建时间
	private Date lastUpdatedTime;// 修改时间
	private Integer userRegisterType;// 用户注册方式：1web网站注册
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getPersonnelNumber() {
		return personnelNumber;
	}
	public void setPersonnelNumber(String personnelNumber) {
		this.personnelNumber = personnelNumber;
	}
	public Integer getMobileValue() {
		return mobileValue;
	}
	public void setMobileValue(Integer mobileValue) {
		this.mobileValue = mobileValue;
	}
	public String getMailValue() {
		return mailValue;
	}
	public void setMailValue(String mailValue) {
		this.mailValue = mailValue;
	}
	public String getIdcardValue() {
		return idcardValue;
	}
	public void setIdcardValue(String idcardValue) {
		this.idcardValue = idcardValue;
	}
	public String getLoginPasswordValue() {
		return loginPasswordValue;
	}
	public void setLoginPasswordValue(String loginPasswordValue) {
		this.loginPasswordValue = loginPasswordValue;
	}
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getAuthCodeValue() {
		return authCodeValue;
	}
	public void setAuthCodeValue(String authCodeValue) {
		this.authCodeValue = authCodeValue;
	}
	public Date getRecentLandingDate() {
		return recentLandingDate;
	}
	public void setRecentLandingDate(Date recentLandingDate) {
		this.recentLandingDate = recentLandingDate;
	}
	public Integer getOperatorUserInfoId() {
		return operatorUserInfoId;
	}
	public void setOperatorUserInfoId(Integer operatorUserInfoId) {
		this.operatorUserInfoId = operatorUserInfoId;
	}
	public Integer getMerchantBaseInfoId() {
		return merchantBaseInfoId;
	}
	public void setMerchantBaseInfoId(Integer merchantBaseInfoId) {
		this.merchantBaseInfoId = merchantBaseInfoId;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public Integer getUserRegisterType() {
		return userRegisterType;
	}
	public void setUserRegisterType(Integer userRegisterType) {
		this.userRegisterType = userRegisterType;
	}
	
	
	
	
}
