package com.ibsplc.demo;

import com.ibsplc.files.payLoad;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		JsonPath js=new JsonPath(payLoad.CourcePrice());
		js.getInt("courses.size()");
		int count=js.getInt("courses.size()");
		System.out.println(count);
		System.out.println(js.getInt("dashboard.purchaseAmount"));
		System.out.println(js.getString("courses[0].title"));
		for (int i=0;i<count;i++) {
		String title=js.getString("courses["+i+"].title");
		int price=	js.getInt("courses["+i+"].price");
		System.out.println(title+" "+price);
		//print no of copies sold by RPA element
		for(int j=0;j<count;j++) {
		String courseTitle=	js.getString("courses["+j+"].title");
		if(courseTitle.equalsIgnoreCase("RPA")) {
		int copy=js.getInt("courses["+j+"].copies");
		System.out.println(copy);
		break;
		
		}
		}
			
		}

	}

}
