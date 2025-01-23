package TestClassesAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.junit.jupiter.api.Test;

import java.io.File;

public class uploadFileInRest {
    @Test
    public void uploadFileAsRequest()
    {
        //create file instance
        File file = new File("src/test/resources/TestData/JSONFiles/companyDetails.JSON");

        RequestSpecification req = RestAssured.given().baseUri("https://reqres.in/").basePath("api/users").multiPart("file",file).contentType("multipart/form-data");
        Response res= req.post();

        System.out.println(res.prettyPrint());
        System.out.println("****************************************************  To print request body  ********");
        System.out.println(SpecificationQuerier.query(req).getContentType().toString());

    }
}
