package me.haitmq.spring.mvc.crud.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = ValidAmountConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAmount {
	

	// define default message
	public String message() default "Số tiền phải từ hàng nghìn";

	// define default groups
	public Class<?>[] groups() default {};
	
	// define default payloads
	public Class<? extends Payload>[] payload() default {};
}
