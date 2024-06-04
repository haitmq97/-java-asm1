package me.haitmq.spring.mvc.crud.config;

import javax.validation.ConstraintValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

public class MySpringConstraintValidatorFactory extends SpringConstraintValidatorFactory {

	@Autowired
    private AutowireCapableBeanFactory beanFactory;

    public MySpringConstraintValidatorFactory(AutowireCapableBeanFactory beanFactory) {
        super(beanFactory); // Gọi constructor của lớp cha SpringConstraintValidatorFactory
        this.beanFactory = beanFactory;
    }

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
        // Attempt to retrieve the bean from the Spring application context
        T instance = beanFactory.getBean(key);
        if (instance == null) {
            // If not found, create a new instance and inject dependencies
            instance = beanFactory.createBean(key);
        }
        return instance;
    }
}