package TestClassesAPI;

import POJOClasses.Address;
import POJOClasses.employeePOJO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class SerializationDeserialization {
    @Test
    public void testSerialization() throws JsonProcessingException {
        employeePOJO emp = new employeePOJO();
        emp.setFirstName("Chanchal");
        emp.setLastName("Tomer");
        emp.setGender("Female");
        emp.setAge(32);
        emp.setMarried(true);

        Address address=new Address();
        address.setCity("Agra");
        address.setPin(110078);
        address.setState("UP");
        address.setStreet("Kashiganj");

        emp.setAddress(address);
        //Serialization : Converting class object into stream of bytes i.e json data
        ObjectMapper obj = new ObjectMapper();
        String payload=obj.writerWithDefaultPrettyPrinter().writeValueAsString(emp);

        RequestSpecification req= RestAssured.given().baseUri("https://reqres.in/");
        Response res = req.basePath("api/users").contentType(ContentType.JSON).body(payload).when().post();

        System.out.println(res.asPrettyString());

        //Deserialization
           employeePOJO e1=obj.readValue(payload,employeePOJO.class);

           System.out.println(e1.getFirstName());
    }
}
