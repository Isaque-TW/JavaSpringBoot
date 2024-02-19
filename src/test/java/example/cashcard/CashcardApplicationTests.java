package example.cashcard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Isso iniciará nosso aplicativo Spring Boot e o disponibilizará para nosso teste para executar solicitações a ele.

class CashcardApplicationTests {
		@Autowired						//  Spring  injeta um auxiliar de teste que nos permitirá fazer solicitações HTTP para o aplicativo em execução localmente.
		TestRestTemplate restTemplate;	// Aqui usamos restTemplate para fazer uma solicitação HTTP GET para o nosso terminal de aplicação /cashcards/99.

		@Test
		void shouldRetundACashCardWhenDataIsSaved() {
			ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/99", String.class);

			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		}

}
