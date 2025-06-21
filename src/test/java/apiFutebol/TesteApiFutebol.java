package apiFutebol;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class TesteApiFutebol {
    @Test
    public void exercicioApiFutebol() {
        String url = "https://api.api-futebol.com.br/v1/campeonatos";

        RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer live_a553f448c7a01ae98ac293cdaffd16")
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString("Campeonato Brasileiro 2025"));

    }

    @Test
    public void exemploExtracaoDeInformacaoJsonPath() {
        String url = "https://api.api-futebol.com.br/v1/campeonatos/14/tabela";

        String primeiroColocado = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer live_a553f448c7a01ae98ac293cdaffd16")
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("[0].time.nome_popular");

        System.out.print("O primeiro colcoado é: " + primeiroColocado);

    }

    @Test
    public void exemploExtracaoDeInformacaoResponseJsonPath() {

        Response response;

        String url = "https://api.api-futebol.com.br/v1/campeonatos/14/tabela";

        response = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer live_a553f448c7a01ae98ac293cdaffd16")
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        String primeiroColocado, segundoColocado, terceiroColocado, quartoColocado;

        primeiroColocado = response.path("[0].time.nome_popular");
        segundoColocado = response.path("[1].time.nome_popular");
        terceiroColocado = response.path("[2].time.nome_popular");
        quartoColocado = response.path("[3].time.nome_popular");

        System.out.println("1º colocado: " + primeiroColocado);
        System.out.println("2º colocado: " + segundoColocado);
        System.out.println("3º colocado: " + terceiroColocado);
        System.out.println("4º colocado: " + quartoColocado);


    }

    @Test
    public void tabelaCampeonatoExercicio01E02() {

        Response response;

        String url = "https://api.api-futebol.com.br/v1/campeonatos/14/tabela";

        response = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer live_a553f448c7a01ae98ac293cdaffd16")
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        for (int i = 0; i < 20; i++) {
            System.out.println((i + 1) + "º colocado: " + response.path("[" + i + "].time.nome_popular"));
        }
        int pontosPrimeiro = response.path("[0].pontos");
        int pontosUltimo = response.path("[19].pontos");

        Assert.assertTrue("O primeiro colocado deveria ter mais pontos que o último colocado.", pontosPrimeiro > pontosUltimo);
    }
}
