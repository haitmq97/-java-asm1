package me.haitmq.spring.mvc.crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;

@Component
public class SpringConstraintValidatorFactory implements ConstraintValidatorFactory {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConstraintValidatorFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
        return applicationContext.getBean(key);
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> instance) {
        // No-op
    }
}
