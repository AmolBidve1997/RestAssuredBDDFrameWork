package authManager;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utility.ConfigReader;

public class TokenGenerator {




	public static String renewToken() throws IOException {

		ConfigReader cr = new ConfigReader();

		HashMap<String, String> param = new HashMap<String, String>();

		param.put("grant_type", "refresh_token");

		param.put("refresh_token", cr.readConfigData("refreshtoken"));

		param.put("client_id", cr.readConfigData("Clientid"));

		param.put("client_secret", cr.readConfigData("Clientsecret"));

		RestAssured.baseURI = "https://accounts.spotify.com";

		Response response = given()

				.contentType(ContentType.URLENC)

				.formParams(param)

				//				.log().all()

				.when()

				.post("/api/token")

				.then()

				//				.log().all()

				.extract()

				.response();

		JsonPath jp = response.jsonPath();

		String accessToken = jp.getString("access_token");

		if(response.statusCode()!=200) {

			throw new RuntimeException("Failed To generate access Token");
		}

		return accessToken;


	}




}
