package test.rest.tests;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class ClientControllerRestTest{

    @Test
    public void basicPingTest(){
        given().when().get("clients").then().statusCode(200);
    }
    @Test
    public void badRequest(){
        given().when().get("clients/b").then().statusCode(400);
    }
    @Test
    public void wrongUrl(){
        given().when().get("clientss").then().statusCode(404);
    }
    @Test
    public void verifyClientName() {
        given().when().get("/clients/1").then()
                .body("name",equalTo("Drutex sp. z.o.o"))
                .body("address", equalTo("Bytow, ul. slaska 6"))
                .body("contact", equalTo("Jan Nowak"))
        .statusCode(200);
    }
    @Test
    public void addClient() {
        Map<String,String> client = new HashMap<>();
        client.put("name", "Klient testowy");
        client.put("address", "Lublin, ul. Morwowa");
        client.put("contact", "Jan Kowalski");
        client.put("phone", "111111111");

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(201);
    }
    @Test
    public void addClientConflict() {
        Map<String,String> client = new HashMap<>();
        client.put("name", "Klient testowy");
        client.put("address", "Lublin, ul. Morwowa");
        client.put("contact", "Jan Kowalski");
        client.put("phone", "111111111");

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(409);
    }

}
