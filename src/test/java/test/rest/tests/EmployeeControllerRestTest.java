package test.rest.tests;

import com.moi.entity.Employee;
import org.junit.Test;

import java.time.LocalDate;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class EmployeeControllerRestTest {

    @Test
    public void basicPingTest() {
        given().when().get("employees").then().statusCode(200);
    }

    @Test
    public void badRequest() {
        given().when().get("embloyees/b").then().statusCode(404);
    }

    @Test
    public void wrongUrl() {
        given().when().get("employes").then().statusCode(404);
    }

    @Test
    public void methodNotSupportedDelete() {
        given().when().delete("employees").then().statusCode(405);
    }

    @Test
    public void methodNotSupportedPost() { given().when().post("employees/11").then().statusCode(405); }

    @Test
    public void methodNotSupportedPut() {
        given().when().put("employees").then().statusCode(405);
    }

    @Test
    public void getEmployeeName() {
        given()
                .when().get("/employees/1").then()
                .body("name", equalTo("Felicja"))
                .statusCode(200);
    }

    @Test
    public void getEmployeeLastName() {
        given()
                .when().get("/employees/2").then()
                .body("lastName", equalTo("Szczepańska"))
                .statusCode(200);
    }

    @Test
    public void getEmployeePesel() {
        given()
                .when().get("/employees/3").then()
                .body("pesel", equalTo("46122738250"))
                .statusCode(200);
    }

    @Test
    public void getEmployeeAddress() {
        given()
                .when().get("/employees/4").then()
                .body("address", equalTo("ul. Krasickiego Ignacego 18 81-867 Sopot"))
                .statusCode(200);
    }

    @Test
    public void getEmployeePhone() {
        given()
                .when().get("/employees/5").then()
                .body("phone", equalTo("51 359 29 52"))
                .statusCode(200);
    }

    @Test
    public void getEmployeeTypeOfContract() {
        given()
                .when().get("/employees/6").then()
                .body("typeOfContract", equalTo("B2B"))
                .statusCode(200);
    }

    @Test
    public void getEmployeeEmployedSince() {
        given()
                .when().get("/employees/7").then()
                .body("employedSince", equalTo("2012-11-01"))
                .statusCode(200);
    }

    @Test
    public void addEmployee() {
        Employee employee = new Employee.Builder()
                .name("Borys")
                .lastName("Sobczak")
                .pesel("86091153410")
                .address("ul. Hutnicza 88 42-600 Tarnowskie Góry")
                .phone("78 134 26 02")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2014-04-01"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .statusCode(201);
    }

    @Test
    public void addEmployeeEmptyPhone() {
        Employee employee = new Employee.Builder()
                .name("Dominik")
                .lastName("Kaczmarek")
                .pesel("76100385275")
                .address("ul. Bohaterów Westerplatte 55 65-001 Zielona Góra")
                .typeOfContract("Umowa o pracę")
                .employedSince(LocalDate.parse("2017-01-01"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .statusCode(201);
    }

    @Test
    public void addEmployeeEmptyName() {
        Employee employee = new Employee.Builder()
                .name("Borys")
                .lastName("Sobczak")
                .pesel("86091153410")
                .address("ul. Hutnicza 88 42-600 Tarnowskie Góry")
                .phone("78 134 26 02")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2014-04-01"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .statusCode(201);
    }

    @Test
    public void addEmployeeEmptyLastName() {

    }

    @Test
    public void addEmployeeEmptyPesel() {

    }

    @Test
    public void addEmployeeEmptyAddress() {

    }

    @Test
    public void addEmployeeEmptyTypeOfContract() {

    }

    @Test
    public void addEmployeeEmptyEmployedSince() {

    }

    @Test
    public void addEmployeeAlreadyExist() {

    }

    @Test
    public void addEmployeePeselTooLong() {

    }

    @Test
    public void addEmployeePeselTooShort() {

    }

    @Test
    public void addEmployeePeselWrongFormat() {

    }

    @Test
    public void addEmployeePhoneTooShort() {

    }

    @Test
    public void addEmployeePhoneTooLong() {

    }

    @Test
    public void addEmployeePhoneWrongFormat() {

    }

    @Test
    public void addEmployeeEmployedSinceWrongFormat() {

    }

    @Test
    public void updateEmployee() {

    }

    @Test
    public void updateEmployeeName() {

    }

    @Test
    public void updateEmployeeLastName() {

    }

    @Test
    public void updateEmployeePesel() {

    }

    @Test
    public void updateEmployeeAddress() {

    }

    @Test
    public void updateEmployeePhone() {

    }

    @Test
    public void updateEmployeeTypeOfContract() {

    }

    @Test
    public void updateEmployeeEmployedSince() {

    }

    @Test
    public void updateEmployeeEmptyName() {

    }

    @Test
    public void updateEmployeeEmptyLastName() {

    }

    @Test
    public void updateEmployeeEmptyPesel() {

    }

    @Test
    public void updateEmployeePeselTooShort() {

    }

    @Test
    public void updateEmployeePeselTooLong() {

    }

    @Test
    public void updateEmployeePeselWrongFormat() {

    }

    @Test
    public void updateEmployeeEmptyAddress() {

    }

    @Test
    public void updateEmployeeEmptyPhone() {

    }

    @Test
    public void updateEmployeePhoneTooShort() {

    }

    @Test
    public void updateEmployeePhoneTooLong() {

    }

    @Test
    public void updateEmployeePhoneWrongFormat() {

    }

    @Test
    public void updateEmployeeEmptyTypeOfContract() {

    }

    @Test
    public void updateEmployeeEmptyEmployedSince() {

    }

    @Test
    public void updateEmployeeEmployedSinceWrongFormat() {

    }
}
