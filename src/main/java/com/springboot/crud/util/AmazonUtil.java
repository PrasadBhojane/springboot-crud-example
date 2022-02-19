package com.springboot.crud.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonUtil {

	@Value("${masterToken}")
	private String masterToken;
	
	public boolean isTokenValid(String token) {
		
		if (token.equals(masterToken)) {
			return true;
		}
		return false;
	}
	
}
