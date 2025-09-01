package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;
import utils.FakerUtils;

public class AppointmentTest extends BaseTest {

    @Test
    public void testCreateAndSearchAppointment() {
        // 1. Create Patient
        Response patientRes = ApiUtils.createResource("Patient", FakerUtils.createRandomPatient());
        String patientId = patientRes.jsonPath().getString("id");

        // 2. Create Appointment
        Response appointmentRes = ApiUtils.createResource("Appointment", FakerUtils.createRandomAppointment(patientId));
        Assert.assertEquals(appointmentRes.getStatusCode(), 201, "Appointment creation failed!");
        String appointmentId = appointmentRes.jsonPath().getString("id");

        // 3. Fetch Appointment
        Response getRes = ApiUtils.getResourceById("Appointment", appointmentId);
        Assert.assertEquals(getRes.getStatusCode(), 200);

        // 4. Search Appointment by Patient
        Response searchRes = ApiUtils.searchResource("Appointment", "actor", patientId);
        Assert.assertEquals(searchRes.getStatusCode(), 200);

        // 5. Cleanup
        ApiUtils.deleteResource("Appointment", appointmentId);
        ApiUtils.deleteResource("Patient", patientId);
    }
}
