import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
public class Rest_Put_Prac {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://reqres.in/";

		String RequestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";

		JsonPath JspRequest=new JsonPath(RequestBody);
		String Req_name=JspRequest.getString("name");
		String Req_job=JspRequest.getString("job");
		LocalDateTime currentdate=LocalDateTime.now();
	String expecteddate=currentdate.toString().substring(0,9);
		
		String ResponseBody = given().header("Content-Type","application/json").body(RequestBody).
				when().post("api/users/2").then().extract().response().asString();
		
		System.out.println(ResponseBody);
		
		JsonPath JspResponse=new JsonPath(ResponseBody);
		String Res_name=JspResponse.getString("name");
		String Res_job=JspResponse.getString("job");
		String Res_updatedAt=JspResponse.getString("updatedAt");
		Res_updatedAt=Res_updatedAt.substring(0,9);
		
		Assert.assertEquals(Res_name, Req_name);
		Assert.assertEquals(Res_job, Req_job);
		Assert.assertEquals(Res_updatedAt, expecteddate);
		System.out.println(ResponseBody);
		
	}

}
