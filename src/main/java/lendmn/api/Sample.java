package lendmn.api;


import java.io.IOException;
import java.net.MalformedURLException;

import lendmn.api.Client; 
import lendmn.api.LendMNService;
import lendmn.api.InvalidResponseException;

public class Sample {
	private static String host = "mgw.test.lending.mn";
	
	private static LendMNService service;

	public static void main(String[] args) {
		service = new LendMNService("128_kCaox7SYU2uUrngjvexN", "EFDLT4j1hK", host);
		
		try {
			Client client = service.consumeCode("http://localhost/", "XK6FFqg4rNab05d");
		} catch (ApiException e) {
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		} catch (InvalidResponseException e) {
		}
	}
}