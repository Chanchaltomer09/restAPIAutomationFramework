package TestClassesAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class firstGetAPI {

    @Test
    public void getAPIusingDirectURL()
    {
        Response res = RestAssured.get("https://reqres.in/api/users/2");
        System.out.println(res.asString());
        System.out.println(res.getStatusCode());
        System.out.println(res.getBody());
        System.out.println(res.getStatusLine());
        System.out.println(res.getTime());

        Assert.assertEquals(res.statusCode(),200);
    }
    @Test
    public void getAPIusingBDDMethod()
    {
        Response res = RestAssured.given().baseUri("https://reqres.in/").basePath("api/users/2").contentType(ContentType.JSON)
                .when().get();
        System.out.println(res.asString());
        System.out.println(res.getStatusCode());
    }
}
