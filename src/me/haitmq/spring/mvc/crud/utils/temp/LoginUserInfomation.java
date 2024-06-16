package me.haitmq.spring.mvc.crud.utils.temp;


import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import me.haitmq.spring.mvc.crud.common.LoginUser;

public class LoginUserInfomation {
	
	public static void SetLoginUserInfoToSession(HttpSession session, LoginUser loginUser) {
		
	}
	
	public static Integer getCurrentUserId(HttpSession session) {
		return (Integer) session.getAttribute("currentUserId");
	}
	
	
	public static void addLoginUserInfoToModel(HttpSession session, Model theModel) {
		theModel.addAttribute("isLogined", session.getAttribute("isLogined"));
		theModel.addAttribute("isActive", session.getAttribute("isActive"));
		theModel.addAttribute("isAdmin", session.getAttribute("isAdmin"));
		theModel.addAttribute("currentUserId", session.getAttribute("currentUserId"));
	}
	
	public static void removeLoginUserInfoFromSesstion(HttpSession session) {
		session.removeAttribute("isLogined");
		session.removeAttribute("isActive");
		session.removeAttribute("isAdmin");
		session.removeAttribute("currentUserId");
	}
}
