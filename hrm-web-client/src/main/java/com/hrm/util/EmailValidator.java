package com.hrm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hrm.ws.schema.bean.employee.Employee;

public class EmailValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public boolean supports(Class<?> clazz) {
		return Employee.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Employee employee = (Employee) target;
		if (isNotEmpty(employee.getEmail())) {
			if (!validateEmail(employee.getEmail())) {
				errors.rejectValue("email", "invalid.email",
						"Invalid email address");
			}
		} else {
			errors.rejectValue("email", "required.email", "Eamil is required.");
		}

	}
	
	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	private boolean validateEmail(final String email) {

		matcher = pattern.matcher(email);
		return matcher.matches();

	}

	private boolean isNotEmpty(String val) {
		return isNotNull(val) && (val.length() > 0);
	}

	private boolean isNotNull(String val) {
		return val != null;
	}

}
