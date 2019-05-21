package lendmn.api;

import java.io.IOException;
import java.net.MalformedURLException;

import lendmn.api.Client;
import lendmn.api.LendMNService;
import lendmn.api.InvalidResponseException;
import lendmn.api.ApiException;

public class Sample {
  private static String host = "mgw.test.lending.mn";

  private static LendMNService service;

  public static void main(String[] args) {
    service = new LendMNService("128_kCaox7SYU2uUrngjvexN", "EFDLT4j1hK", host);

    try {
      Client client = service.consumeCode("http://localhost/", "XK6FFqg4rNab05d");
    } catch (ApiException e) {

      System.out.print("code: ");
      System.out.print(e.getApiErrorCode());
      System.out.println("[" + e.getApiErrorCode().getErrorCode() + "]");
      if(e.getErrorKey() != null) {
        System.out.println("error: ".concat(e.getErrorKey()));
      }
      if(e.getMessage() != null) {
        System.out.println("message: ".concat(e.getMessage()));
      }
      System.out.println();
    } catch (InvalidResponseException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}