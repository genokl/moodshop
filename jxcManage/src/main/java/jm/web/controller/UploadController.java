package jm.web.controller;
//package jm.xcx.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.UUID;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//
//import com.alibaba.fastjson.JSONObject;
//
//import jm.basic.util.CommonUtils;
//import jm.basic.util.ConstantPool;
//import jm.basic.util.ImageUtil;
//import jm.basic.util.ResponseUtils;
//
///**
// * 文件上传controller
// * @author wxy
// */
//@Controller
//@RequestMapping(value = "/upload")
//public class UploadController {
//	
//
////	//上传图片 品牌   K pic  : V  图片本身
////	@RequestMapping(value = "/uploadPic.do")
////	public void uploadPic(@RequestParam(required = false) MultipartFile pic,
////			HttpServletResponse response) throws IOException{
////		JSONObject jo = new JSONObject();
////		Integer statusCode=ConstantPool.STATUS_CODE_INIT;
////		try {
////			//		System.out.println(pic.getOriginalFilename());
////			//Java接口 连接 FastDFS
////			String path = FastDFSUtils.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
////			String url = ConstantPool.IMG_URL + path;
////			statusCode=ConstantPool.STATUS_CODE_ADD;
////			//工具
////			jo.put("url", url);
////			jo.put("path", path);
////		} catch (Exception e) {
////			statusCode=ConstantPool.STATUS_EXCEPTION;
////			e.printStackTrace();
////		}
////		jo.put("statusCode", statusCode);
////		//json 
////		System.out.println(jo.toString());
////		ResponseUtils.renderJson(response, jo.toString());
////	}
////	
////	//上传图片 品牌   K pic  : V  图片本身
////	@RequestMapping(value = "/uploadPic1.do")
////	public void uploadPic1(@RequestParam(required = false) File file,
////			HttpServletResponse response) throws IOException{
////		JSONObject jo = new JSONObject();
////		Integer statusCode=ConstantPool.STATUS_CODE_INIT;
////		try {
////			//		System.out.println(pic.getOriginalFilename());
////			//Java接口 连接 FastDFS
////			String path = FastDFSUtils.uploadPic1(file);
////			String url = ConstantPool.IMG_URL + path;
////			statusCode=ConstantPool.STATUS_ADD;
////			//工具
////			jo.put("url", url);
////			jo.put("path", path);
////		} catch (Exception e) {
////			statusCode=ConstantPool.STATUS_EXCEPTION;
////			e.printStackTrace();
////		}
////		jo.put("statusCode", statusCode);
////		//json 
////		System.out.println(jo.toString());
////		ResponseUtils.renderJson(response, jo.toString());
////	}
//	
//	/**
//	 * 上传图片 
//	 * @param response
//	 * @param request
//	 * @param uid
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/uploadPic.do")
//	public void uploadPic(
//			HttpServletResponse response,
//			HttpServletRequest request,
//			String uid
//			) throws IOException{
//		
//		JSONObject jo = new JSONObject();
//		Integer statusCode=ConstantPool.STATUS_CODE_INIT;
//		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//			// 检查form中是否有enctype="multipart/form-data"
//			if (multipartResolver.isMultipart(request)) {
//				// 将request变成多部分request
//				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//				// 获取multiRequest 中所有的文件名
//				Iterator iter = multiRequest.getFileNames();
//				
//				while (iter.hasNext()) {
//					// 一次遍历所有文件
//					MultipartFile file = multiRequest.getFile(iter.next().toString());
//					if (file != null) {
//						// 设置上传文件主目录
//						String uploadPath = request.getSession().getServletContext().getRealPath("/") + "/upload/" + CommonUtils.getDate2Str(new Date(),"yyyyMMdd");
//						String s1 = request.getHeader("user-agent");
//						if(s1.contains("iPhone")) {
//							
//						}else{
//							
//						} 
//						// 生成今天的上传文件目录
//						File toDayFile = new File(uploadPath);
//						if (!toDayFile.exists()) {
//							toDayFile.mkdirs();
//						}
//						// 创建文件名称
//						File uploadFile =new File(uploadPath + "/" +UUID.randomUUID().toString()+fileSuffixName(file.getOriginalFilename()));
//						// 上传
//						file.transferTo(uploadFile);
//						ImageUtil.zoomPicWithProportion(uploadFile.getPath(), 0.85f);
//						ImageUtil.zoomPicWithProportion(uploadFile.getPath(), 0.5f);
////						ImageUtil.addWaterMarkPic(uploadFile.getPath(), 0.5f);
//						jo.put("fileUrl", "upload/" + CommonUtils.getDate2Str(new Date(),"yyyyMMdd")+"/"+uploadFile.getName());
//					}
//				}
//				statusCode=ConstantPool.STATUS_CODE_SUCCESS;
//		}
//		jo.put("statusCode", statusCode);
//		//json 
////		System.out.println(jo.toString());
//		ResponseUtils.renderJson(response, jo);
//	}
//	
//	/**
//	 * 获取文件后缀名
//	 * @param fileName  文件名
//	 * @return 返回后缀
//	 */
//	public String fileSuffixName(String fileName){
//		return fileName.substring(fileName.lastIndexOf("."));
//	}
//	
//	
//}
