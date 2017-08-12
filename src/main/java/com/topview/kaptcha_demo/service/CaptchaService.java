package com.topview.kaptcha_demo.service;

import java.util.List;

import com.topview.kaptcha_demo.exception.CaptchaException;

public interface CaptchaService {
	
	/**
	 * 生产验证码图片
	 * @param captchaKey
	 * @return
	 * @throws CaptchaException 
	 */
	byte[] generateCaptchaImage(String captchaKey) throws CaptchaException;
	
	/**
	 * 生成随机的验证码主键
	 * @return
	 */
	 String generateCaptchaKey();
	 
	 /**
	  * 校验用户反馈的主键和值
	  * @param captchaKey
	  * @param captchaValue
	  * @return
	 * @throws CaptchaException 
	  */
	 boolean validateCaptcha(String captchaKey,String captchaValue) throws CaptchaException;
	 /**
	  * 取得预定义的验证码图片内容
	  * @return
	  */
	 List<String> getPreDefinedTexts();
	 /**
	  * 预定义验证码图片的内容
	  * @param PreDefinedTexts
	  */
	 void setPreDefinedTexts(List<String> PreDefinedTexts);
}
