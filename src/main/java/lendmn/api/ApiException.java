package lendmn.api;

public class ApiException extends Exception {
  private static final long serialVersionUID = 5862782020104931773L;
  
  protected ApiError error;
  protected String key;

  public ApiException(ApiError error) {
    super();
    this.error = error;
  }

  public ApiException(ApiError error, String message) {
    super(message);
    this.error = error;
  }

  public ApiException(ApiError error, String message, String key) {
    super(message);
    this.error = error;
    this.key = key;
  }
  
  
  public ApiError getApiErrorCode() {
    return this.error;
  }
  
  public String getErrorKey() {
    return this.key;
  }

}
