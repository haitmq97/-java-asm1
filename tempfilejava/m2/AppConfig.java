package me.haitmq.spring.mvc.crud.config;


import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import java.util.Map;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/*
@Configuration
public class AppConfig {
	
	@Bean
    public Validator validator(SpringConstraintValidatorFactory springConstraintValidatorFactory) {
        ValidatorFactory validatorFactory = Validation.byDefaultProvider()
            .configure()
            .constraintValidatorFactory(springConstraintValidatorFactory)
            .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}

*/


@Configuration
public class AppConfig {

    
    
    
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;
    
    
    /*
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setConstraintValidatorFactory(new MySpringConstraintValidatorFactory(beanFactory));
        return validatorFactoryBean;
    }
    */
    
    /*
    

    
    @Bean
    public Validator validator(SpringConstraintValidatorFactory springConstraintValidatorFactory) {
        ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .configure()
                .constraintValidatorFactory(springConstraintValidatorFactory)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
        */
    
    @Bean
    public Validator validator(final AutowireCapableBeanFactory autowireCapableBeanFactory) {

        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
            .configure().constraintValidatorFactory((ConstraintValidatorFactory) new SpringConstraintValidatorFactory(autowireCapableBeanFactory))
            .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }
    
   
}