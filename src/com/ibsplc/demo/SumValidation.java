package com.ibsplc.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ibsplc.files.payLoad;

import io.restassured.path.json.JsonPath;

public class SumValidation {
	// To verify total purchase amount
	@Test
	public void SumOfCourse() {
		int sum=0;
		JsonPath js=new JsonPath(payLoad.CourcePrice());
		int count=js.getInt("courses.size()");
		for (int i=0;i<count;i++) {
		int price=	js.getInt("courses["+i+"].price");
		int copy=	js.getInt("courses["+i+"].copies");
		int amount=price*copy;
		System.out.println(amount);
		sum=sum+amount;
	}
		System.out.println(sum);
		int purchaseamount=js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(purchaseamount,sum);

}
}
