package lendmn.api;

import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class Service {
	static final String GET_ACCESS_TOKEN_PATH = "/api/oauth/v2/token";
	static final String OAUTH_GRANT_TYPE = "authorization_code";
	protected String clientId;
	protected String secret;
	protected String redirectUri;
	protected String host;

	public Service(String clientId, String secret, String redirectUri, String host) {
		this.clientId = clientId;
		this.secret = secret;
		this.redirectUri = redirectUri;
		this.host = host;
	}

	public Client consumeCode(String code) throws MalformedURLException, IOException, InvalidResponseException, ApiException {
		return this.consumeCode(code, Service.GET_ACCESS_TOKEN_PATH);
	}

	public Client consumeCode(String code, String getAccessTokenPath) throws MalformedURLException, IOException, InvalidResponseException, ApiException {

		URL url = new URL("https", this.host, -1, getAccessTokenPath);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("client_id", this.clientId);
		params.put("client_secret", this.secret);
		params.put("redirect_uri", this.clientId);
		params.put("grant_type", Service.OAUTH_GRANT_TYPE);
		params.put("code", code);

		AccessToken accessToken = Service.parseResponse(HttpRequest.request(url, params));

		return new Client(this.host, accessToken);
	}

	private static AccessToken parseResponse(String httpResponse) throws InvalidResponseException, IOException, ApiException {
		JsonNode response = HttpRequest.parseResponse(httpResponse);
		
		
		return HttpRequest.mapper.convertValue(response, AccessToken.class);
	}
}
