package TestClassesAPI;

import Utilities.randomEmailGenerator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class TypesOfAuthExample {
    String accessToken;

    @Test
    public void basicAuthEx()
    {
        Response res=RestAssured.given().baseUri("http://echo.getpostman.com//basic-auth").auth().basic("postman","postman").get();
        Assert.assertEquals(res.statusCode(),200);
        System.out.println(res.statusLine());
    }

    @Test
    public void DigestAuthEx()
    {
        Response res=RestAssured.given().baseUri("http://echo.getpostman.com//digest-auth").auth().basic("postman","postman").get();
        Assert.assertEquals(res.statusCode(),200);
        System.out.println(res.statusLine());
    }
    @Test
    public void BearerTokenAuthEx()
    {
        String token ="Bearer daad3c55725fd3cdcfe05591c81d9ca32f4a434d31fbfc6593c660997d246c3d";

        JSONObject js = new JSONObject();
        js.put("name","chanchal78j88");
        js.put("gender","female");
        String mail= randomEmailGenerator.getComplexRandomMail();
        js.put("email",mail);
        js.put("status","Active");

        RequestSpecification req=RestAssured.given().baseUri("https://gorest.co.in").basePath("/public/v2/users/").headers("Authorization",token).contentType(ContentType.JSON).body(js.toJSONString());
        Response res=req.post();
        Assert.assertEquals(res.statusCode(),201);
        System.out.println(res.statusLine());
        System.out.println(res.body().asString());
    }
    @Test
    public void APIKeyAythEx()
    {

        String apiKey ="683a199e3438bc9b8f22fe8ea6b52fe8";

        RequestSpecification req=RestAssured.given().baseUri("https://api.openweathermap.org").basePath("/data/2.5/weather/").queryParam("q" ,"nagpur").queryParam("appid",apiKey);
        Response res=req.get();
        Assert.assertEquals(res.statusCode(),200);
        System.out.println(res.statusLine());
        System.out.println(res.body().asString());
    }
    @Test
    public void OAuth2Example1()
    {

            String clientId="AZpDKq3m5hqSdnIaKD9ppBuFaEdMCdbMo7LrTv7q10cnXVE_7yXfktTAKxJWhvvyhkcaYLVxuc3Sgs2Z";
        String clientSecret="EABftUAlGZLr2_w1xQ7ud_uh9_UqChrA6HZg_77qVCOdwIMHM6IPGmD_QxBhIfG3tz-_s0E12O4DjOXZ";

        RequestSpecification req=RestAssured.given().baseUri("https://api-m.sandbox.paypal.com").basePath("/v1/oauth2/token/").auth().preemptive().basic(clientId,clientSecret)
                .param("grant_type","client_credentials");
        Response res=req.post();
        Assert.assertEquals(res.statusCode(),200);
        System.out.println(res.statusLine());
        System.out.println(res.body().asString());
        accessToken = res.getBody().jsonPath().get("access_token");
        System.out.println("access token = "+accessToken);
    }
    @Test(dependsOnMethods="OAuth2Example1")
    public void OAuth2Example2()
    {
        String token=accessToken;
        RequestSpecification req=RestAssured.given().baseUri("https://api-m.sandbox.paypal.com/").basePath("/v2/invoicing/invoices").auth().oauth2(token)
                .queryParam("page",3)
                .queryParam("page_size",4).queryParam("total_count_required",true);
        Response res=req.get();
        Assert.assertEquals(res.statusCode(),200);
        System.out.println(res.statusLine());
        System.out.println(res.body().asString());
    }

}
