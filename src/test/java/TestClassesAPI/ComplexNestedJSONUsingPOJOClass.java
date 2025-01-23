package TestClassesAPI;
/*
{"CompanyName":"XYZLtd",
"Street":"Africa Avenue",
"City":"RK Puram , Delhi",
"State":"New Delhi",
"pinCode":110019,
"BankAccounts":["HDFC,"SBI","AXIS"],
"employees":
 [
   { "firstName":"Chanchal",
      "lastName":"Tomer",
      "gender":"female",
       "age":33,
      "salary":110000,
      "Address":{"Street":"Govindpuri",
                  "City":"Vijaywada",
                  "State": "Andra Pradesh",
                  "pinCode" : 530012
      }

   },
   {  "firstName":"Suresh",
     "lastName":"Yadav",
      "gender":"male",
      "age":35,
      "salary":11000,
      "Address":{"Street":"Kalkaji",
                  "City":"Delhi",
                  "State": "New Delhi",
                  "pinCode" : 110018
      }
   }
  ]
}
 */

import POJOClasses.Address;
import POJOClasses.CompanyDetails;
import POJOClasses.employeePOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ComplexNestedJSONUsingPOJOClass {
    @Test
    public  void createUser() throws JsonProcessingException {
        CompanyDetails e1= new CompanyDetails();
        e1.setCompanyName("XYZ Ltd");
        e1.setCity("RK Puram , Delhi");
        e1.setState("New Delhi");
        e1.setPinCode(110019);
        e1.setStreet("Delhi");

        List<String> BankAccounts= new ArrayList<>();
        BankAccounts.add("HDFC");BankAccounts.add("AXIS");
        BankAccounts.add("SBI");
        e1.setBankAccounts(BankAccounts);

        List<employeePOJO> emp= new ArrayList<>();
        employeePOJO empDetails1= new employeePOJO();
        empDetails1.setFirstName("Chanchal");
        empDetails1.setLastName("Tomer");
        empDetails1.setAge(33);
        empDetails1.setGender("female");
        empDetails1.setSalary(1100000);

        Address add1= new Address();
        add1.setStreet("Govindpuri");
        add1.setCity("Vijaywada");
        add1.setState("Andhra Pradesh");
        add1.setPin(530012);

        empDetails1.setAddress(add1);

        //adding second employee details
        employeePOJO empDetails2= new employeePOJO();
        empDetails2.setFirstName("Suresh");
        empDetails2.setLastName("Yadav");
        empDetails2.setAge(35);
        empDetails2.setGender("male");
        empDetails2.setSalary(110000);

        Address add2= new Address();
        add2.setStreet("Kalkaji");
        add2.setCity("Delhi");
        add2.setState("New Delhi");
        add2.setPin(110018);

        empDetails2.setAddress(add2);

        //Adding employees details to company details
        emp.add(empDetails2);emp.add(empDetails1);
        e1.setEmployees(emp);

        ObjectMapper obj = new ObjectMapper();
        String payload= obj.writerWithDefaultPrettyPrinter().writeValueAsString(e1);

        RequestSpecification req= RestAssured.given().baseUri("https://reqres.in/").basePath("api/users").body(payload);
        Response res= req.post();

        System.out.println(res.prettyPrint());
        System.out.println("****************************************************  To print request body  ********");
        System.out.println(SpecificationQuerier.query(req).getBody().toString());
    }
}
