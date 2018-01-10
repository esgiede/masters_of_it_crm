package test.rest.tests;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class ProjectsHasEmployeesRestTest {

    @Test
    public void basicPingTest(){
        given().when().get("phe").then().statusCode(200);
    }
    @Test
    public void badRequest(){
        given().when().get("phe/b").then().statusCode(404);
    }
    @Test
    public void wrongUrl(){
        given().when().get("phee").then().statusCode(404);
    }
    @Test
    public void methodNotSupportedDelete(){
        given().when().delete("phe").then().statusCode(405);
    }
    @Test
    public void methodNotSupportedPost(){
        given().when().post("phe/11").then().statusCode(405);
    }
    @Test
    public void methodNotSupportedPut(){ given().when().put("phe").then().statusCode(405); }
    /*@Test
    public void verifyPhe() {
        given().when().get("/phe/1").then()
                .body("employee",equalTo(given()
                        .when().get("/employees/1").asString()))
                .statusCode(200);
    }*/
    @Test
    public void pheNotFound() {
        given().when().get("/phe/20").then()
                .statusCode(404);
    }
    //public void addPhe()
    //public void addPheConflict()
    //public void updatePheProject()
    //public void updatePheEmployee()
    //public void updatePheRole()
    //public void updatePhe()
    //public void addPheEmptyProject()
    //public void addPheEmptyEmployee()
    //public void addPheEmptyRole()
    @Test
    public void deletePhe(){ given().when().delete("phe/1").then().statusCode(204); }
    @Test
    public void deletePheNotFound(){ given().when().delete("phe/21").then().statusCode(404); }
}
