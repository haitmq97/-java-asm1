package me.haitmq.spring.mvc.crud.content_path;

public final class ViewConstants {
    // view
	
	//public
    public static final String V_HOME = "public/donation-table";
    public static final String V_PUBLIC_DONATION_DETAILS = "public/donation-detail";
    
    public static final String V_LOGIN = "common/login-form";
    public static final String V_REGISTER = "common/register-form";
    public static final String V_ERROR = "common/error-page";
    public static final String V_USER_PROFILE = "profile";
    public static final String V_PRODUCT_DETAIL = "productDetail";
    
    // admin
    // donation
    public static final String V_ADMIN_DONATIONS = "admin/donation-table";
    public static final String V_ADMIN_DONATION_DETAIL = "admin/donation-detail";
    
    // user
    public static final String V_ADMIN_USERS = "admin/user-table";
    public static final String V_ADMIN_USER_DETAIL = "admin/user-detail";
    
    // userDonation
    
    public static final String V_ADMIN_USERDONATION = "admin/userDonation-table";
    
    
    // Đường dẫn chuyển hướng
    public static final String V_REDIRECT_HOME = "redirect:/v1/home";
    public static final String V_REDIRECT_LOGIN = "redirect:/login";
    public static final String V_REDIRECT_USER_PROFILE = "redirect:/profile";
    public static final String V_REDIRECT_PRODUCT_DETAIL = "redirect:/productDetail";
    
    public static final String V_REDIRECT_ADMIN_DONATIONS = "redirect:/admin/donations";
    public static final String V_REDIRECT_ADMIN_USERS = "redirect:/admin/users";
    
    public static final String V_REDIRECT_ADMIN_USERDONATIONS = "redirect:/admin/user_donations";
    
    
    // endpoint
    public static final String E_HOME = "/v1/home";
    public static final String E_LOGIN = "/v1/login";
    public static final String E_LOGOUT = "/v1/logout";
    public static final String E_REGISTER = "/v1/register";
    
    public static final String E_DONATION_DETAIL = "/v1/donation-detail";
    
    
    public static final String E_USER_DETAIL = "/v1/user-detail";
    
    
    public static final String E_ADMIN_DONATIONS = "/admin/donations";
    public static final String E_ADMIN_DONATION_DETAIL = "/admin/donation-detail";
    
    
    public static final String E_ADMIN_USERS = "/admin/users";
    public static final String E_ADMIN_USER_DETAIL = "/admin/user-detail";
    
    
    public static final String E_ADMIN_USERDONATIONS = "/admin/user_donations";
    
    public static final String E_USER_PROFILE = "/user/profile";
    
    

    private ViewConstants() {
        throw new AssertionError();
    }
}
