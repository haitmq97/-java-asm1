package me.haitmq.spring.mvc.crud.utils;

import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;

public class JSPDataFormat {
	
	public static String donationStatusFormat(DonationStatus status) {
		switch(status) {
		  case NEW:
			return "Mới tạo";
		  case DONATING:
		    return "Đang quyên góp";
		  case END:
			    return "Đã kết thúc";
		  case CLOSED:
			    return "Đã đóng";
		  default:
		    return "Không xác định";
		}
	}
	
	
	public static String userStatusFormat(UserStatus status) {
		switch(status) {
		  case ACTIVE:
			return "Đang hoạt động";
		  case LOCKED:
		    return "Bị khóa";
		  default:
		    return "Không xác định";
		}
	}

}
