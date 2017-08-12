package com.topview.kaptcha_demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.topview.kaptcha_demo.exception.CaptchaException;
import com.topview.kaptcha_demo.service.CaptchaService;
import com.topview.kaptcha_demo.util.RandomGenerator;

@Controller
public class CaptchaController {
	
	@Autowired
	CaptchaService captchaService;
	
	
	@RequestMapping("/validate")
	@ResponseBody
	public String validate(String validateCode,HttpSession session) throws CaptchaException{
		System.out.println(validateCode);
		String captchaKey = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(captchaKey != null && captchaKey.equals(validateCode)) return "true";
		return "false";
	}
	
	@RequestMapping("/validate2")
	@ResponseBody
	public String validate2(String validateCode,HttpSession session) throws CaptchaException{
		System.out.println(validateCode);
		String captchaKey = (String) session.getAttribute("myCaptchaKey");
		if(captchaService.validateCaptcha(captchaKey, validateCode)) return "true";
		return "false";
	}
	
	
	@RequestMapping("/generate")
	@ResponseBody
	public byte[] generateImage(HttpServletResponse response,HttpSession session) throws IOException, CaptchaException{
		System.out.println("generateImage");
		String captchaKey =  captchaService.generateCaptchaKey();
		session.setAttribute("myCaptchaKey", captchaKey);
		return captchaService.generateCaptchaImage(captchaKey);
		//response.getOutputStream().write(captchaService.generateCaptchaImage(captchaKey));
	}
}
