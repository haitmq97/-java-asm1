package me.haitmq.spring.mvc.crud.utils;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



public class ObjecMapper {
	public static <T, U> void copyCommonProperties(T source, U target) {
		Set<String> commonFieldSet = new HashSet<>();
		// lấy tất cả các trường định nghĩa trong lớp source
		for(Field field: source.getClass().getDeclaredFields()) {
			commonFieldSet.add(field.getName());
		}
		
		for(Field field:  source.getClass().getDeclaredFields()) {
			if(commonFieldSet.contains(field)) {
				if(isLazy(field)) {
					continue;
				}
				 try {
		                field.setAccessible(true);
		                Field targetField = target.getClass().getDeclaredField(field.getName());
		                targetField.setAccessible(true);
		                Object value = field.get(source);
		                targetField.set(target, value);
		            } catch (NoSuchFieldException | IllegalAccessException e) {
		                e.printStackTrace();
		            }
			}
		}
	}
	
	public static boolean isLazy(Field field) {
		if(field.isAnnotationPresent(OneToOne.class)) {
			// lấy đối tương annotation oneToOne
			 OneToOne oneToOne = field.getAnnotation(OneToOne.class);
			 return oneToOne.fetch() == FetchType.LAZY;
		}
		
		if(field.isAnnotationPresent(OneToMany.class)) {
			 OneToMany oneToMany = field.getAnnotation(OneToMany.class);
			 return oneToMany.fetch() == FetchType.LAZY;
		}
		if(field.isAnnotationPresent(OneToMany.class)) {
			 OneToMany oneToMany = field.getAnnotation(OneToMany.class);
			 return oneToMany.fetch() == FetchType.LAZY;
		}
		if(field.isAnnotationPresent(ManyToOne.class)) {
			ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
			 return manyToOne.fetch() == FetchType.LAZY;
		}
		if(field.isAnnotationPresent(ManyToMany.class)) {
			ManyToMany manyToMany = field.getAnnotation(ManyToMany.class);
			 return manyToMany.fetch() == FetchType.LAZY;
		}
		return true;
	}
}
