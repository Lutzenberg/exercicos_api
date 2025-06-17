package apiFutebol;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

public class TesteApiFutebol {
    @Test
    public void exercicioApiFutebol() {
        String url = "https://api.api-futebol.com.br/v1/campeonatos";

        RestAssured.given()
                .log().all()
                .header("Authorization","Bearer live_a553f448c7a01ae98ac293cdaffd16")
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString("Campeonato Brasileiro 2025"));

    }

}
