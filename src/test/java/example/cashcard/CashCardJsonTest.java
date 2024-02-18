package example.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest // A anotação @JsonTest marca o CashCardJsonTest como uma classe de teste que
          // usa o framework Jackson (que é incluído como parte do Spring). Isto fornece
          // um suporte extensivo a testes e análises JSON. Também estabelece todo o
          // comportamento relacionado para testar objectos JSON.
public class CashCardJsonTest {

    @Autowired // @Autowired é uma anotação que direciona o Spring para criar um objeto do tipo
               // solicitado.
    private JacksonTester<CashCard> json; // O JacksonTester é um invólucro de conveniência para a biblioteca de análise
                                          // de Jackson JSON. Ele lida com a serialização e desserialização de objectos
                                          // JSON.

    @Test
    void cashCardSerializationTest() throws IOException {
        CashCard cashCard = new CashCard(99L, 123.45);
        assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
                .isEqualTo(123.45);
    }

    @Test
    void cashCardDeserializationTest() throws IOException {
        String expected = """
                {
                    "id":99,
                    "amount":123.45
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new CashCard(99L, 123.45));
        assertThat(json.parseObject(expected).id()).isEqualTo(99);
        assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
    }
}
