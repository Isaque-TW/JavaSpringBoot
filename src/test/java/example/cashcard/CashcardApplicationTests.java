package example.cashcard;

import static org.assertj.core.api.Assertions.assertThat;

import javax.swing.text.Document;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Isso iniciará nosso aplicativo Spring
																			// Boot e o disponibilizará para nosso teste
																			// para executar solicitações a ele.

class CashcardApplicationTests {
	@Autowired // Spring injeta um auxiliar de teste que nos permitirá fazer solicitações HTTP
				// para o aplicativo em execução localmente.
	TestRestTemplate restTemplate; // Aqui usamos restTemplate para fazer uma solicitação HTTP GET para o nosso
									// terminal de aplicação /cashcards/99.

	@Test
	void shouldRetundACashCardWhenDataIsSaved() {
		ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/99", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		// Isso converte a resposta String em um objeto JSON-aware com muitos métodos
		// auxiliares.
		DocumentContext documentContext = JsonPath.parse(response.getBody());

		// Esperamos que quando solicitamos um Cash Card com id de 99, um objeto JSON
		// será retornado com algo no campo id. Por enquanto, afirme que o id não é
		// nulo.
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(99);

		Double amount = documentContext.read("$.amount");
		assertThat(amount).isEqualTo(123.45);
	}

	@Test
	void shouldNotReturnACashCardWithAnUknownId() {
		ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/1000", 
		String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	
	}
}

