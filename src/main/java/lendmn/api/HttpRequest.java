package lendmn.api;

import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

class HttpRequest {
	public static ObjectMapper mapper = new ObjectMapper();

	public static String request(URL url, Map<String, String> params) throws IOException {
		return HttpRequest.request(url, params, null);
	}

	public static String request(URL url, Map<String, String> parameters, String accessToken) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		if (accessToken != null) {
			con.setRequestProperty("x-and-auth-token", accessToken);
		}

		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);

		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(HttpRequest.getParamsString(parameters));
		out.flush();
		out.close();

		Reader streamReader = null;
		if (con.getResponseCode() > 299) {
			streamReader = new InputStreamReader(con.getErrorStream());
		} else {
			streamReader = new InputStreamReader(con.getInputStream());
		}

		BufferedReader in = new BufferedReader(streamReader);
		String inputLine;
		StringBuilder content = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}

		in.close();

		return content.toString();
	}

	public static JsonNode parseResponse(String response) throws InvalidResponseException, IOException, ApiException {
		try {
			JsonNode root = mapper.readTree(response);
			int code = root.get("code").asInt();
			if (code != 0) {
				JsonNode message = root.path("response")
						.path("error_message");
				ApiError apiError = ApiError.get(code);
				if(message.isMissingNode()) {
					throw new ApiException(apiError);
				} 
				throw new ApiException(apiError, message.asText());
			}
			
			return root.get("response");
		} catch (JsonParseException e) {
			throw new InvalidResponseException("expected json");
		}
	}

	public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}

		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}
}
