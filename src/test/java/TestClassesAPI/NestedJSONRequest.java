package TestClassesAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestedJSONRequest {
    /*Sample JSON
    [ {  "firstName" :"Chanchal",
        "lastName" : "Tomer",
        "gender": "female",
        "age": 32,
        "salary": 10000.32
    } ,
    {  "firstName" :"Aniket",
        "lastName" : "Jaiswal",
         "gender": "male",
          "age": 33,
          "salary": 10000.32
    }  ]

     */
    @Test
    //Method1 : Using JSON ARRAY
    public void NestedJSONUsingJSONArray()
    {
        RestAssured.baseURI="https://reqres.in/";
        RequestSpecification req=RestAssured.given();

        JSONObject js1= new JSONObject();
        js1.put("firstName","Chanchal");
        js1.put("lastName","Tomer");
        js1.put("Gender","Female");

        JSONObject js2= new JSONObject();
        js2.put("firstName","Aniket");
        js2.put("lastName","Jaiswal");
        js2.put("Gender","male");

        JSONObject js3= new JSONObject();
        js3.put("firstName","Sita");
        js3.put("lastName","Sharma");
        js3.put("Gender","Female");

        JSONArray js = new JSONArray();
        js.add(js1);
        js.add(js2);
        js.add(js3);
        Response res = req.basePath("api/users").contentType(ContentType.JSON).body(js).when().post();
        System.out.println(res.asPrettyString());
    }

    //Method 2 : Using List or Set
    @Test
    public void NestedJSONRequestUsingList()
    {
        Map<String,Object> js1=new HashMap<>();
        Map<String,Object> js2=new HashMap<>();
        Map<String,Object> js3=new HashMap<>();

        js1.put("firstName","Chanchal");
        js1.put("lastName","Tomer");
        js1.put("Gender","Female");

        js2.put("firstName","Aniket");
        js2.put("lastName","Jaiswal");
        js2.put("Gender","male");

        js3.put("firstName","Sita");
        js3.put("lastName","Sharma");
        js3.put("Gender","Female");

        List<Map<String,Object>> js=new ArrayList<>();

        js.add(js1);
        js.add(js2);
        js.add(js3);
        RequestSpecification req= RestAssured.given().baseUri("https://reqres.in/");
        Response res = req.basePath("api/users").contentType(ContentType.JSON).body(js).when().post();
        System.out.println(res.asPrettyString());


    }
}
