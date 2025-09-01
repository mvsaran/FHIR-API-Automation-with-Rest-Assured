package base;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import utils.ApiUtils;


public class BaseTest {
    @BeforeClass
    public void setup() {
        //RestAssured.baseURI = "http://hapi.fhir.org/baseR4";
        
        // Auto-read from ApiUtils, no hardcoding
        RestAssured.baseURI = ApiUtils.BASE_URL;
        
        
    }
}