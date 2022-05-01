package com.ibsplc.files;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import  static io.restassured.RestAssured.*;

public class DynamicJson {
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
	String response=given().log().all().header("Content-Type","application/json")
		.body(payLoad.addRequest(isbn,aisle))
		.when().post("Library/Addbook.php").then().assertThat()
		.statusCode(200).extract().response().asString();
	     JsonPath js=new JsonPath(response);
	 String id= js.get("ID");
	 System.out.println(id);
	 
	 //delete
	 
	 String responsedelete=given().log().all().header("Content-Type","application/json")
				.body("{\r\n"
						+ " \r\n"
						+ "\"ID\" : \""+id+"\"\r\n"
						+ " \r\n"
						+ "} \r\n"
						+ "")
				.when().delete("Library/DeleteBook.php").then().assertThat()
				.statusCode(200).extract().response().asString();
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData(){
		return new Object[][] {{"ghjk","678"},{"etyr","890"},{"tyui","456"}};
	}

}
