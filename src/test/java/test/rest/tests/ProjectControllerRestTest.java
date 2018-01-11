package test.rest.tests;

import com.moi.entity.Client;
import com.moi.entity.Project;
import org.junit.Test;

import java.time.LocalDate;

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
                .body("name",equalTo("Wykonanie strony internetowej"))
                .statusCode(200);
    }
    @Test
    public void projectNotFound() {
        given().when().get("/projects/20").then()
                .statusCode(404);
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
    @Test
    public void addProjectConflict() {
        Project project = new Project.Builder()
                .name("Konflikt")
                .startDate(LocalDate.parse("2017-10-10"))
                .endDate(LocalDate.parse("2018-10-10"))
                .build();
        given()
                .contentType("application/json")
                .body(project)
                .when().post("/projects").then()
                .statusCode(409);
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
        given()
                .when().get("/projects/2").then()
                .body("name",equalTo("Nowa nazwa"))
                .statusCode(200);
    }
    @Test
    public void updateProjectNotFound() {
        Project project = new Project.Builder()
                .name("Nowa nazwa2")
                .startDate(LocalDate.parse("2017-12-10"))
                .endDate(null)
                .build();
        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/21").then()
                .statusCode(404);
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
        given()
                .when().get("/projects/3").then()
                .body("startDate",equalTo("2016-10-10"))
                .statusCode(200);
    }
    @Test
    public void updateEndDate() {
        Project project = new Project.Builder()
                .name("Test edycji daty zakończenia")
                .startDate(LocalDate.parse("2017-12-09"))
                .endDate(LocalDate.parse("2018-10-10"))
                .build();
        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/4").then()
                .statusCode(200);
        given()
                .when().get("/projects/4").then()
                .body("endDate",equalTo("2018-10-10"))
                .statusCode(200);
    }
    @Test
    public void deleteProject(){
        given().when().delete("projects/5").then().statusCode(204);
    }
    @Test
    public void deleteProjectNotFound(){
        given().when().delete("projects/22").then().statusCode(404);
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
                .when().put("/projects/6").then()
                .statusCode(200);
        given()
                .when().get("/projects/6").then()
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
                .when().put("/projects/7").then()
                .statusCode(200);
        given()
                .when().get("/projects/7").then()
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
                        .when().get("/clients/2")
                        .as(Client.class)))
                .build();
        given()
                .contentType("application/json")
                .body(project)
                .when().put("/projects/8").then()
                .statusCode(200);
        given()
                .when().get("/projects/8").then()
                .body("name",equalTo("Klient zmieniony"))
                .statusCode(200);
    }
    @Test
    public void getPageSize(){
        given()
                .when().get("/projects?size=2").then()
                .body("size", equalTo(2))
                .statusCode(200);
    }
    @Test
    public void getPageNumber(){
        given()
                .when().get("/projects?page=1&size=2").then()
                .body("number",equalTo(1))
                .statusCode(200);
    }
    @Test
    public void getPageElements(){
        given()
                .when().get("/projects?page=0&size=2").then()
                .body("numberOfElements",equalTo(2))
                .statusCode(200);
    }
}
