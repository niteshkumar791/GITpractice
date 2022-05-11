package RESTAPI_bdd;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class API_get {

	
	public void getdetails() {

		given()
		.when()
			.get("http://restapi.demoqa.com/utilities/weather/city/hyderabad")
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.assertThat().body("City", equalTo("Hyderabad"))
			.header("Content-Type", "application/json");
	}
	
	@Test
	public void  get_singleUser() {
		
		given()
		.when()
			.get("https://reqres.in/api/users/2")
		.then()
			.statusCode(200);
			
	}

}
