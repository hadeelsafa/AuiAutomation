package api;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ReqresAPITestFlow extends BaseFlow {

    String firstName;
    String lastName;


    /**
     * this test will send post request to add new user and from the response we will extract the is
     */
    @Test
    public void addUser() {

        JSONObject bodyContent = new JSONObject();
        bodyContent.put("name", "NAME");
        bodyContent.put("job", "JOB");
        String id = given().log().all().header("Content-Type", "application/json")
                .body(bodyContent.toString())
                .when().post(resource)
                .then().log().all()
                .assertThat().statusCode(201)
                .extract().jsonPath().getString("id");
        System.out.println("The returned id is " + id);
    }

    /**
     * this test will send get request and from the response it will extract the first name and the last name
     */
    @Test
    public void getUser() {

        response = sendRequest("7", 200);
        JsonPath js = new JsonPath(response);
        firstName = js.getString(firstNamePath);
        lastName = js.get(lastNamePath);
        System.out.println("The user with ID#7 is" + " " + firstName + " " + lastName);
    }


    /**
     * this is the negative test which will send the get request with invalid id and will verify that the response is not found(404)
     */

    @Test
    public void invalidTest() {
        response = sendRequest("invalidId", 404);
        Assert.assertEquals(response, "{}", "the response not like the expected ");
    }


}