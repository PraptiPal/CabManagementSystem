package com.cab.book.com.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ContactNumberValidator implements 
			ConstraintValidator<ContactNumberConstraint, Long> {

  @Override
  public void initialize(ContactNumberConstraint contactNumber) {
  }

  @Override
  public boolean isValid(Long contactField,
    ConstraintValidatorContext cxt) {
	  
	  String contactNumber = String.valueOf(contactField);
	  
	  int count = 0;
	  if(contactNumber.matches("[0-9]")) {
		  long temp = contactField; 
		  while(temp > 0) {
			  int d= (int)temp % 10;
			  count++;
			  temp/=10;
		  }
	  }
      return contactNumber != null 
        && count <= 10;
	  
//	  return contactNumber != null
//	          && (contactField.length() > 8) && (contactField.length() < 14);
  }

}