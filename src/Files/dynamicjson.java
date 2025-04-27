package Files;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class dynamicjson
{
	@Test 
	public void addBook()
	{

		String response = given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").formParams("grant_type","client_credentials").formParam("scope","trust").when().log().all()
				 .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String access_token=js.getString("access_token");
		String r2=    given()

				.queryParams("access_token", access_token)

				.when()

				           .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")

				.asString();

				System.out.println(r2);
		
	}
	
	
	
	
	
}
