package me.haitmq.spring.mvc.crud.utils;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
}
