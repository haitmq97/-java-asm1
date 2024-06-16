package me.haitmq.spring.mvc.crud.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CustomValidator {
	
	
	public static void removeFieldError(BindingResult bindingResult, String fieldName) {
	    
		// Tạo danh sách tạm thời để lưu trữ các lỗi không thuộc về field cần loại bỏ
        List<FieldError> fieldErrorsToKeep = new ArrayList<>();

        // Lặp qua tất cả các lỗi hiện có
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            if (!fieldError.getField().equals(fieldName)) {
                // Nếu lỗi không thuộc về field cần loại bỏ, thêm vào danh sách tạm thời
                fieldErrorsToKeep.add(fieldError);
            }
        }

        // Xóa tất cả các lỗi trong BindingResult
        bindingResult.getFieldErrors().forEach(error -> bindingResult.rejectValue(error.getField(), error.getCode(), error.getDefaultMessage()));

        // Thêm lại các lỗi từ danh sách tạm thời vào BindingResult
        for (FieldError fieldError : fieldErrorsToKeep) {
            bindingResult.addError(fieldError);
        }
	}
	
public static void removeFieldError2(BindingResult bindingResult, String fieldName) {
	    
		// Tạo danh sách tạm thời để lưu trữ các lỗi không thuộc về field cần loại bỏ
        List<FieldError> fieldErrorsToKeep = new ArrayList<>();

        // Lặp qua tất cả các lỗi hiện có
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
        	System.out.println("         //// the error: " + fieldError);
        	if (!fieldError.getField().equals(fieldName)) {
               System.out.println("         //// the error in field: " +  fieldError.getField());
                fieldErrorsToKeep.add(fieldError);
            }
        }

        // Xóa tất cả các lỗi trong BindingResult
        bindingResult.getFieldErrors().forEach(error -> bindingResult.rejectValue(error.getField(), error.getCode(), error.getDefaultMessage()));

        // Thêm lại các lỗi từ danh sách tạm thời vào BindingResult
        for (FieldError fieldError : fieldErrorsToKeep) {
            bindingResult.addError(fieldError);
        }
	}    
}
