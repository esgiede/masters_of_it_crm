package test.rest.tests;

import com.moi.entity.Client;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class ClientControllerRestTest{

    @Test
    public void basicPingTest(){
        given().when().get("clients").then().statusCode(200);
    }
    @Test
    public void badRequest(){

        given()
                .when().get("clients/b").then()
                .body("message", equalTo("Podaj prawidłowy typ danych"))
                .statusCode(404);
    }
    @Test
    public void wrongUrl(){
        given().when().get("clientss").then().statusCode(404);
    }
    @Test
    public void methodNotSupportedDelete(){

        given()
                .when().delete("clients").then()
                .body("message",equalTo("Method not supported"))
                .statusCode(405);
    }
    @Test
    public void methodNotSupportedPost(){

        given()
                .when().post("clients/11").then()
                .body("message",equalTo("Method not supported"))
                .statusCode(405);
    }
    @Test
    public void methodNotSupportedPut(){
        given()
                .when().put("clients/12").then()
                .body("message",equalTo("Method not supported"))
                .statusCode(405); }
    @Test
    public void verifyClientName() {
        given().when().get("/clients/1").then()
                .body("name",equalTo("Drutex sp. z o.o."))
                .statusCode(200);
    }
   @Test
    public void addClient() {
        Client client = new Client();
        client.setName("Test dodawania klienta");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact("Jan kowalski");
        client.setPhone("111111111");

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(201);
    }
    @Test
    public void addClientConflict() {
        Client client = new Client();
        client.setName("Test dodawania klienta");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact("Jan kowalski");
        client.setPhone("111111111");

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(500);
    }
    @Test
    public void updateClientName() {
        Client client = new Client();
        client.setId((long) 2);
        client.setName("Wyedytowana nazwa");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact("Jan kowalski");
        client.setPhone("111111111");

        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients").then()
                .statusCode(200);

        given().when().get("/clients/2").then()
                .body("name",equalTo("Wyedytowana nazwa"))
                .statusCode(200);
    }
    @Test
    public void updateClientAddress() {
        Client client = new Client();
        client.setId((long) 3);
        client.setName("Test edycji adresu");
        client.setAddress("Krakow, ul. Poznanska");
        client.setContact("Jan kowalski");
        client.setPhone("111111111");

        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients").then()
                .statusCode(200);

        given().when().get("/clients/3").then()
                .body("address",equalTo("Krakow, ul. Poznanska"))
                .statusCode(200);
    }
    @Test
    public void updateClientContact() {
        Client client = new Client();
        client.setId((long) 4);
        client.setName("Test edycji kontaktu");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact("Damian Nowak");
        client.setPhone("111111111");

        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients").then()
                .statusCode(200);

        given().when().get("/clients/4").then()
                .body("contact",equalTo("Damian Nowak"))
                .statusCode(200);
    }
    @Test
    public void updateClientPhone() {
        Client client = new Client();
        client.setId((long) 5);
        client.setName("Test edycji telefonu");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact("Jan kowalski");
        client.setPhone("222222222");

        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients").then()
                .statusCode(200);

        given().when().get("/clients/5").then()
                .body("phone",equalTo("222222222"))
                .statusCode(200);
    }
    @Test
    public void updateClient() {
        Client client = new Client();
        client.setId((long) 6);
        client.setName("Wyedytowana nazwa2");
        client.setAddress("Wyedytowany adres");
        client.setContact("Wyedytowany kontakt");
        client.setPhone("333333333");

        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients").then()
                .statusCode(200);

        given().when().get("/clients/6").then()
                .body("name",equalTo("Wyedytowana nazwa2"))
                .body("address",equalTo("Wyedytowany adres"))
                .body("contact",equalTo("Wyedytowany kontakt"))
                .body("phone",equalTo("333333333"))
                .statusCode(200);
    }
    @Test
    public void deleteClient() {
        given().when().delete("clients/7").then().statusCode(204);
    }
    @Test
    public void addClientEmptyName() {
        Client client = new Client();
        client.setName(null);
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact("Jan kowalski");
        client.setPhone("111111111");

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
    @Test
    public void addClientEmptyAddress() {
        Client client = new Client();
        client.setName("Test dodawania klienta");
        client.setAddress(null);
        client.setContact("Jan kowalski");
        client.setPhone("111111111");

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
    @Test
    public void addClientEmptyContact() {
        Client client = new Client();
        client.setName("Test dodawania klienta");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact(null);
        client.setPhone("111111111");

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
    @Test
    public void addClientEmptyPhone() {
        Client client = new Client();
        client.setName("Test dodawania klienta");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact("Jan kowalski");

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
    @Test
    public void addClientEmptyAllParameters() {
        Client client = new Client();

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
    @Test
    public void addClientPhoneTooShort() {
        Client client = new Client();
        client.setName("Test dodawania klienta");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact(null);
        client.setPhone("111");

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
    @Test
    public void addClientPhoneTooLong() {
        Client client = new Client();
        client.setName("Test dodawania klienta");
        client.setAddress("Lublin, ul. Morwowa");
        client.setContact(null);
        client.setPhone("1111111111111");

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
}
