package me.haitmq.spring.mvc.crud.utils;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import me.haitmq.spring.mvc.crud.common.LoginUser;

public class SessionUtils {
	
	public static String CURRENT_ENDPOINT = "currentEndpoint";
	
	public static void setCurrentEndpoint(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		String requestUri = request.getRequestURI();
		
		String contextPath = request.getContextPath();
		String endpoint = requestUri.substring(contextPath.length());

		String query = request.getQueryString();
		
		if(query!= null && !query.isEmpty() ) {
			endpoint+= "?" + query;
			System.out.println("test here:.................................query: true");
		} else {
			System.out.println("test here:.................................query: false");
		}


		System.out.println("test here:.................................");
		
		System.out.println("test here:................................. requestURi:" + requestUri);
		System.out.println("test here:................................. contentPath:" + contextPath);
		System.out.println("test here:................................. query:" + query);
		

		

		

		session.setAttribute(CURRENT_ENDPOINT, endpoint);
		
	}
	
	public static String getCurrentEndpoint(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		return (String) session.getAttribute(CURRENT_ENDPOINT);
	}
	
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
	
	public static void setLoginUserInfoToSesstion(HttpSession session, 
			boolean islogined, 
			boolean isActive, 
			boolean isAdmin, int currentUserId) {
		session.setAttribute("isLogined", islogined);
		session.setAttribute("isActive", isActive);
		session.setAttribute("isAdmin", isAdmin);
		session.setAttribute("currentUserId", currentUserId);
	}

	public static void removeLoginUserInfoFromSesstion(HttpSession session) {
		session.removeAttribute("isLogined");
		session.removeAttribute("isActive");
		session.removeAttribute("isAdmin");
		session.removeAttribute("currentUserId");
	}

}
