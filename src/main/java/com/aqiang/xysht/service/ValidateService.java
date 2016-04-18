package com.aqiang.xysht.service;

import java.util.Date;
import java.util.List;

import com.aqiang.xysht.entities.ErrorMessage;

public interface ValidateService {

	public void validate(List<ErrorMessage> errorMessages, String field, String fieldName, Object... args);

	public void validate(List<ErrorMessage> errorMessages, Date field, String fieldName, Object... args);

	public void validate(List<ErrorMessage> errorMessages, Object price, String fieldName, Object... args);

	public void validatePasswordEqRepassword(List<ErrorMessage> errorMessages, String password, String repassword);

	public void validateUsernameBeenUsed(List<ErrorMessage> messages, String username);

}
