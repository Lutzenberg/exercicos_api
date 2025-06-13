import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class TestePrimeiraApi {

    String url = "http://localhost:8080/api";
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();

    @Test
    public void exercicio01TesteComSucessoPrimeiraApi() {

        String path = "/primeiraApi";

        given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(containsString("Retorno da api com sucesso!!!"));

    }

    @Test
    public void exercicio02_TesteQueryParamPrimeiraApi() {

        String path = "/primeiraApiV1";
        String textoQueryParam = "QA Academy";

        given()
                .queryParam("palavra", textoQueryParam)
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(containsString(textoQueryParam));

    }

    @Test
    public void exercicio03TestePathParamPrimeiraApi() {

        String textPathParam = "QA Academy";
        String path = "/primeiraApiV2/" + textPathParam;

        given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(containsString(textPathParam));

    }
}