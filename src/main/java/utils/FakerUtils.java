package utils;

import com.github.javafaker.Faker;
import org.json.JSONObject;

public class FakerUtils {
    private static final Faker faker = new Faker();

    public static JSONObject createRandomPatient() {
        JSONObject patient = new JSONObject();
        patient.put("resourceType", "Patient");
        patient.put("name", new Object[] {
                new JSONObject().put("family", faker.name().lastName())
                        .put("given", new String[]{faker.name().firstName()})
        });
        patient.put("gender", faker.options().option("male", "female"));
        patient.put("birthDate", "1980-01-01");
        return patient;
    }

    public static JSONObject createRandomCondition(String patientId) {
        JSONObject condition = new JSONObject();
        condition.put("resourceType", "Condition");
        condition.put("subject", new JSONObject().put("reference", "Patient/" + patientId));
        condition.put("code", new JSONObject().put("text", faker.medical().diseaseName()));
        return condition;
    }

    public static JSONObject createRandomEncounter(String patientId) {
        JSONObject encounter = new JSONObject();
        encounter.put("resourceType", "Encounter");
        encounter.put("status", "finished");
        encounter.put("class", new JSONObject().put("code", "AMB"));
        encounter.put("subject", new JSONObject().put("reference", "Patient/" + patientId));
        return encounter;
    }

    public static JSONObject createRandomAppointment(String patientId) {
        JSONObject appointment = new JSONObject();
        appointment.put("resourceType", "Appointment");
        appointment.put("status", "booked");
        appointment.put("description", "Checkup Appointment");
        appointment.put("participant", new Object[] {
                new JSONObject().put("actor", new JSONObject().put("reference", "Patient/" + patientId))
        });
        return appointment;
    }
}
