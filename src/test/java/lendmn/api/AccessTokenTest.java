package lendmn.api;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AccessTokenTest {
	
	private static ObjectMapper mapper;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mapper = new ObjectMapper();
	}
	
	@Test
	public void test() {
		String json = "{\n" + 
				"    \"accessToken\": \"NzBiYTgyNjc0MmU3Zjk1NDgzYTMxMGU5NDU1OGE1N2Q0NGZjMDBhNjg3NGQ3ODM4NmYwMjFkOTBmZDk0Zjg2MQ\",\n" + 
				"    \"expiresIn\": 3600,\n" + 
				"    \"scope\": \"invoice,user_name,user_phone\"\n" + 
				"  }";
		
		try {
			AccessToken token = mapper.readValue(json, AccessToken.class);
			
			assertEquals("token is correct", "NzBiYTgyNjc0MmU3Zjk1NDgzYTMxMGU5NDU1OGE1N2Q0NGZjMDBhNjg3NGQ3ODM4NmYwMjFkOTBmZDk0Zjg2MQ", token.getToken());
			assertEquals("expire time is", 3600, token.getExpiresIn().longValue());
			assertArrayEquals("scopes is correct", "invoice,user_name,user_phone".split(","), token.getScopes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEquals() {
		AccessToken expected = new AccessToken("NzBiYTgyNjc0MmU3Zjk1NDgzYTMxMGU5NDU1OGE1N2Q0NGZjMDBhNjg3NGQ3ODM4NmYwMjFkOTBmZDk0Zjg2MQ", 3600, "invoice,user_name,user_phone");
		AccessToken actual = new AccessToken("NzBiYTgyNjc0MmU3Zjk1NDgzYTMxMGU5NDU1OGE1N2Q0NGZjMDBhNjg3NGQ3ODM4NmYwMjFkOTBmZDk0Zjg2MQ", 3600, "invoice,user_name,user_phone");
		
		assertEquals(expected, actual);
		actual.setToken("bogusvalue");
		assertNotEquals(expected, actual);
		
	}
}
