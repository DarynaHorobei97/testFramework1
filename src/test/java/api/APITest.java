package api;


import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;

public class APITest extends BaseApiTest {

    String API_key = "f64de71caae815e25c74070ab1aadbd1";

    private Map<String, Object> reqBody = new HashMap<>();


   @BeforeMethod
    public void setReqBody() {
        Map<String, Object> methodProperties = new HashMap<>();

        methodProperties.put("CityName", "м. Київ");
        methodProperties.put("Limit", 5);

        reqBody.put("apiKey", API_key);
        reqBody.put("modelName", "Address");
        reqBody.put("calledMethod", "searchSettlements");
        reqBody.put("methodProperties", methodProperties);


    }

    @Test
    public void checkThatSuccess() {
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    public void checkJsonSchema() {
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(new File(System.getProperty("user.dir") +
                        "\\src\\main\\resources\\validators\\np_address_schema.json")));
    }

    @Test
    public void checkRegions() {
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("data[0].Addresses[0].Present", equalTo("м. Київ, Київська обл."))
                .body("data[0].TotalCount", equalTo(106));
    }

    @Test
    public void checkKyivIsPresent() {

        List<api.Address> addressList = given()
                .spec(requestSpecification)
                .when()
                .contentType(ContentType.JSON)
                .body(reqBody)
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .extract()
                .body().jsonPath().getList("data[0].Address", api.Address.class);
        System.out.println(addressList);
    }

    @Test
    public void testWithPojoUsage() {

        List<Address> addressesData = given()
                .spec(this.requestSpecification)
                .when()
                .contentType(ContentType.JSON)
                .when()
                .body(this.reqBody)
                .post()
                .then()
                .spec(responseSpecification)
                .extract()
                .body().jsonPath().getList("data[0].Addresses", Address.class);

        System.out.println(addressesData);
        addressesData.forEach(el -> assertTrue(el.getPresent().contains("Київ")));
    }
}
