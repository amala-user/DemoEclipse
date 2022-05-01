package com.ibsplc.demo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import  static io.restassured.RestAssured.*;
import  static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;
import com.ibsplc.files.payLoad;
public class Basics {

	public static void main(String[] args) throws IOException {
		//post method
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//Hamcrest in restassured body itself
	String response=	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/com/ibsplc/files/addPlace.json")))).when().post("/maps/api/place/add/json").then().assertThat()
		.statusCode(200).body("scope",equalTo("APP")).extract().response().asString();
	
	//printing the response extracted
	System.out.println(response);
	
	//extracting a parameter in json output response
	JsonPath js=new JsonPath(response);//parsing json
	String placeId=js.getString("place_id");
	System.out.println(placeId);
	
	//update the address of placeid extracted in above response-put method
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String expectedAddress="70 Summer walk, USA";
	given().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body("{\r\n"
			+ "\"place_id\":\""+placeId+"\",\r\n"
			+ "\"address\":\""+expectedAddress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}\r\n"
			+ "").when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)	
	.body("msg",equalTo("Address successfully updated"));
	
	//get method to verify whether address is updated
	RestAssured.baseURI="https://rahulshettyacademy.com";
    String getResponse=given().queryParam("key","qaclick123").queryParam("place_id",placeId)
	.when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
	.extract().response().asString();
    JsonPath js1=new JsonPath( getResponse);
  String actualAddress= js1.getString("address");
  System.out.println(actualAddress);
  Assert.assertEquals(expectedAddress,actualAddress);
	
	
	}

}
