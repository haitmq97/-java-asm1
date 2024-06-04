package me.haitmq.spring.mvc.crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;



@Component
public class SpringConstraintValidatorFactory implements ConstraintValidatorFactory {
	
	/*
	
    
    
    
	

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
        return applicationContext.getBean(key);
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> instance) {
        // No-op for Spring-managed beans
    }
	*/
	private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Autowired
    public SpringConstraintValidatorFactory(AutowireCapableBeanFactory autowireCapableBeanFactory) {
        this.autowireCapableBeanFactory = autowireCapableBeanFactory;
    }

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
        return autowireCapableBeanFactory.getBean(key);
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> instance) {
        // No-op
    }
}


