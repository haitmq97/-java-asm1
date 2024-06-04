package me.haitmq.spring.mvc.crud.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class AppConfig {
	
	
    
	@Bean
    public SpringConstraintValidatorFactory springConstraintValidatorFactory(ApplicationContext applicationContext) {
        return new SpringConstraintValidatorFactory(applicationContext);
    }

    @Bean
    public Validator validator(SpringConstraintValidatorFactory springConstraintValidatorFactory) {
        ValidatorFactory validatorFactory = Validation.byDefaultProvider()
            .configure()
            .constraintValidatorFactory(springConstraintValidatorFactory)
            .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}

