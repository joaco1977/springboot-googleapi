package com.asimplemodule.security.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.asimplemodule.security.converter.GoogleUserConverter;
import com.asimplemodule.security.service.GoogleUserAuthenticationService;
import com.google.api.client.auth.oauth2.Credential;

@Component
public class UserFacade {

	@Autowired
	private GoogleUserAuthenticationService googleUserAuthenticationService;

	public UserDetails getGoogleUserDetails(String authCode) {
		Credential userCredential = googleUserAuthenticationService
				.getCredentialForAuthCode(authCode);
		String googleUserJSON = googleUserAuthenticationService
				.getGoogleUser(userCredential);
		GoogleUserConverter googleUserConverter = new GoogleUserConverter();
		return googleUserConverter.convert(googleUserJSON);
	}

}
