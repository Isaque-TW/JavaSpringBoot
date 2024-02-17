package example.cashcard;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class CashCardJsonTest {

    @Test                                           // A anotação @Test faz parte da biblioteca JUnit e o método assertThat faz parte da biblioteca AssertJ. Ambas as bibliotecas são importadas após a instrução do pacote.
    void myFirstTest() {
        assertThat(1).isEqualTo(1);
    }
}
