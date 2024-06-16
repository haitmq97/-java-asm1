package me.haitmq.spring.mvc.crud.utils;





import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.content_path.ViewConstants;

public class SessionUtils {
	
	public static String CURRENT_ENDPOINT = "currentEndpoint";
	
	public static void setCurrentEndpoint(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		String requestUri = request.getRequestURI();
		
		String contextPath = request.getContextPath();
		String endpoint = requestUri.substring(contextPath.length());

		String query = request.getQueryString();
		
		/*
		String sizeRegex = "size=\\d+";
		String pageRegex = "page=\\d+";
		String searchingValueRegex = "searchingValue=[^&]*";
		String idRegex = "id=\\d+";
		
		
		
		Pattern sizePattern = Pattern.compile(sizeRegex);
		Pattern pagePattern = Pattern.compile(pageRegex);
		Pattern searchingValuePattern = Pattern.compile(searchingValueRegex);
		*/
		List<String> regexList = new ArrayList<>(Arrays.asList("size=\\d+", "page=\\d+", "searchingValue=[^&]*")) ;
		
		if(request.getServletPath().equals(ViewConstants.E_ADMIN_USER_DETAIL) 
				|| request.getServletPath().equals(ViewConstants.E_ADMIN_DONATION_DETAIL)
				|| request.getServletPath().equals(ViewConstants.E_DONATION_DETAIL)) {
			regexList.add("id=\\d+");
			
		}
		
		StringBuilder theFormatQuery = new StringBuilder(""); 
		if(query!= null && !query.isEmpty() ) {
			
			for(String regex: regexList) {
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(query);
				if(matcher.find()) {
					if(theFormatQuery.length() !=0) {
						theFormatQuery.append("&");
					}
					
					theFormatQuery.append(matcher.group());
				}
			}
			
			
			if(!theFormatQuery.isEmpty()) {
				endpoint += "?" +theFormatQuery.toString();
			}
			
			
			/*
			if(request.getServletPath().equals(ViewConstants.E_ADMIN_USER_DETAIL) 
					|| request.getServletPath().equals(ViewConstants.E_ADMIN_DONATION_DETAIL)
					|| request.getServletPath().equals(ViewConstants.E_DONATION_DETAIL)) {
				
				
			}
			*/
			//endpoint+= "?" + query;
			//StringBuilder sBuilder = new StringBuilder(query);
			
			
			
			//System.out.println("...........................in setCurrentEndpoint endpoint test2: " + endpoint);
			
		} else {
			//System.out.println("test here:.................................query: false");
		}
		
		

		/*
		System.out.println("test here:.................................");
		
		System.out.println("test here:................................. requestURi:" + requestUri);
		System.out.println("test here:................................. contentPath:" + contextPath);
		System.out.println("test here:................................. query:" + query);
		System.out.println("test here:................................. schema:" + request.getScheme());
		System.out.println("test here:................................. serverName :" + request.getServerName());
		System.out.println("test here:................................. serverPort  :" + request.getServerPort());
		System.out.println("test here:................................. servletPath :" + request.getServletPath());
		
		System.out.println("test here:................................. servletPath class:" + request.getServletPath().getClass());
		System.out.println("test here:................................. queryString :" + request.getQueryString());
		
		System.out.println("...........................in setCurrentEndpoint endpoint test3: " + endpoint);
		

		*/

		session.setAttribute(CURRENT_ENDPOINT, endpoint);
		/*
		System.out.println("...........................in setCurrentEndpoint CURRENT_ENDPOINT: " + session.getAttribute(CURRENT_ENDPOINT));
		*/
	}
	
	public static String getCurrentEndpoint(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		//System.out.println("...........................in getCurrentEndpoint endpoint: " +  session.getAttribute(CURRENT_ENDPOINT));
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
