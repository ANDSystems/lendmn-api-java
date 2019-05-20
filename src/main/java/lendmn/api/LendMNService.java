package lendmn.api;

import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class LendMNService {
	static final String GET_ACCESS_TOKEN_PATH = "/api/oauth/v2/token";
	static final String OAUTH_GRANT_TYPE = "authorization_code";
	static final String SCHEME = "https";
	protected String clientId;
	protected String secret;
	protected String host;

	public LendMNService(String clientId, String secret, String host) {
		this.clientId = clientId;
		this.secret = secret;
		this.host = host;
	}

	public Client consumeCode(String redirectUri, String code) throws MalformedURLException, IOException, InvalidResponseException, ApiException {
		return this.consumeCode(code, LendMNService.GET_ACCESS_TOKEN_PATH);
	}

	public Client consumeCode(String redirectUri, String code, String getAccessTokenPath) throws MalformedURLException, IOException, InvalidResponseException, ApiException {
		return this.consumeCode(code, getAccessTokenPath, LendMNService.SCHEME);
	}

	public Client consumeCode(String redirectUri, String code, String getAccessTokenPath, String scheme) throws MalformedURLException, IOException, InvalidResponseException, ApiException {
		if(scheme == null) {
			scheme = LendMNService.SCHEME;
		}
		
		URL url = new URL(scheme, this.host, -1, getAccessTokenPath);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("client_id", this.clientId);
		params.put("client_secret", this.secret);
		params.put("redirect_uri", redirectUri);
		params.put("grant_type", LendMNService.OAUTH_GRANT_TYPE);
		params.put("code", code);

		AccessToken accessToken = LendMNService.parseResponse(HttpRequest.request(url, params));

		return new Client(this.host, accessToken);
	}

	private static AccessToken parseResponse(String httpResponse) throws InvalidResponseException, IOException, ApiException {
		JsonNode response = HttpRequest.parseResponse(httpResponse);
		
		
		return HttpRequest.mapper.convertValue(response, AccessToken.class);
	}
}
