package lendmn.api;

public class ApiException extends Exception {
	private static final long serialVersionUID = 5862782020104931773L;
	
	protected ApiError error;

	public ApiException(ApiError error) {
		super();
		this.error = error;
	}

	public ApiException(ApiError error, String message) {
		super(message);
		this.error = error;
	}

}
