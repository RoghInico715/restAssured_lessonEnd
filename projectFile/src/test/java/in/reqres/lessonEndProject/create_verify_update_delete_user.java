package in.reqres.lessonEndProject;

import java.util.HashMap;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class create_verify_update_delete_user extends TestBase {

	HashMap<String, String> map = new HashMap<String, String>();
	String id;
	Response response;
	JsonPath jsonPath;
	
	@Test(priority = 0)
	public void createUser() {
		map.put("name", "john");
		map.put("job", "doctor");
		RestAssured.baseURI = "https://reqres.in/";
		RestAssured.basePath = "/api/users";
		response = RestAssured
		.given()
			.contentType("application/json")
			.body(map)
		.when()
			.post()
		.then()
			.statusCode(201)
			.log().all()
			.extract().response();
		logger.info("User Created Successfully");
		jsonPath = response.jsonPath();
		id = jsonPath.get("id");
	}
	
	@Test(priority = 1)
	public void updateUser() {
		map.put("name", "john");
		map.put("job", "programmer");
		RestAssured.baseURI = "https://reqres.in/";
		RestAssured.basePath = "/api/users/" + id;
		RestAssured
		.given()
			.contentType("application/json")
			.body(map)
		.when()
			.put()
		.then()
			.statusCode(200)
			.log().all();
		logger.info("User Updated Successfully");
	}
	
	@Test(priority = 2)
	public void deleteUser() {
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "/api/users/" + id;
		
		RestAssured
			.when()
				.delete()
			.then()
				.statusCode(204);
		logger.info("User Deleted Successfully");
	}
}
