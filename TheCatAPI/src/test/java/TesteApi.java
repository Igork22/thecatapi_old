import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TesteApi {

    String vote_id;

    @Test
    public void cadastro(){
        String url = "https://api.thecatapi.com/v1/user/passwordlesssignup";
        String corpo = "{\"email\": \"igorohricht95@gmail.com\", \"appDescription\": \"Learning automation test\"}";

        // DADO QUE GIVEN
        Response response = given().contentType("application/json").body(corpo).
       // QUANDO ESTIVER COM WHEN
                when().post(url);
       //ENTÃO THEN
                response.then().statusCode(200).body("message", containsString("SUCCESS"));

                System.out.println("RETORNO: =>" + response.body().asString());
    }

    @Test
    public void votacao(){
        String url = "https://api.thecatapi.com/v1/votes/";

        Response response =
                given().contentType("application/json").body("{\"image_id\": \"a_Lc0sHB3\", \"value\": true, \"sub_id\": \"demo-f829cf\"}")
                .when().post(url);
        response.then().statusCode(200).body("message", containsString("SUCCESS"));

        System.out.println("RETORNO: =>" + response.body().asString());
        String id = response.jsonPath().getString("id");
        vote_id = id;
        System.out.println("ID: => " + id);
    }

    @Test
    public void deletaVotacao(){
        votacao();
        deletaVoto();
    }

    @Test
    public void deletaVoto(){
        String url = "https://api.thecatapi.com/v1/votes/{vote_id}";

        Response response =
                given().contentType("application/json")
                        .header("x-api-key", "c21a9ecd-c67b-40ad-acc5-b371e1f82a16")
                        .pathParam("vote_id", "vote_id")
                        .when().delete("https://api.thecatapi.com/v1/votes/{vote_id}");

        System.out.println("RETORNO: =>" + response.body().asString());

       // response.then().statusCode(200).body("message", containsString("SUCCESS"));
    }


}
