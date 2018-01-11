package test.rest.tests;

import com.moi.entity.Client;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class ClientControllerRestTest {

    @Test
    public void basicPingTest() {
        given().when().get("clients").then().statusCode(200);
    }

    @Test
    public void badRequest() {

        given()
                .when().get("clients/b").then()
                .body("message", equalTo("Podaj prawidłowy typ danych"))
                .statusCode(404);
    }

    @Test
    public void wrongUrl() {
        given().when().get("clientss").then().statusCode(404);
    }

    @Test
    public void methodNotSupportedDelete() {

        given()
                .when().delete("clients").then()
                .body("message", equalTo("Method not supported"))
                .statusCode(405);
    }

    @Test
    public void methodNotSupportedPost() {

        given()
                .when().post("clients/11").then()
                .body("message", equalTo("Method not supported"))
                .statusCode(405);
    }

    @Test
    public void methodNotSupportedPut() {
        given()
                .when().put("clients").then()
                .body("message", equalTo("Method not supported"))
                .statusCode(405);
    }

    @Test
    public void getClientName() {
        given()
                .when().get("/clients/1").then()
                .body("name", equalTo("Drutex sp. z o.o."))
                .statusCode(200);
    }

    @Test
    public void addClient() {
        Client client = new Client.Builder()
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
        Client client = new Client.Builder()
                .name("Test dodawania klienta")
                .address("Lublin, ul. Morwowa")
                .contact("Jan kowalski")
                .phone("111111111")
                .build();
        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(409);
    }

    @Test
    public void updateClientName() {
        Client client = new Client.Builder()
                .name("Test edycji nazwy - wyedytowana")
                .address("Lublin, ul. Morwowa")
                .contact("Jan Kowalski")
                .phone("111111111")
                .build();
        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/2").then()
                .statusCode(200);
        given()
                .when().get("/clients/2").then()
                .body("name", equalTo("Test edycji nazwy - wyedytowana"))
                .statusCode(200);
    }

    @Test
    public void updateClientAddress() {
        Client client = new Client.Builder()
                .name("Test edycji adresu")
                .address("Krakow, ul. Poznanska")
                .contact("Jan Nowak")
                .phone("111111111")
                .build();
        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/3").then()
                .statusCode(200);
        given().when().get("/clients/3").then()
                .body("address", equalTo("Krakow, ul. Poznanska"))
                .statusCode(200);
    }

    @Test
    public void updateClientContact() {
        Client client = new Client.Builder()
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
                .body("contact", equalTo("Damian Nowak"))
                .statusCode(200);
    }

    @Test
    public void updateClientPhone() {
        Client client = new Client.Builder()
                .name("Test edycji telefonu")
                .address("Lublin, ul. Morwowa")
                .contact("Jan Kowalski")
                .phone("222222222")
                .build();
        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/5").then()
                .statusCode(200);
        given().when().get("/clients/5").then()
                .body("phone", equalTo("222222222"))
                .statusCode(200);
    }

    @Test
    public void updateClient() {
        Client client = new Client.Builder()
                .name("Wyedytowana nazwa")
                .address("Wyedytowany adres")
                .contact("Wyedytowany kontakt")
                .phone("333333333")
                .build();
        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/6").then()
                .statusCode(200);
        given()
                .when().get("/clients/6").then()
                .body("name", equalTo("Wyedytowana nazwa"))
                .body("address", equalTo("Wyedytowany adres"))
                .body("contact", equalTo("Wyedytowany kontakt"))
                .body("phone", equalTo("333333333"))
                .statusCode(200);
    }

    @Test
    public void getPageSize() {
        given()
                .when().get("/clients?size=5").then()
                .body("size", equalTo(5))
                .statusCode(200);
    }

    @Test
    public void getPageNumber() {
        given()
                .when().get("/clients?page=1&size=5").then()
                .body("number", equalTo(1))
                .statusCode(200);
    }

    @Test
    public void getPageElements() {
        given()
                .when().get("/clients?page=0&size=5").then()
                .body("numberOfElements", equalTo(5))
                .statusCode(200);
    }

    @Test
    public void deleteClient() {
        given().when().delete("clients/7").then().statusCode(204);
    }

    @Test
    public void addClientEmptyName() {
        Client client = new Client.Builder()
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
        Client client = new Client.Builder()
                .name("Klient bez adresu")
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
        Client client = new Client.Builder()
                .name("Klient bez kontaktu")
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
        Client client = new Client.Builder()
                .name("Klient bez telefonu")
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
        Client client = new Client.Builder().build();
        given()
                .contentType("application/json")
                .body(client)
                .when().post("/clients").then()
                .statusCode(500);
    }

    @Test
    public void addClientPhoneTooShort() {
        Client client = new Client.Builder()
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
        Client client = new Client.Builder()
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

    @Test
    public void updateClientEmptyName() {
        Client client = new Client.Builder()
                .name(null)
                .address("Wyedytowany adres")
                .contact("Wyedytowany kontakt")
                .phone("333333333")
                .build();
        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/1").then()
                .statusCode(500);
    }

    @Test
    public void updateClientEmptyAddress() {
        Client client = new Client.Builder()
                .name("Klient bez adresu")
                .address(null)
                .contact("Wyedytowany kontakt")
                .phone("333333333")
                .build();
        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/1").then()
                .statusCode(500);
    }

    @Test
    public void updateClientEmptyContact() {
        Client client = new Client.Builder()
                .name("Klient bez adresu")
                .address("Adres")
                .contact(null)
                .phone("333333333")
                .build();
        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/1").then()
                .statusCode(500);
    }

    @Test
    public void updateClientEmptyPhone() {
        Client client = new Client.Builder()
                .name("Klient bez adresu")
                .address("Adres")
                .contact("Kontakt")
                .phone(null)
                .build();
        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/1").then()
                .statusCode(500);
    }

    @Test
    public void updateClientPhoneTooShort() {
        Client client = new Client.Builder()
                .name("Klient bez adresu")
                .address("Adres")
                .contact("Kontakt")
                .phone("33")
                .build();
        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/1").then()
                .statusCode(500);
    }

    @Test
    public void updateClientPhoneTooLong() {
        Client client = new Client.Builder()
                .name("Klient bez adresu")
                .address("Adres")
                .contact("Klient")
                .phone("333333333333333333")
                .build();
        given()
                .contentType("application/json")
                .body(client)
                .when().put("/clients/1").then()
                .statusCode(500);
    }

    @Test
    public void deleteClientHasProject() {
        given().when().delete("clients/8").then().statusCode(409);
    }

    @Test
    public void deleteClientWrongId() {
        given().when().delete("clients/20").then().statusCode(404);
    }
}
