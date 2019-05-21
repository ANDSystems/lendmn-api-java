package lendmn.api;

public enum ApiError {

	NotEnoughParameters(1), InvalidParameter(2), ServiceUnavailable(3), InvalidResponse(4), ConfigurationError(5),
	MerchantWalletDoesNotExist(6),

	TransactionNotFound(57), InsufficientFunds(58), DailyLimitReached(59), TransactionAlreadyCompleted(60),
	LowerThanMinimumAmount(61), LargerThanMaximumAmount(62),

	InvoiceAmountCannotBeChanged(127), InvoicePaid(128), InvoiceCancelled(130), InvoiceExpired(131),
	InvoiceAmountMismatch(132),

	UserNotFound(201), RequestParameterError(400), RequestAuthorizationError(401),
	
	UnknownError(999999);

	private final int code;
	
	private static ApiError[] cachedValues = null;

	ApiError(int code) {
		this.code = code;
	}

	public int getErrorCode() {
		return this.code;
	}
	
	public static ApiError get(int code) {
		if(ApiError.cachedValues == null) {
			ApiError.cachedValues = ApiError.values();
		}
		
	    for(int i = 0; i < ApiError.cachedValues.length; i++)
	    {
	        if(ApiError.cachedValues[i].getErrorCode() == code)
	            return ApiError.cachedValues[i];
	    }
	    return ApiError.UnknownError;
	}

}
