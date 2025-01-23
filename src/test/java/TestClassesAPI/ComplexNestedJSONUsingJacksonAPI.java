package TestClassesAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSObject;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

public class ComplexNestedJSONUsingJacksonAPI {

    /*
    { "firstName":"Chanchal",
      "lastName" : "Tomer:,
      "age" : 32,
      "TechnicalSkill" :
      { "Coding":"Java",
         "WebAutomation":"Selenium"
      }
    }
     */

    @Test
    public void createNestedJSONRequest() throws JsonProcessingException {
        //Create Object Mapper instance
        ObjectMapper objectMapper=new ObjectMapper();

        //create object node
        ObjectNode user1= objectMapper.createObjectNode();
        user1.put("firstName","Chanchal");
        user1.put("lastName","Tomer");
        user1.put("Gender","Female");
        user1.put("age",32);

        //Create another Object Node for Technical skill
        ObjectNode techSkill= objectMapper.createObjectNode();
        techSkill.put("coding","java");
        techSkill.put("WebAutomation","Selenium");

        user1.put("TechSkill",techSkill);
        RequestSpecification req= RestAssured.given().baseUri("https://reqres.in/");
        Response res = req.basePath("api/users").contentType(ContentType.JSON).body(user1).when().post();

        //System.out.println(res.asPrettyString());

        //print userDetails
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1));
        //print field value
        System.out.println("firstname = "+user1.get("firstName"));
        //print field values
        Iterator<String> itr =user1.fieldNames();
        while(itr.hasNext())
        {
            System.out.println(itr.next());
        }
        //print only values
        Iterator<JsonNode> itr1 =user1.elements();
        while(itr1.hasNext())
        {
            System.out.println(itr1.next());
        }
    }
}
