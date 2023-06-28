import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
public class Rest_get_prac {

	public static void main(String[] args) {
		RestAssured.baseURI="https://reqres.in/";
		
		given().header("Content-Type","application/json").log().all().when().get("api/users?page=2").then().log().all().extract().response().asString();
	}

}
