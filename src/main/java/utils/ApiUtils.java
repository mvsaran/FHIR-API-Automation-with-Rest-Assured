package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

/**
 * Utility class for interacting with FHIR APIs using RestAssured.
 */
public class ApiUtils {

    // Default base URL (can be overridden via system property or config file)
   // private static final String BASE_URL = System.getProperty("fhir.base.url", "http://hapi.fhir.org/baseR4");
    public static final String BASE_URL = "http://hapi.fhir.org/baseR4";

    /**
     * Search for a FHIR resource by query parameters.
     */
    public static Response searchResource(String resourceType, String paramName, String paramValue) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .basePath(resourceType)
                .queryParam(paramName, paramValue)
                .get()
                .then()
                .extract()
                .response();
    }

    /**
     * Create a new FHIR resource from JSON string.
     */
    public static Response createResource(String resourceType, String resourceJson) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .basePath(resourceType)
                .contentType(ContentType.JSON)
                .body(resourceJson)
                .post()
                .then()
                .extract()
                .response();
    }

    /**
     * Create a new FHIR resource from JSONObject.
     */
    public static Response createResource(String resourceType, JSONObject resourceJson) {
        return createResource(resourceType, resourceJson.toString());
    }

    /**
     * Read a FHIR resource by ID.
     */
    public static Response getResourceById(String resourceType, String resourceId) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .basePath(resourceType + "/" + resourceId)
                .get()
                .then()
                .extract()
                .response();
    }

    /**
     * Delete a FHIR resource by ID.
     */
    public static Response deleteResource(String resourceType, String resourceId) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .basePath(resourceType + "/" + resourceId)
                .delete()
                .then()
                .extract()
                .response();
    }
}
