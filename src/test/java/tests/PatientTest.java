package tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import io.restassured.response.Response;
import utils.ApiUtils;
import utils.FakerUtils;

@Epic("FHIR API Automation")
@Feature("Patient Resource Management")
public class PatientTest {

    @Test(description = "Verify that a new Patient resource can be created successfully")
    @Story("Create Patient")
    @Description("This test validates the creation of a new Patient resource in the FHIR server using dynamically generated test data.")
    public void testCreatePatient() {
        JSONObject newPatient = FakerUtils.createRandomPatient();
        Response response = ApiUtils.createResource("Patient", newPatient.toString());

        Assert.assertEquals(response.getStatusCode(), 201, "Patient creation failed!");
        System.out.println("Created Patient ID: " + response.jsonPath().getString("id"));
    }
}
