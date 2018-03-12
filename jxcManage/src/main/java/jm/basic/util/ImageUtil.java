//package jm.basic.util;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
//import javax.imageio.ImageIO;
//
//import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.geometry.Positions;
//
///**
// * 
// * @author PanYu
// * 
// */
//public class ImageUtil {
//
//
//
//	/**
//	 * 根据分辨率缩放图片
//     * size(width,height) 若图片横比200小，高比300小，不变
//     * 若图片横比200小，高比300大，高缩小到300，图片比例不变 
//     * 若图片横比200大，高比300小，横缩小到200，图片比例不变
//     * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
//	 */
//    public static String zoomPicWithResolution(String imgUrl,Integer width,Integer height){
//    	
//    	//Thumbnails.of("images/test.jpg").size(2560, 2048).toFile("C:/image_2560x2048.jpg");
//    	String scaleStr=width+"x"+height;//修改部分
//    	String filefileNameNoEx=fileNameNoEx(imgUrl);//原文件不加后缀名部分
//    	String fileSuffixName=fileSuffixName(imgUrl);//原文件不加后缀名
//    	String fileDealUrl=filefileNameNoEx+"_"+scaleStr+fileSuffixName;//缩放后文件名
//    	try {
//    		Thumbnails.of(imgUrl).size(width, height).toFile(fileDealUrl);
////    		Thumbnails.of(imgUrl).scale(scale).toFile(fileDealUrl);
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
//    	System.out.println("file+"+fileDealUrl);
//    	return fileDealUrl;
//    }
//    /**
//     * 按照比例进行缩放
//     * 
//     * @throws IOException
//     */
//    public static String zoomPicWithProportion(String imgUrl,float scale){
//    	/**
//    	 * scale(比例)
//    	 */
////    	Thumbnails.of(imgUrl).scale(scale).toFile("C:/image_25%.jpg");
//    	String scaleStr=(scale*100+"").replace(".0", "");//scaleStr 等于100时为不缩放
//    	String filefileNameNoEx=fileNameNoEx(imgUrl);
//    	String fileSuffixName=fileSuffixName(imgUrl);
//    	String fileDealUrl=filefileNameNoEx+"_"+scaleStr+fileSuffixName;
//    	try {
//			Thumbnails.of(imgUrl).scale(scale).toFile(fileDealUrl);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    	System.out.println("file+"+fileDealUrl);
//    	return fileDealUrl;
//    }
//    /**
//     * 照片添加水印
//     * @throws IOException
//     */
//    public static String addWaterMarkPic(String imgUrl,float scale){
//    	/**
//    	 * scale(比例)
//    	 */
////    	Thumbnails.of(imgUrl).scale(scale).toFile("C:/image_25%.jpg");
//    	String scaleStr=(scale*100+"").replace(".0", "");//scaleStr 等于100时为不缩放
//    	String filefileNameNoEx=fileNameNoEx(imgUrl);
//    	String fileSuffixName=fileSuffixName(imgUrl);
//    	String fileDealUrl=filefileNameNoEx+"_"+scaleStr+fileSuffixName;
//    	try {
//    		System.out.println(filefileNameNoEx.substring(0, filefileNameNoEx.lastIndexOf("//")-1)+"/watermark.jpg");
//    		 Thumbnails.of(imgUrl).size(1280, 1024).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(filefileNameNoEx.substring(0, filefileNameNoEx.lastIndexOf("/"))+"/watermark.jpg")), 0.5f)
//             .outputQuality(0.8f).toFile(filefileNameNoEx+"_watermark_bottom_right"+fileSuffixName);
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
//    	System.out.println("file+"+fileDealUrl);
//    	return fileDealUrl;
//    }
//    
//    private void test2() throws IOException {
//        /**
//         * scale(比例)
//         */
//        Thumbnails.of("images/test.jpg").scale(0.25f).toFile("C:/image_25%.jpg");
//        Thumbnails.of("images/test.jpg").scale(1.10f).toFile("C:/image_110%.jpg");
//    }
//
//    /**
//     * 不按照比例，指定大小进行缩放
//     * 
//     * @throws IOException
//     */
//    private void test3() throws IOException {
//        /**
//         * keepAspectRatio(false) 默认是按照比例缩放的
//         */
//        Thumbnails.of("images/test.jpg").size(120, 120).keepAspectRatio(false).toFile("C:/image_120x120.jpg");
//    }
//
//    /**
//     * 旋转
//     * 
//     * @throws IOException
//     */
//    private void test4() throws IOException {
//        /**
//         * rotate(角度),正数：顺时针 负数：逆时针
//         */
//        Thumbnails.of("images/test.jpg").size(1280, 1024).rotate(-90).toFile("C:/iamge-90.jpg");
//    }
//
//    /**
//     * 水印
//     * 
//     * @throws IOException
//     */
//    private void test5() throws IOException {
//        /**
//         * watermark(位置，水印图，透明度)
//         */
//        Thumbnails.of("images/test.jpg").size(1280, 1024).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("images/watermark.jpg")), 0.5f)
//                .outputQuality(0.8f).toFile("C:/image_watermark_bottom_right.jpg");
//        Thumbnails.of("images/test.jpg").size(1280, 1024).watermark(Positions.CENTER, ImageIO.read(new File("images/watermark.png")), 0.5f)
//                .outputQuality(0.8f).toFile("C:/image_watermark_center.jpg");
//    }
//
//    /**
//     * 裁剪
//     * 
//     * @throws IOException
//     */
//    private void test6() throws IOException {
//        /**
//         * 图片中心400*400的区域
//         */
//        Thumbnails.of("images/test.jpg").sourceRegion(Positions.CENTER, 400, 400).size(200, 200).keepAspectRatio(false)
//                .toFile("C:/image_region_center.jpg");
//        /**
//         * 图片右下400*400的区域
//         */
//        Thumbnails.of("images/test.jpg").sourceRegion(Positions.BOTTOM_RIGHT, 400, 400).size(200, 200).keepAspectRatio(false)
//                .toFile("C:/image_region_bootom_right.jpg");
//        /**
//         * 指定坐标
//         */
//        Thumbnails.of("images/test.jpg").sourceRegion(600, 500, 400, 400).size(200, 200).keepAspectRatio(false).toFile("C:/image_region_coord.jpg");
//    }
//
//    /**
//     * 转化图像格式
//     * 
//     * @throws IOException
//     */
//    private void test7() throws IOException {
//        /**
//         * outputFormat(图像格式)
//         */
//        Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("png").toFile("C:/image_1280x1024.png");
//        Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("gif").toFile("C:/image_1280x1024.gif");
//    }
//
//    /**
//     * 输出到OutputStream
//     * 
//     * @throws IOException
//     */
//    private void test8() throws IOException {
//        /**
//         * toOutputStream(流对象)
//         */
//        OutputStream os = new FileOutputStream("C:/image_1280x1024_OutputStream.png");
//        Thumbnails.of("images/test.jpg").size(1280, 1024).toOutputStream(os);
//    }
//
//    /**
//     * 输出到BufferedImage
//     * 
//     * @throws IOException
//     */
//    private void test9() throws IOException {
//        /**
//         * asBufferedImage() 返回BufferedImage
//         */
//        BufferedImage thumbnail = Thumbnails.of("images/test.jpg").size(1280, 1024).asBufferedImage();
//        ImageIO.write(thumbnail, "jpg", new File("C:/image_1280x1024_BufferedImage.jpg"));
//    }
//    
//    /**
//	 * 获取文件后缀名
//	 * @param fileName  文件名
//	 * @return 返回后缀
//	 */
//	public static String fileSuffixName(String fileName){
//		return fileName.substring(fileName.lastIndexOf("."));
//	}
//	
//	/**
//	 * 获取文件名(排除后缀名)
//	 * @param fileName  文件名
//	 * @return 返回后缀
//	 */
//	public static String fileNameNoEx(String fileName){
//		return fileName.substring(0,fileName.lastIndexOf("."));
//	}
//}
//	 
