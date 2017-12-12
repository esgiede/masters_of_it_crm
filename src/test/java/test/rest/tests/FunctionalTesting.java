package test.rest.tests;

import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;

public class FunctionalTesting {

    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        RestAssured.port = 8080;


        String basePath;
        basePath = "/";
        RestAssured.basePath = basePath;

        String baseHost;
        baseHost = "http://localhost";
        RestAssured.baseURI = baseHost;

    }

}
