package Basics;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import Files.payload;

public class basics {
	public static void main(String[] args)
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		 
		//Given When Then - following this pattern is must in RestAssured
		//Given() - Request input details 
		//When() - Post Method or Get Method
		//Post Method or Get Method - carries an argument which is resource 
		//then() - Validations - Assertthat 
		
		
		//This is Addplace API - used to add a new place in google maps
		String response = given().queryParam("key", "qaclick123").headers("Content-Type","application/json").body(payload.files()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).headers("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
		System.out.println(response);
		
		//To extract the place_id from the response - we can use jsonpath class
		JsonPath js = new JsonPath(response);
		String placeid=js.getString("place_id");
		System.out.println(placeid);
		
		
		
		//This is Updateplace API - used to update the address of the existing place
		
		given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\"70 orange Street Australia\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200);
		
		//This is GetPlaceAPI - we are validating the latest address has been updated or not.
		
		
	
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeid).when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200);
	}
	
	
	
	
}
