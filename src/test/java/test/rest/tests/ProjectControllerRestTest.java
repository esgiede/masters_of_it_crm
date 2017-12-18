package test.rest.tests;

import com.moi.entity.Client;
import com.moi.entity.Employee;
import com.moi.entity.Project;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProjectControllerRestTest {

    @Test
    public void basicPingTest(){
        given().when().get("projects").then().statusCode(200);
    }
    @Test
    public void badRequest(){
        given().when().get("projects/b").then().statusCode(404);
    }
    @Test
    public void wrongUrl(){
        given().when().get("project").then().statusCode(404);
    }
    @Test
    public void methodNotSupportedDelete(){
        given().when().delete("projects").then().statusCode(405);
    }
    @Test
    public void methodNotSupportedPost(){
        given().when().post("projects/11").then().statusCode(405);
    }
    @Test
    public void methodNotSupportedPut(){ given().when().put("projects").then().statusCode(405); }
    @Test
    public void verifyProjectName() {
        given().when().get("/projects/1").then()
                .body("name",equalTo("Wykonanie aplikacji webowej"))
                .statusCode(200);
    }
    @Test
    public void addProject() {

        Project project = new Project.Builder()
                .name("Nowy projekt")
                .startDate(LocalDate.parse("2017-10-10"))
                .endDate(LocalDate.parse("2018-10-10"))
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().post("/projects").then()
                .statusCode(201);
    }
    ///////////////////////////////////////////////////////
    @Test
    public void addEmployeeToProject(){

        Set<Employee> employees = new HashSet<>();

        employees.add(given()
                .when().get("/employees/3")
                .as(Employee.class));

        employees.add(given()
                .when().get("/employees/2")
                .as(Employee.class));

        employees.add(given()
                .when().get("/employees/5")
                .as(Employee.class));

        Project project = new Project.Builder()
                .name("Nowy projekt")
                .startDate(LocalDate.parse("2017-10-10"))
                .endDate(LocalDate.parse("2018-10-10"))
                .employees(employees)
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/13").then()
                .statusCode(200);
    }
    @Test
    public void updateProjectName() {

        Project project = new Project.Builder()
                .name("Nowa nazwa")
                .startDate(LocalDate.parse("2017-12-10"))
                .endDate(null)
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/2").then()
                .statusCode(200);

        given().when().get("/projects/2").then()
                .body("name",equalTo("Nowa nazwa"))
                .statusCode(200);
    }
    @Test
    public void updateStartDate() {

        Project project = new Project.Builder()
                .name("Test edycji daty rozpoczecia")
                .startDate(LocalDate.parse("2016-10-10"))
                .endDate(null)
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/3").then()
                .statusCode(200);

        given().when().get("/projects/3").then()
                .body("startDate",equalTo("2016-10-10"))
                .statusCode(200);
    }
    @Test
    public void updateEndDate() {

        Project project = new Project.Builder()
                .name("Test edycji daty zakonczenia")
                .startDate(LocalDate.parse("2017-12-09"))
                .endDate(LocalDate.parse("2018-10-10"))
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/4").then()
                .statusCode(200);

        given().when().get("/projects/4").then()
                .body("endDate",equalTo("2018-10-10"))
                .statusCode(200);
    }
    @Test
    public void editEmployeesInProject(){

        Set<Employee> employees = new HashSet<>();

        employees.add(given()
                .when().get("/employees/3")
                .as(Employee.class));

        Project project = new Project.Builder()
                .name("Test edycji pracownikow")
                .startDate(LocalDate.parse("2017-12-09"))
                .endDate(null)
                .employees(employees)
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/6").then()
                .statusCode(200);
    }
    @Test
    public void deleteEmployeesInProject(){

        Project project = new Project.Builder()
                .name("Test usuwania pracownikow")
                .startDate(LocalDate.parse("2017-12-09"))
                .endDate(LocalDate.parse("2017-12-09"))
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/8").then()
                .statusCode(200);
    }
    @Test
    public void deleteProject(){
        given().when().delete("projects/9").then().statusCode(204);
    }
    @Test
    public void addProjectEmptyName() {

        Project project = new Project.Builder()
                .name(null)
                .startDate(LocalDate.parse("2017-12-09"))
                .endDate(null)
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().post("/projects").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
    @Test
    public void addProjectEmptyStartDate(){

        Project project = new Project.Builder()
                .name("Pusta data rozpoczecia")
                .startDate(null)
                .endDate(null)
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().post("/projects").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
    @Test
    public void addClientToProject(){

        Project project = new Project.Builder()
                .name("Klient dodany")
                .startDate(LocalDate.parse("2017-12-09"))
                .endDate(null)
                .client((given()
                        .when().get("/clients/1")
                        .as(Client.class)))
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/10").then()
                .statusCode(200);

        given().when().get("/projects/10").then()
                .body("name",equalTo("Klient dodany"))
                .statusCode(200);

    }
    @Test
    public void deleteClientInProject(){

        Project project = new Project.Builder()
                .name("Klient usuniety")
                .startDate(LocalDate.parse("2017-12-09"))
                .endDate(null)
                .client(null)
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/11").then()
                .statusCode(200);

        given().when().get("/projects/11").then()
                .body("name",equalTo("Klient usuniety"))
                .statusCode(200);

    }
    @Test
    public void updateClientInProject(){

        Project project = new Project.Builder()
                .name("Klient zmieniony")
                .startDate(LocalDate.parse("2017-12-09"))
                .endDate(null)
                .client((given()
                        .when().get("/clients/1")
                        .as(Client.class)))
                .build();

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/12").then()
                .statusCode(200);

        given().when().get("/projects/12").then()
                .body("name",equalTo("Klient zmieniony"))
                .statusCode(200);

    }


}
