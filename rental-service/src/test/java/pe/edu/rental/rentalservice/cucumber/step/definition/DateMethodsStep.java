package pe.edu.rental.rentalservice.cucumber.step.definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pe.edu.rental.rentalservice.rental.model.Publication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class DateMethodsStep {

    private RequestSpecification requestSpecification;
    private Response response;
    private Scenario scenario;

    private int statusCode;

    @Before
    public void before(Scenario scenario){
        this.scenario = scenario;
    }

    @Given("the endpoint date is available")
    public void theEndpointDateIsAvailable() {
        RestAssured.baseURI = "http://localhost:8090/api/v1";
        requestSpecification = given();
    }

    @And("A publication is already stored")
    public void aPublicationIsAlreadyStored() {

    }

    @And("date information is entered")
    public void dateInformationIsEntered(DataTable dataTable) {

        List<List<String>> rows = dataTable.asLists(String.class);
        HashMap date = new HashMap();
        date.put("startDate", rows.get(1).get(0));
        date.put("months", Integer.parseInt(rows.get(1).get(1)));
        date.put("phoneNumber", Integer.parseInt(rows.get(1).get(2)));
        date.put("email", rows.get(1).get(3));
        date.put("description", rows.get(1).get(4));
        date.put("status", Boolean.valueOf(rows.get(1).get(5)));
        date.put("publicationId", Integer.parseInt(rows.get(1).get(6)));
        date.put("tenantId", Integer.parseInt(rows.get(1).get(7)));

        statusCode =
                given()
                        .contentType("application/json")
                        .body(date)
                        .when()
                        .post("http://localhost:8090/api/v1/dates")
                        .then()
                        .extract().statusCode();
    }

    @Given("there is no date created for the post with id {string}")
    public void thereIsNoDateCreatedForThePostWithId(String id) {
        String url = "http://localhost:8090/api/v1/dates/publicationId=" + id;
        List<Publication> res;
        res = given().contentType("application/json").when().get(url).then().extract().body().path("content");
        Assert.assertEquals("0", res.size() + "");
    }

    @Then("response is {string}")
    public void responseIs(String status) {
        Assert.assertEquals(status, statusCode + "");
    }

    @And("exists a date created for the post with id {string}")
    public void existsADateCreatedForThePostWithId(String id) {
        String url = "http://localhost:8090/api/v1/dates/publicationId=" + id;
        List<Publication> res;
        res = given().contentType("application/json").when().get(url).then().extract().body().path("content");
        Assert.assertEquals("1", res.size() + "");
    }
}
