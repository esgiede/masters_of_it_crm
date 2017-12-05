package test.rest.tests;

import com.moi.entity.Employee;
import com.moi.entity.Project;
import org.junit.Test;

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
        project.setStartDate("2017-10-10");
        project.setEndDate("2018-10-10");
        project.setClientId(2);

        given()
                .contentType("application/json")
                .body(project)
                .when().post("/projects").then()
                .statusCode(201);
    }
    @Test
    public void addProjectWithEmployees(){
        Project project = new Project();
        project.setName("Nowy projekt2");
        project.setStartDate("2017-10-10");
        project.setEndDate("2018-10-10");
        project.setClientId(2);

        Set<Employee> employees = new HashSet<>();

        employees.add(new Employee("Jan", "Kowalski", "Front-end developer"));

        project.setEmployees(employees);

        given()
                .contentType("application/json")
                .body(project)
                .when().post("/projects").then()
                .statusCode(201);
    }
    @Test
    public void updateProjectName() {
        Project project = new Project();
        project.setProjectId((long) 2);
        project.setName("Nowa nazwa");
        project.setStartDate("2017-12-10");
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
        project.setProjectId((long) 3);
        project.setName("Test edycji daty rozpoczecia");
        project.setStartDate("2016-10-10");
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
        project.setProjectId((long) 4);
        project.setName("Test edycji daty zakonczenia");
        project.setStartDate("2017-12-09");
        project.setEndDate("2018-10-10");
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
        project.setProjectId((long) 5);
        project.setName("Test edycji klienta");
        project.setStartDate("2017-12-09");
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
        //TO DO
    }
    @Test
    public void addEmployeesToProject (){
        //TO DO
    }
    @Test
    public void deleteEmployeesInProject(){
        //TO DO
    }
    @Test
    public void deleteProject(){
        given().when().delete("projects/9").then().statusCode(204);
    }

}
