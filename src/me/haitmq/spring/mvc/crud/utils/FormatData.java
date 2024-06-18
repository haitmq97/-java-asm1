package me.haitmq.spring.mvc.crud.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;

public class FormatData {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public static Date stringToDate(String data) {
		Date result;
		try {
			result = dateFormat.parse(data);
		} catch (ParseException e) {
			result = null;
		}
		return result;
	}

	public static UserStatus userStatusFormat(String intputStatus) {

		if (!intputStatus.isEmpty()) {
			if ("đang hoạt động".startsWith(intputStatus.toLowerCase().trim())) {
				return UserStatus.ACTIVE;
			} else if ("bị khóa".startsWith(intputStatus.toLowerCase().trim())) {
				return UserStatus.LOCKED;
			}
		}

		return null;
	}

	public static DonationStatus donationStatusFormat(String intputStatus) {

		if (!intputStatus.isEmpty()) {
			if ("mới tạo".startsWith(intputStatus.toLowerCase().trim())) {
				return DonationStatus.NEW;
			} else if ("đang quyên góp".startsWith(intputStatus.toLowerCase().trim())) {
				return DonationStatus.DONATING;
			} else if ("đã kết thúc".startsWith(intputStatus.toLowerCase().trim())) {
				return DonationStatus.END;
			} else if ("đã đóng".startsWith(intputStatus.toLowerCase().trim())) {
				return DonationStatus.CLOSED;
			}
		}

		return null;
	}

	public static UserDonationStatus userDonationStatusFormat(String intputStatus) {

		if (!intputStatus.isEmpty()) {
			if ("chờ xác nhận".startsWith(intputStatus.toLowerCase().trim())) {
				return UserDonationStatus.WAITING;
			} else if ("đã xác nhận".startsWith(intputStatus.toLowerCase().trim())) {
				return UserDonationStatus.CONFIRMED;
			} else if ("đã hủy".startsWith(intputStatus.toLowerCase().trim())) {
				return UserDonationStatus.CANCELED;
			}
		}

		return null;
	}
}
