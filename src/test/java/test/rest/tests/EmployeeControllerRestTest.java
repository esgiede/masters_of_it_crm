package test.rest.tests;

import com.moi.entity.Employee;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class EmployeeControllerRestTest {

    @Test
    public void basicPingTest(){
        given().when().get("employees").then().statusCode(200);
    }
    @Test
    public void badRequest(){
        given().when().get("embloyees/b").then().statusCode(404);
    }
    @Test
    public void wrongUrl(){
        given().when().get("employes").then().statusCode(404);
    }
    @Test
    public void methodNotSupportedDelete(){ given().when().delete("employees").then().statusCode(405); }
    @Test
    public void methodNotSupportedPost(){
        given().when().post("employees/11").then().statusCode(405);
    }
    @Test
    public void methodNotSupportedPut(){ given().when().put("employees/12").then().statusCode(405); }
    @Test
    public void verifyEmployeeName() {
        given().when().get("/employees/1").then()
                .body("name",equalTo("Jan"))
                .body("lastName",equalTo("Kowalski"))
                .statusCode(200);
    }
    @Test
    public void addEmployee() {

        Employee employee = new Employee.Builder()
                .name("Test")
                .lastName("Dodawania")
                .role("Analyst")
                .build();

        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .statusCode(201);
    }
    @Test
    public void updateEmployeeName() {

        Employee employee = new Employee.Builder()
                .id((long) 6)
                .name("Wyedytowane")
                .lastName("Imienia")
                .role("Project manager")
                .build();

        given()
                .contentType("application/json")
                .body(employee)
                .when().put("/employees").then()
                .statusCode(200);

        given().when().get("/employees/6").then()
                .body("name",equalTo("Wyedytowane"))
                .statusCode(200);
    }
    @Test
    public void updateEmployeeLastName() {

        Employee employee = new Employee.Builder()
                .id((long) 7)
                .name("Edycja")
                .lastName("Wyedytowane")
                .role("Project manager")
                .build();

        given()
                .contentType("application/json")
                .body(employee)
                .when().put("/employees").then()
                .statusCode(200);

        given().when().get("/employees/7").then()
                .body("lastName",equalTo("Wyedytowane"))
                .statusCode(200);
    }
    @Test
    public void updateEmployeeRole() {

        Employee employee = new Employee.Builder()
                .id((long) 8)
                .name("Edycja")
                .lastName("Stanowiska")
                .role("Analyst")
                .build();

        given()
                .contentType("application/json")
                .body(employee)
                .when().put("/employees").then()
                .statusCode(200);

        given().when().get("/employees/8").then()
                .body("role",equalTo("Analyst"))
                .statusCode(200);
    }
    @Test
    public void updateEmployee() {

        Employee employee = new Employee.Builder()
                .id((long) 9)
                .name("Wyedytowany")
                .lastName("Wpis")
                .role("Analyst")
                .build();

        given()
                .contentType("application/json")
                .body(employee)
                .when().put("/employees").then()
                .statusCode(200);

        given().when().get("/employees/9").then()
                .body("name",equalTo("Wyedytowany"))
                .body("lastName",equalTo("Wpis"))
                .body("role",equalTo("Analyst"))
                .statusCode(200);
    }
    @Test
    public void deleteEmployee() {
        given().when().delete("employees/10").then().statusCode(204);
    }
    @Test
    public void addEmployeeEmptyName() {

        Employee employee = new Employee.Builder()
                .name(null)
                .lastName("Wpis")
                .role("Analyst")
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
                .role("Analyst")
                .build();

        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
    @Test
    public void addEmployeeEmptyRole() {

        Employee employee = new Employee.Builder()
                .name("Jan")
                .lastName("Kowalski")
                .role(null)
                .build();

        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
    @Test
    public void addEmployeeInvalidRole() {

        Employee employee = new Employee.Builder()
                .name("Jan")
                .lastName("Kowalski")
                .role("invalid role")
                .build();

        given()
                .contentType("application/json")
                .body(employee)
                .when().post("/employees").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }

}
