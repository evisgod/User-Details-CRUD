package com.videotel.test.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.videotel.test.exception.InvalidInputException;

/**
 * This is the Validation class to validate all input parameters as per the
 * business requirement I did not write any code for the validation as per the
 * requirement but we can provide the validation here.
 */
@Component
public class UserInputValidation {
	public void validCountryCode(String code) {
		if (StringUtils.isBlank(code) || code.length() != 3) {
			throw new InvalidInputException("Please provide valid 3 letter ISO code.");
		}
	}
}
