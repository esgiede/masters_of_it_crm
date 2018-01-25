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
                .lastName("Ostrowski")
                .pesel("98011053913")
                .address("ul. Żużlowców 94 51-050 Wrocław")
                .phone("53 954 44 83")
                .typeOfContract("Umowa zlecenie")
                .employedSince(LocalDate.parse("2018-01-01"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeEmptyLastName() {
        Employee employee = new Employee.Builder()
                .name("Jan")
                .pesel("94081131102")
                .address("ul. Szamarzewskiego Augustyna 3 60-569 Poznań")
                .phone("53 432 94 15")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-05-01"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeEmptyPesel() {
        Employee employee = new Employee.Builder()
                .name("Małgorzata")
                .lastName("Tomaszewska")
                .address("Pl. Matki Polki 18 71-461 Szczecin")
                .phone("51 688 21 73")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2016-11-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeEmptyAddress() {
        Employee employee = new Employee.Builder()
                .name("Zygmunt")
                .lastName("Czerwiński")
                .pesel("88053046992")
                .phone("51 852 85 05")
                .typeOfContract("Umowa zlecenie")
                .employedSince(LocalDate.parse("2017-01-01"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeEmptyTypeOfContract() {
        Employee employee = new Employee.Builder()
                .name("Juliana")
                .lastName("Pawłowska")
                .address("ul. Daszyńskiego Ignacego 89 42-600 Tarnowskie Góry")
                .pesel("88053046992")
                .phone("51 852 85 05")
                .employedSince(LocalDate.parse("2014-09-01"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeEmptyEmployedSince() {
        Employee employee = new Employee.Builder()
                .name("Maryla")
                .lastName("Zielinska")
                .address("ul. Mestwina 13 03-175 Warszawa")
                .pesel("72090962269")
                .phone("66 458 09 15")
                .typeOfContract("B2B")
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeAlreadyExist() {
        Employee employee = new Employee.Builder()
                .name("Franciszek")
                .lastName("Adamski")
                .address("Al. Solidarności 85 31-752 Kraków")
                .pesel("95061558269")
                .phone("78 145 65 68")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Pracownik o podanych danych już istnieje"))
                .statusCode(409);
    }

    @Test
    public void addEmployeePeselTooLong() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeePeselTooShort() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("7810211720")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeePeselWrongFormat() {

    }

    @Test
    public void addEmployeePhoneTooShort() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("78102117205")
                .phone("78")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeePhoneTooLong() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58 4343")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeePhoneWrongFormat() {

    }

    @Test
    public void addEmployeeEmployedSinceWrongFormat() {

    }
    @Test
    public void addEmployeeNameStartsWithWhitespaces() {
        Employee employee = new Employee.Builder()
                .name("  Dominika")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeNameOnlyWhitespaces() {
        Employee employee = new Employee.Builder()
                .name("    ")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeNameEmptyString() {
        Employee employee = new Employee.Builder()
                .name("")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeLastNameStartsWithWhitespaces() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("  Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeLastNameOnlyWhitespaces() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("   ")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeLastNameEmptyString() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeePeselStartsWithWhitespaces() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("  781021172051")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeePeselOnlyWhitespaces() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("           ")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeAddressStartsWithWhitespaces() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address(" ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeAddressOnlyWhitespaces() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address("   ")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeAddressEmptyString() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address("")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeTypeOfContractStartsWithWhitespaces() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract(" B2B")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeTypeOfContractOnlyWhitespaces() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("   ")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeTypeOfContractEmptyString() {
        Employee employee = new Employee.Builder()
                .name("Dominika")
                .lastName("Szymanska")
                .address("ul. Konwaliowa 72 91-859 Łódź")
                .pesel("781021172051")
                .phone("78 408 39 58")
                .typeOfContract("")
                .employedSince(LocalDate.parse("2017-02-10"))
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wypełnij prawidłowo wszystkie pola"))
                .statusCode(500);
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
