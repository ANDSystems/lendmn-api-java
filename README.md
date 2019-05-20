# LendMN Open Platform JAVA implementation


## Usage

`LendMNService` бол үндсэн тохиргоогоо хадгалах class, үүнийг хэрэглэгчээс хамааралгүй ашиглаж болно. Харин `Client` нь бол тус хэрэглэгчийн AccessToken-ыг агуулсан class instance. AccessToken тус бүрт нэг Client үүсгэж болно.

### Create and Get AccessToken

```java
package sample;

use lendmn.api.LendMNService;
use lendmn.api.Client; 


public class Sample {
	private static String host = "mgw.test.lending.mn";
	
	private static LendMNService service;

	public static void main(String[] args) {
		service = new LendMNService("128_kCaox7SYU2uUrngjvexN", "EFDLT4j1hK", host);
		
		Client client = service.consumeCode("XK6FFqg4rNab05d");
	}
}
```