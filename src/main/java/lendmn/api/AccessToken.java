package lendmn.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {
	private String token;
	private Long expiresIn;
	private String[] scopes;
	public String scope;

	@JsonCreator
	public AccessToken(@JsonProperty("accessToken") String token, @JsonProperty("expiresIn") long expiresIn,
			@JsonProperty("scope") String scopes) {
		this.token = token;
		this.expiresIn = expiresIn;
		this.scopes = scopes.split(",");
		this.scope = scopes;
	}

	@JsonIgnore
	public String getToken() {
		return token;
	}

	@JsonIgnore
	public AccessToken setToken(String token) {
		this.token = token;
		return this;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public AccessToken setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

	@JsonIgnore
	public String[] getScopes() {
		return scopes;
	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
        	
            return true;
        } else if (obj == null) {
        	
            return false;
        } else if (obj instanceof AccessToken) {
        	AccessToken token = (AccessToken) obj;

            if ((token.getToken() == null && this.token == null) || (token.getToken().equals(this.token) && 
        		((token.getExpiresIn() == null && this.expiresIn == null) || token.getExpiresIn().equals(this.expiresIn)) && 
        		((token.scope == null && this.scope == null) || token.scope.equals(this.scope)))) {

                return true;

            }

        }

        return false;
	}

}
