package Api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;

import static io.restassured.RestAssured.given;

public class API {

    @Test
    public void postValid() {
        RestAssured.baseURI = "https://test-stand.gb.ru";
        given().urlEncodingEnabled(true)
                .param("username", "3184")
                .param("password", "109a33176f")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post("/login")
                .then().statusCode(200);
    }
    @Test
    public void postInValid() {
        RestAssured.baseURI = "https://test-stand.gb.ru";
        given().urlEncodingEnabled(true)
                .param("username", "31")
                // .param("password", "109a33176f")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post("/login")
                .then().statusCode(401);
    }
    @Test
    public void getNotMyPostNotAuthTest(){
        RestAssured.
                when().get("https://test-stand.gb.ru/api/posts?owner=notMe&sort=createdAt&order=ASC&page=1").
                then().assertThat().statusCode(401);
    }
    @Test
    public void getNotMyPostTest() {
        given()
                .headers("X-Auth-Token", "976855f3f92cfac60e1227815d4f6c45")
                .when()
                .get("https://test-stand.gb.ru/api/posts?owner=notMe&sort=createdAt&order=ASC&page=1")
                .then()
                .statusCode(200);
    }
    @Test
    public void getNotMyPostPositiveTest() {
        given()
                .headers("X-Auth-Token", "976855f3f92cfac60e1227815d4f6c45")
                /*   .queryParam("owner", "notMe")
                   .queryParam("sort", "createdAt")
                   .queryParam("order", "ASC")
                   .queryParam("page", "1")*/

                .expect()
                .body("title", equalTo("жареные сосиски"))
                .body("description", equalTo("Оригинальная подача самых простых жареных сосисок. Самый простой рецепт приготовления сосисок - это"))
                .body("content", equalTo("Оригинальная подача самых простых жареных сосисок. Самый простой рецепт приготовления сосисок - это просто их сварить. Но вкуснее их всё-таки пожарить. Но пожарить их можно необычно, от чего самое простое блюдо - сосиски, будет выглядеть очень красиво."))
                .when()
                .get("https://test-stand.gb.ru/api/posts?owner=notMe&sort=createdAt&order=ASC&page=1");
    }

    @Test
    public void getMyPostNotAuthTest(){
        RestAssured.
                when().get("https://test-stand.gb.ru/api/posts?sort=createdAt&order=ASC&page=1").
                then().assertThat().statusCode(401);
    }
    @Test
    public void getMyPostTest() {
        given()
                .headers("X-Auth-Token", "976855f3f92cfac60e1227815d4f6c45")
                .when()
                .get("https://test-stand.gb.ru/api/posts?sort=createdAt&order=ASC&page=1")
                .then()
                .statusCode(200);
    }

}
