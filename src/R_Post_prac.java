import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;;

public class R_Post_prac {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://reqres.in/";

		String RequestBody = "{\r\n" + "    \"name\": \"puja\",\r\n" + "    \"job\": \"leader\"\r\n" + "}";
		JsonPath JspRequest = new JsonPath(RequestBody);
		String Req_name = JspRequest.getString("name");
		String Req_job = JspRequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String Expecteddate = currentdate.toString().substring(0, 9);

		String ResponseBody = given().header("Content-Type", "application/json").body(RequestBody).when()
				.post("api/users").then().extract().response().asString();
		System.out.println(ResponseBody);

		JsonPath JspResponse = new JsonPath(ResponseBody);
		String Res_name = JspResponse.getString("name");
		String Res_job = JspResponse.getString("job");
		String Res_id = JspResponse.getString("id");
		String Res_createdAt = JspResponse.getString("createdAt");
		Res_createdAt = Res_createdAt.substring(0, 9);

		Assert.assertEquals(Res_name, Req_name);
		Assert.assertEquals(Res_job, Req_job);
		Assert.assertNotEquals(Res_id, 0);
		Assert.assertEquals(Res_createdAt, Expecteddate);
		System.out.println(ResponseBody);

	}

}