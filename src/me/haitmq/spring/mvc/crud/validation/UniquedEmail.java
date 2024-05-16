package me.haitmq.spring.mvc.crud.validation;

import javax.validation.Payload;

public @interface UniquedEmail {
	
	// define default message
	public String message() default "username already exists";

	// define default groups
	public Class<?>[] groups() default {};
	
	// define default payloads
	public Class<? extends Payload>[] payload() default {};
}
