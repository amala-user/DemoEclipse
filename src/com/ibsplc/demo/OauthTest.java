package com.ibsplc.demo;
import  static io.restassured.RestAssured.*;
public class OauthTest {

	public static void main(String[] args) {
		
		//code to get course list
	String response=given().queryParam("access_token"," ").when().get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
	System.out.println(response);
	}

}
