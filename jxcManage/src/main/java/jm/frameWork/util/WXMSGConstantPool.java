package jm.frameWork.util;

import java.util.Arrays;
import java.util.List;

/**
 * 微信消息常量池
 * @author pzg
 *
 */
public class WXMSGConstantPool {
	/**
	 * 数据提交提醒
	 */
	public  static String WX_TEMPLA_A ="Jr79S36GMIjozfX1uQbrN0p_xm--2UGWVBW2VWBNqU8";
	/**
	 * 报名审核结果通知 
	 */
	public  static String WX_TEMPLA_B ="E-Or1ajIkDJaXkEJYPxpnF2czAYsbrXleP78vjxkebk";
	/**
	 * 审核结果通知
	 */
	public  static String WX_TEMPLA_C ="RdsMuR67WHBszM7gyMxxmNCTjD3hYArKwqGEMapVli4";
	/**
	 * 成员报名付费通知
	 */
	public  static String WX_TEMPLA_D ="cKgrFoZLRiJ9mMlYi5XyQJPb-kjOeNgoPSlYEaHqsZY";
	/**
	 * 产融家测试openid
	 */
	public  static String TEST_ADMIN="o_R1VuBw-Ff0lREvOfqG0DRi49o4";
	/**
	 * 微信消息发送接口
	 */
	public  static String WX_MSG_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	/**
	 * 微信发送文本字体默认颜色
	 */
	public  static String WX_MSG_DEFAULT_COLOR="#000000"; 
	/**
	 * 微信发现消息基础默认remark 内容
	 */
	public  static String WX_MST_DEFAULT_REMARK=" ● 姓名:name\\n ● 性别：sex\\n ● 手机号码:phone\\n ● 公司名称:firmName\\n ● 公司职务:firmPost\\n ● 年龄段:ageStage";
	/**
	 * 优董活动报名状态
	 */
	private final List<String> memberWXActivitySignUpStatusList= Arrays.asList(
			"未通过","审核中","待支付","已审核","已支付","退款中","退款成功","已取消","参加活动");
	/**
	 * 根据报名状态获取中文码
	 * @param signUpStatus
	 * @return
	 */
	public String getMemberWXActivitySignUpStatusList(int signUpStatus){
		return memberWXActivitySignUpStatusList.get(signUpStatus-1);
	}
	/**
	 * 向管理推送wx消息 first 文本list
	 */
	private final static  List<String> adminWXMsgFirstList=Arrays.asList(
			"user已报名activityTitele，点击查看完整内容！",
			"user支付activityTitele报名费用，点击查看完整内容！",
			"user申请activityTitele退款，点击查看完整内容！");
	/**
	 * 根据类型获取需要的管理员 first 文本
	 * @param type 
	 * @return
	 */
	public static String getAdminWXMsgFirst(int type){
		return adminWXMsgFirstList.get(type);
	}
	/**
	 * 向管理员推送 消息 点击跳转链接
	 * 0 入群
	 * 1 活动列表
	 * 2 某一个会员填写的报名详情
	 */
	private final static List<String> adminWxMsgReturnUrl=Arrays.asList(
			"http://www.ydclub.cc/activity_queryspcount.action?&pageName=listrqsp&isflag=1&activityType=-1&activitystatus=''",
			"http://www.ydclub.cc/activity_queryspcount.action?&pageName=listbmsh&isflag=1&activityType=''&activitystatus=''",
			"http://www.ydclub.cc/signUp_toActivitySignUpPage.action?loadMemberId="
			);
	/**
	 * 根据活动类型获取 管理员微信跳转地址
	 * @param activityType 活动类型 0为跳转至用户报名详情页
	 * @return
	 */
	public static String getAdminWxMsgReturnUrl(int activityType){
		switch (activityType) {
		case -1:
			return adminWxMsgReturnUrl.get(0);
		case 0:
			return adminWxMsgReturnUrl.get(2);
		default:
			return adminWxMsgReturnUrl.get(1);
		}
	}
	
	
}
