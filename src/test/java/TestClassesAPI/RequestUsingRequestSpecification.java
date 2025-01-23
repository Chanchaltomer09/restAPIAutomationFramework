package TestClassesAPI;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class RequestUsingRequestSpecification {

    @Test
    public void getAPIUsingRequestSpecification()
    {
        RestAssured.baseURI="https://reqres.in/";
        RestAssured.basePath="api/users/3";
        RequestSpecification req=RestAssured.given();
        Response res = req.get();

        System.out.println(res.asString());
        System.out.println(res.getStatusCode());
        System.out.println(res.getBody());
        System.out.println(res.getStatusLine());
        System.out.println(res.getTime());

        Assert.assertEquals(res.statusCode(),200);
        // Check headers
        System.out.println("**************************************************");
        Headers headers= res.getHeaders();
        for(Header header: headers)
        {
            System.out.println("key :"+ header.getName() + " Value : "+ header.getValue());
        }

        // check JSON Response body
        System.out.println("**************************************************");
        String firstname = res.jsonPath().getString("data.first_name");
        //System.out.println(firstname);
        Assert.assertEquals(firstname,"Emma");

        //retrieve Request Specofication
        System.out.println("**************************************************");
        QueryableRequestSpecification req1= SpecificationQuerier.query(req);
        String basepath= req1.getBasePath();
        System.out.println(basepath);
    }
}
