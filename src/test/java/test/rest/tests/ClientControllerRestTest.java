package test.rest.tests;

import com.moi.entity.Client;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
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
                .body("address", equalTo("Bytow, ul. Slaska 6"))
                .body("contact", equalTo("Jan Nowak"))
                .statusCode(200);
    }
    @Test
    public void addClient() {
        Client client = new Client();
        client.setName("Klient testowy");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact("Jan kowalski");
        client.setPhone(111111111);

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(201);
    }
    @Test
    public void addClientConflict() {
        Client client = new Client();
        client.setName("Klient testowy");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact("Jan kowalski");
        client.setPhone(111111111);

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(409);
    }
    @Test
    public void updateClient() {
        Client client = new Client();
        client.setClientId((long) 2);
        client.setName("Test edycji");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact("Jan kowalski");
        client.setPhone(111111111);

        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients").then()
                .statusCode(200);

        given().when().get("/clients/2").then()
                .body("name",equalTo("Test edycji"))
                .statusCode(200);
    }

}
