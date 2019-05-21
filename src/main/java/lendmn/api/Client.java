package lendmn.api;

import lendmn.api.request.Request;

public class Client {
	protected AccessToken accessToken;
	protected String host;

	public Client(String host, AccessToken accessToken) {
		this.host = host;
		this.accessToken = accessToken;
	}

	public Boolean request(Request request) {
		return true;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public AccessToken getAccessToken() {
		return this.accessToken;
	}

}
