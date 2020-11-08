package br.ce.alexandrobs.rest;

import static io.restassured.RestAssured.*;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	
	@Test
	public void testOlaMundo() {
		
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
		//System.out.println(response.getBody().asString().equals("Ola"));
		//System.out.println(response.statusCode() == 200);
		//ao invés de sysout podemos usar a classe Assert do Junit pra ver se esses testes são true
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		//Assert.assertTrue((response.statusCode() == 201));
		//Assert.assertTrue("Erro no Status Code: ",(response.statusCode() == 201));
		Assert.assertEquals(200, response.statusCode());
		
		//throw new RuntimeException();
		
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}
	
	@Test
	public void devoConhecerOutrasFormasRestAssured() {

		//forma prolixa de escrever esse teste
		//por conta do import static da pra usar direto os métodos do RestAssured
		Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		
		//forma de escrever com RestAssured o mesmo teste acima
		//usando import static, fica ainda menor
		get("http://restapi.wcaquino.me/ola").then().statusCode(200);
		
		//agora iremos ver uma terceira forma de usar conhecida como
		//Given When Then
		//pra melhor legibilidade ficando assim
		given() // Pré condições
		.when() // Ação
			.get("http://restapi.wcaquino.me/ola")
		.then() // Assertivas
		.assertThat()
			.statusCode(200);
		
	}

}
