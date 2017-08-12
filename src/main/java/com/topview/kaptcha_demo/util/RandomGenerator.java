package com.topview.kaptcha_demo.util;

import java.util.Random;

public class RandomGenerator {
	private static String range = "0123456789abckdefhijklmnopqrstuvwxyz";
	
	/**
	 * 随机生成一个长度为8的字符串
	 * @return
	 */
	public static synchronized String getRandomString(){
		Random random = new Random();
		StringBuffer result = new StringBuffer();
		
		for(int i=0;i<8;i++){
			result.append(range.charAt(random.nextInt(range.length())));
		}
		return result.toString();
	}
}
