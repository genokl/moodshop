package jm.service.impl;
//package jm.xcx.service.impl;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.alibaba.fastjson.JSONObject;
//
//import jm.basic.bean.Member;
//import jm.basic.dao.impl.MemberDaoImpl;
//import jm.basic.util.CommonUtils;
//import jm.xcx.service.MemberService;
//
//@Transactional
//@Service("memberService")
//public class MemberServiceImpl implements MemberService{
//
//	@Resource(name="memberDaoImpl")
//	private MemberDaoImpl memberDaoImpl;
//	
//
//	@Override
//	public Member getInstanceByID(int memberId) {
//		//  根据会员ID 查询一个会员
//		return memberDaoImpl.findById(memberId);
//	}
//
//
//	@Override
//	public Member mergeMembersByWebJSONAndXcxJSON(int jsonType, JSONObject jo, String wxAppId, int isFans) throws UnsupportedEncodingException {
//		String tOpenId=jo.getString("openId");
////		String tUnionId=jo.getString("unionId");;
//		
////		hql="from ms.basic.bean.Member where openId= '"+tOpenId+"' or unionId ='"+tUnionId+"'";
//		String sql="select * from ms_member where open_id= '"+tOpenId+"'";
//		List<Member> ml =memberDaoImpl.excuteBySqlList(sql, Member.class);
//		Member m = new Member();
//		if(ml==null||ml.size()==0){
//			//根据openId或者unionId 没有查询人任何对象
//			// 会员管理系统小程序
////			m.setUnionId(tUnionId);
//			m.setHeadImg(jo.getString("avatarUrl"));;
//			m.setSex(jo.getInteger("gender"));
//			m.setNickName(URLEncoder.encode(jo.getString("nickName"),"utf-8"));
//			m.setIsDisplayCartoon(0);
//			//添加最新的 会员对象
//			memberDaoImpl.save(m);
////			m.setUnionId(tUnionId);
//			m.setOpenId(tOpenId);
//			m.setCreateTime(new Date());
//			return m;
//		}else{
//			Member rm = ml.get(0);
//			// 会员管理系统小程序
//			rm.setHeadImg(jo.getString("avatarUrl"));
//			rm.setSex(jo.getInteger("gender"));
//			rm.setNickName(URLEncoder.encode(jo.getString("nickName"),"utf-8"));
//			return rm;
//		}
//		
//	}
//	
//}
