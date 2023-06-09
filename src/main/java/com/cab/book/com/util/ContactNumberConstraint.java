package com.cab.book.com.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ContactNumberValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface ContactNumberConstraint {
	 String message() default "Invalid phone number";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
}
