package com.sun.proxy;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Demo {
	/**
	 * ZipFile 有好几种构造方法 
	 * 入参可以是路径path 也可以是file
	 */
	public static void readZip(String zipPath) throws Exception {
		ZipFile zipFile =  new ZipFile(zipPath);
		Enumeration<ZipEntry> enu = (Enumeration<ZipEntry>) zipFile.entries();
		while(enu.hasMoreElements()) {
			ZipEntry zipEntry = enu.nextElement();
			String name = zipEntry.getName();
			System.out.println("文件全路径:"+name);
		}
	}
	
	public static void main(String[] args) {
		try {
			readZip("D:\\test.zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
