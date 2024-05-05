package me.haitmq.spring.mvc.crud.utils;

import java.util.HashMap;
import java.util.Map;

public class DonationStatusMapper {
    private static final Map<String, String> statusMap = new HashMap<>();

    static {
        statusMap.put("NEW", "Mới tạo");
        statusMap.put("DONATING", "Đang quyên góp");
        statusMap.put("END", "Đã kết thúc");
        statusMap.put("CLOSED", "Đã đóng");
    }

    public static String getStatus(String status) {
        return statusMap.getOrDefault(status, "Không xác định");
    }
}

