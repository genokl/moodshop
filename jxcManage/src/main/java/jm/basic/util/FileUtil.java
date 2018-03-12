package jm.basic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {

	/**
	 * 传入文件对象
	 * 返回对应的字节数组
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] getContent(File file) throws IOException {
//			File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
		&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
		throw new IOException("Could not completely read file "+ file.getName());
		}
		fi.close();
		return buffer;
	}
	
	/**
	 * 获取文件后缀名
	 * @param fileName  文件名
	 * @return 返回后缀
	 */
	public static String fileSuffixName(String fileName){
		return fileName.substring(fileName.lastIndexOf("."));
	}

}
