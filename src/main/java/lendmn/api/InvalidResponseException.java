package lendmn.api;

public class InvalidResponseException extends Exception {
	private static final long serialVersionUID = -5189067418644097746L;

	public InvalidResponseException(String message) {
		super(message);
	}

}
