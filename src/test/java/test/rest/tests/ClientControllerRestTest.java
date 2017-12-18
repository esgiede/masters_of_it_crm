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
                .when().put("clients").then()
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

        Client client =  new Client.Builder()
                .name("Test dodawania klienta")
                .address("Lublin, ul. Morwowa")
                .contact("Jan kowalski")
                .phone("111111111")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(201);
    }
    @Test
    public void addClientConflict() {

        Client client =  new Client.Builder()
                .name("Test dodawania klienta")
                .address("Lublin, ul. Morwowa")
                .contact("Jan kowalski")
                .phone("111111111")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(500);
    }
    @Test
    public void updateClientName() {

        Client client =  new Client.Builder()
                .name("Wyedytowana nazwa")
                .address("Lublin, ul. Morwowa")
                .contact("Jan kowalski")
                .phone("111111111")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/2").then()
                .statusCode(200);

        given().when().get("/clients/2").then()
                .body("name",equalTo("Wyedytowana nazwa"))
                .statusCode(200);
    }
    @Test
    public void updateClientAddress() {

        Client client =  new Client.Builder()
                .name("Test edycji adresu")
                .address("Krakow, ul. Poznanska")
                .contact("Jan kowalski")
                .phone("111111111")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/3").then()
                .statusCode(200);

        given().when().get("/clients/3").then()
                .body("address",equalTo("Krakow, ul. Poznanska"))
                .statusCode(200);
    }
    @Test
    public void updateClientContact() {

        Client client =  new Client.Builder()
                .name("Test edycji kontaktu")
                .address("Lublin, ul. Morwowa")
                .contact("Damian Nowak")
                .phone("111111111")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/4").then()
                .statusCode(200);

        given().when().get("/clients/4").then()
                .body("contact",equalTo("Damian Nowak"))
                .statusCode(200);
    }
    @Test
    public void updateClientPhone() {

        Client client =  new Client.Builder()
                .name("Test edycji telefonu")
                .address("Lublin, ul. Morwowa")
                .contact("Jan kowalski")
                .phone("222222222")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/5").then()
                .statusCode(200);

        given().when().get("/clients/5").then()
                .body("phone",equalTo("222222222"))
                .statusCode(200);
    }
    @Test
    public void updateClient() {

        Client client =  new Client.Builder()
                .name("Wyedytowana nazwa2")
                .address("Wyedytowany adres")
                .contact("Wyedytowany kontakt")
                .phone("333333333")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/6").then()
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

        Client client =  new Client.Builder()
                .id((long)6)
                .name(null)
                .address("Lublin, ul. Morwowa")
                .contact("Jan kowalski")
                .phone("111111111")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(500);
    }
    @Test
    public void addClientEmptyAddress() {

        Client client =  new Client.Builder()
                .id((long)6)
                .name("Test dodawania klienta")
                .contact("Jan kowalski")
                .phone("111111111")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(500);
    }
    @Test
    public void addClientEmptyContact() {

        Client client =  new Client.Builder()
                .id((long)6)
                .name("Test dodawania klienta")
                .address("Lublin, ul. Morwowa")
                .phone("111111111")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(500);
    }
    @Test
    public void addClientEmptyPhone() {

        Client client =  new Client.Builder()
                .id((long)6)
                .name("Test dodawania klienta")
                .address("Lublin, ul. Morwowa")
                .contact("Jan kowalski")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(500);
    }
    @Test
    public void addClientEmptyAllParameters() {
        Client client =  new Client.Builder().build();

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(500);
    }
    @Test
    public void addClientPhoneTooShort() {

        Client client =  new Client.Builder()
                .id((long)6)
                .name("Test dodawania klienta")
                .address("Lublin, ul. Morwowa")
                .contact("Jan kowalski")
                .phone("111")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(500);
    }
    @Test
    public void addClientPhoneTooLong() {

        Client client =  new Client.Builder()
                .id((long)6)
                .name("Test dodawania klienta")
                .address("Lublin, ul. Morwowa")
                .contact("Jan kowalski")
                .phone("11111111111111")
                .build();

        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(500);
    }
}
