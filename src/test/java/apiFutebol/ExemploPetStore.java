package apiFutebol;

import io.restassured.RestAssured;
import org.junit.Test;

public class ExemploPetStore {

    @Test
    public void acrescentarNovoUsuario() {

        String bodyUser = "[{\n" +
                "    \"id\": 123,\n" +
                "    \"username\": \"QA Academy\",\n" +
                "    \"firstName\": \"QA\",\n" +
                "    \"lastName\": \"Academy\",\n" +
                "    \"email\": \"estudante@gmail.com\",\n" +
                "    \"password\": \"123\",\n" +
                "    \"phone\": \"21 980096567\",\n" +
                "    \"userStatus\": 1\n" +
                "  }]\n";


        String url = "https://petstore.swagger.io/v2/user/createWithList";

        RestAssured.given()
                .log().all()
                .header("Content-Type","application/json")
                .body(bodyUser)
                .when()
                .post(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);


    }
}
