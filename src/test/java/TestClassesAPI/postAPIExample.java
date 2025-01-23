package TestClassesAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class postAPIExample {

    @Test
    public void postSingleUserDetailsUsingJsonObject()
    {
        RestAssured.baseURI="https://reqres.in/api/users";
        JSONObject js= new JSONObject();
        js.put("firstName","Chanchal");
        js.put("lastName","Tomer");
        js.put("isPostRequest",true);
        Response res =
                RestAssured.given().contentType(ContentType.JSON).body(js.toJSONString()).when().post();
        System.out.println(res.asPrettyString());
        System.out.println("id =" + res.getBody().jsonPath().getString("id"));

        Assert.assertEquals(res.statusCode(),201);

    }
    @Test
    public void postSingleUserDetailsUsingMap()
    {
        RestAssured.baseURI="https://reqres.in/api/users";
        Map<String,Object> js= new HashMap<>();
        js.put("firstName","Chanchal");
        js.put("lastName","Tomer");
        js.put("isPostRequest",true);
        Response res =
                RestAssured.given().contentType(ContentType.JSON).body(js).when().post();
        System.out.println(res.asPrettyString());
        System.out.println("id =" + res.getBody().jsonPath().getString("id"));

        Assert.assertEquals(res.statusCode(),201);

    }
    @Test
    public void postJSONArrayRequestUsingJsonObject()
    {
        RestAssured.baseURI="https://reqres.in/api/users";
        JSONObject js1= new JSONObject();
        js1.put("firstName","Chanchal");
        js1.put("lastName","Tomer");
        js1.put("Gender","Female");

        JSONObject js2= new JSONObject();
        js2.put("firstName","Suresh");
        js2.put("lastName","Yadav");
        js2.put("Gender","male");

        JSONObject js3= new JSONObject();
        js3.put("firstName","Sita");
        js3.put("lastName","Sharma");
        js3.put("Gender","Female");

        JSONArray js = new JSONArray();
        js.add(js1);
        js.add(js2);
        js.add(js3);
        Response res =
                RestAssured.given().contentType(ContentType.JSON).body(js).when().post();
        System.out.println(res.asPrettyString());
        //System.out.println("id =" + res.getBody().jsonPath().getString("id"));

        Assert.assertEquals(res.statusCode(),201);

    }
}
