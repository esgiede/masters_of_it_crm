package test.rest.tests;

import com.moi.entity.Employee;
import org.junit.Test;

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
    public void methodNotSupportedPost() {
        given().when().post("employees/11").then().statusCode(405);
    }

    @Test
    public void methodNotSupportedPut() {
        given().when().put("employees").then().statusCode(405);
    }

    @Test
    public void verifyEmployeeName() {
        given().when().get("/employees/1").then()
                .body("name", equalTo("Jan"))
                .body("lastName", equalTo("Nowak"))
                .statusCode(200);
    }

    @Test
    public void employeeNotFound() {
        given().when().get("/employees/20").then()
                .statusCode(404);
    }

    @Test
    public void addEmployee() {
        Employee employee = new Employee.Builder()
                .name("Janusz")
                .lastName("Chwastek")
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .statusCode(201);
    }

    @Test
    public void addEmployeeConflict() {
        Employee employee = new Employee.Builder()
                .name("Janusz")
                .lastName("Chwastek")
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .statusCode(409);
    }

    @Test
    public void updateEmployeeName() {
        Employee employee = new Employee.Builder()
                .name("Wyedytowane")
                .lastName("Imienia")
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().put("/employees/2").then()
                .statusCode(200);
        given()
                .when().get("/employees/2").then()
                .body("name", equalTo("Wyedytowane"))
                .statusCode(200);
    }

    @Test
    public void updateEmployeeLastName() {
        Employee employee = new Employee.Builder()
                .name("Edycja")
                .lastName("Wyedytowane")
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().put("/employees/3").then()
                .statusCode(200);
        given()
                .when().get("/employees/3").then()
                .body("lastName", equalTo("Wyedytowane"))
                .statusCode(200);
    }

    @Test
    public void updateEmployee() {
        Employee employee = new Employee.Builder()
                .name("Wyedytowany")
                .lastName("Wpis")
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().put("/employees/4").then()
                .statusCode(200);
        given()
                .when().get("/employees/4").then()
                .body("name", equalTo("Wyedytowany"))
                .body("lastName", equalTo("Wpis"))
                .statusCode(200);
    }

    @Test
    public void deleteEmployee() {
        given().when().delete("employees/5").then().statusCode(204);
    }

    @Test
    public void deleteEmployeeNotFound() {
        given().when().delete("employees/22").then().statusCode(404);
    }

    @Test
    public void deleteEmployeeInProject() {
        given().when().delete("employees/7").then().statusCode(409);
    }

    @Test
    public void addEmployeeEmptyName() {
        Employee employee = new Employee.Builder()
                .name(null)
                .lastName("Wpis")
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }

    @Test
    public void addEmployeeEmptyLastName() {
        Employee employee = new Employee.Builder()
                .name("Jan")
                .lastName(null)
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }

    @Test
    public void updateEmployeeNotFound() {
        Employee employee = new Employee.Builder()
                .name("Wyedytowany")
                .lastName("Wpis2")
                .build();
        given()
                .contentType("application/json")
                .body(employee)
                .when().put("/employees/21").then()
                .statusCode(404);
    }
}
