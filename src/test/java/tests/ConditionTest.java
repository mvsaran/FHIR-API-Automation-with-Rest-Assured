package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;
import utils.FakerUtils;

public class ConditionTest extends BaseTest {

    @Test
    public void testCreateAndSearchCondition() {
        // 1. Create Patient
        Response patientRes = ApiUtils.createResource("Patient", FakerUtils.createRandomPatient());
        String patientId = patientRes.jsonPath().getString("id");

        // 2. Create Condition for Patient
        Response conditionRes = ApiUtils.createResource("Condition", FakerUtils.createRandomCondition(patientId));
        Assert.assertEquals(conditionRes.getStatusCode(), 201, "Condition creation failed!");
        String conditionId = conditionRes.jsonPath().getString("id");

        // 3. Fetch Condition
        Response getRes = ApiUtils.getResourceById("Condition", conditionId);
        Assert.assertEquals(getRes.getStatusCode(), 200);
        Assert.assertTrue(getRes.asString().contains("Condition"));

        // 4. Search Condition by Patient
        Response searchRes = ApiUtils.searchResource("Condition", "patient", patientId);
        Assert.assertEquals(searchRes.getStatusCode(), 200);

        // 5. Cleanup
        ApiUtils.deleteResource("Condition", conditionId);
        ApiUtils.deleteResource("Patient", patientId);
    }
}
