package test.rest.tests;

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
    public void methodNotSupportedPut(){ given().when().put("projects/12").then().statusCode(405); }
    @Test
    public void verifyProjectName() {
        given().when().get("/projects/1").then()
                .body("name",equalTo("Wykonanie aplikacji webowej"))
                .statusCode(200);
    }
    @Test
    public void addProject() {
        Project project = new Project();
        project.setName("Nowy projekt");
        project.setStartDate(LocalDate.parse("2017-10-10"));
        project.setEndDate(LocalDate.parse("2018-10-10"));
        project.setClientId(2);

        given()
                .contentType("application/json")
                .body(project)
                .when().post("/projects").then()
                .statusCode(201);
    }
    ///////////////////////////////////////////////////////
    @Test
    public void addEmployeeToProject(){
        Project project = new Project();
        project.setId((long)10);
        project.setName("Nowy projekt");
        project.setStartDate(LocalDate.parse("2017-10-10"));
        project.setEndDate(LocalDate.parse("2018-10-10"));
        project.setClientId(2);

        Set<Employee> employees = new HashSet<>();

        employees.add(given()
        .body(project)
        .when().get("/employees/2")
        .as(Employee.class));

        project.setEmployees(employees);

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects").then()
                .statusCode(200);
    }
    @Test
    public void updateProjectName() {
        Project project = new Project();
        project.setId((long) 2);
        project.setName("Nowa nazwa");
        project.setStartDate(LocalDate.parse("2017-12-10"));
        project.setEndDate(null);
        project.setClientId(1);

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects").then()
                .statusCode(200);

        given().when().get("/projects/2").then()
                .body("name",equalTo("Nowa nazwa"))
                .statusCode(200);
    }
    @Test
    public void updateStartDate() {
        Project project = new Project();
        project.setId((long) 3);
        project.setName("Test edycji daty rozpoczecia");
        project.setStartDate(LocalDate.parse("2016-10-10"));
        project.setEndDate(null);
        project.setClientId(1);

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects").then()
                .statusCode(200);

        given().when().get("/projects/3").then()
                .body("startDate",equalTo("2016-10-10"))
                .statusCode(200);
    }
    @Test
    public void updateEndDate() {
        Project project = new Project();
        project.setId((long) 4);
        project.setName("Test edycji daty zakonczenia");
        project.setStartDate(LocalDate.parse("2017-12-09"));
        project.setEndDate(LocalDate.parse("2018-10-10"));
        project.setClientId(1);

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects").then()
                .statusCode(200);

        given().when().get("/projects/4").then()
                .body("endDate",equalTo("2018-10-10"))
                .statusCode(200);
    }
    @Test
    public void updateClientId() {
        Project project = new Project();
        project.setId((long) 5);
        project.setName("Test edycji klienta");
        project.setStartDate(LocalDate.parse("2017-12-09"));
        project.setEndDate(null);
        project.setClientId(3);

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects").then()
                .statusCode(200);

        given().when().get("/projects/5").then()
                .body("clientId",equalTo(3))
                .statusCode(200);
    }
    @Test
    public void editEmployeesInProject(){
        Project project = new Project();
        project.setId((long)6);
        project.setName("Test edycji pracownikow");
        project.setStartDate(LocalDate.parse("2017-12-09"));
        project.setEndDate(null);
        project.setClientId(1);

        Set<Employee> employees = new HashSet<>();

        employees.add(given()
                .body(project)
                .when().get("/employees/3")
                .as(Employee.class));

        project.setEmployees(employees);

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects").then()
                .statusCode(200);
    }
    @Test
    public void deleteEmployeesInProject(){
        Project project = new Project();
        project.setId((long)8);
        project.setName("Test usuwania pracownikow");
        project.setStartDate(LocalDate.parse("2017-12-09"));
        project.setEndDate(LocalDate.parse("2017-12-09"));
        project.setClientId(1);

        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects").then()
                .statusCode(200);
    }
    @Test
    public void deleteProject(){
        given().when().delete("projects/9").then().statusCode(204);
    }
    @Test
    public void addProjectEmptyName() {
        Project project = new Project();
        project.setName(null);
        project.setStartDate(LocalDate.parse("2017-12-09"));
        project.setEndDate(null);
        project.setClientId(3);

        given()
                .contentType("application/json")
                .body(project)
                .when().post("/projects").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }
    @Test
    public void addProjectEmptyStartDate(){
        Project project = new Project();
        project.setName("Pusta data rozpoczecia");
        project.setStartDate(null);
        project.setEndDate(null);
        project.setClientId(3);

        given()
                .contentType("application/json")
                .body(project)
                .when().post("/projects").then()
                .body("message", equalTo("Wprowadź poprawnie wszystkie parametry"))
                .statusCode(500);
    }


}
