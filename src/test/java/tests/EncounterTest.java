package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;
import utils.FakerUtils;

public class EncounterTest extends BaseTest {

    @Test
    public void testCreateAndSearchEncounter() {
        // 1. Create Patient
        Response patientRes = ApiUtils.createResource("Patient", FakerUtils.createRandomPatient());
        String patientId = patientRes.jsonPath().getString("id");

        // 2. Create Encounter
        Response encounterRes = ApiUtils.createResource("Encounter", FakerUtils.createRandomEncounter(patientId));
        Assert.assertEquals(encounterRes.getStatusCode(), 201, "Encounter creation failed!");
        String encounterId = encounterRes.jsonPath().getString("id");

        // 3. Fetch Encounter
        Response getRes = ApiUtils.getResourceById("Encounter", encounterId);
        Assert.assertEquals(getRes.getStatusCode(), 200);

        // 4. Search Encounter by Patient
        Response searchRes = ApiUtils.searchResource("Encounter", "patient", patientId);
        Assert.assertEquals(searchRes.getStatusCode(), 200);

        // 5. Cleanup
        ApiUtils.deleteResource("Encounter", encounterId);
        ApiUtils.deleteResource("Patient", patientId);
    }
}
