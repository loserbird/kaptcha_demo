package com.topview.kaptcha_demo.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.topview.kaptcha_demo.exception.CaptchaException;
import com.topview.kaptcha_demo.util.RandomGenerator;
@Service
public class CaptchaServiceImpl implements CaptchaService,InitializingBean{
	
	//验证码生成器
	private DefaultKaptcha producer;
	
	private Map<String,String> captchaMap = new HashMap<>();
	
	private List<String> preDefinedTexts;
	
	private int textCount = 0;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		producer = new DefaultKaptcha();
		producer.setConfig(new Config(new Properties()));
	}

	@Override
	public byte[] generateCaptchaImage(String captchaKey) throws CaptchaException {
		String text = captchaMap.get(captchaKey);
		if(text == null){
			throw new CaptchaException("captcha key" +captchaKey +" not found");
		}
		BufferedImage image = producer.createImage(text);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			ImageIO.write(image, "jpg", out);
		}catch(IOException e){
			throw new CaptchaException("failed to write captcha stream" );
		}
		return out.toByteArray();
	}

	@Override
	public String generateCaptchaKey() {
		String key = RandomGenerator.getRandomString();
		String value = getCapchaText();
		captchaMap.put(key, value);
		return key;
	}

	@Override
	public boolean validateCaptcha(String captchaKey, String captchaValue) throws CaptchaException {
		String text = captchaMap.get(captchaKey);
		if(text == null){
			throw new CaptchaException("failed to write captcha stream" );
		}
		if(text.equals(captchaValue)){
			captchaMap.remove(captchaKey);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<String> getPreDefinedTexts() {
		return preDefinedTexts;
	}

	@Override
	public void setPreDefinedTexts(List<String> PreDefinedTexts) {
		this.preDefinedTexts = preDefinedTexts;
		
	}
	/**
	 * 生成验证码字符串
	 * @return
	 */
	private String getCapchaText(){
		//不为null,则循环预定义字符串列表读取值
		if(preDefinedTexts != null && !preDefinedTexts.isEmpty()){
			String text = preDefinedTexts.get(textCount);
			textCount = (textCount + 1) % preDefinedTexts.size();
			return text;
		}else{
			return producer.createText();
		}
	}

}
