package br.com.analysis.util;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("StringUtil")
public class StringUtilTest {

  @Nested
  @DisplayName("Dado que o método [getIdentifier] seja chamado...")
  class ChamadaGetIdentifier {

    @Nested
    @DisplayName("Dado que a string passada tenha sequência de 3 números no começo...")
    class SequenciaNumeros {

      @Test
      @DisplayName("Deve retornar essa sequência de números")
      void deveRetornarSequencia() {
        assertEquals("010", StringUtil.getIdentifier("010testeçregex"));
      }
    }

    @Nested
    @DisplayName("Dado que a string passada não tenha sequência de 3 números no começo...")
    class SemSequenciaNumeros {

      @Test
      @DisplayName("Deve retornar string vazia")
      void deveRetornarStringVazia() {
        assertEquals("", StringUtil.getIdentifier("diotesteçregex"));
      }
    }
  }

  @Nested
  @DisplayName("Dado que o método [removeBrackets] seja chamado...")
  class ChamadaRemoveBrackets {

    @Nested
    @DisplayName("Dado que a string passada tenha colchetes...")
    class StringColchete {

      @Test
      @DisplayName("Deve retornar parametro sem colchetes")
      void deveRetornarSemColchetes() {
        var parametro = "teste[caixa][0]cadeado789";
        assertEquals("testecaixa0cadeado789", StringUtil.removeBrackets(parametro));
      }
    }

    @Nested
    @DisplayName("Dado que a string passada não tenha colchetes...")
    class StringSemColchete {

      @Test
      @DisplayName("Deve retornar paramentro")
      void deveRetornarProprioParametro() {
        var parametro = "computador153teclado";
        assertEquals(parametro, StringUtil.removeBrackets(parametro));
      }
    }
  }

  @Nested
  @DisplayName("Dado que o método [isFileDat] seja chamado...")
  class ChamadaIsFileDat {

    @Nested
    @DisplayName("Dado que o Path passado seja do tipo '.dat'...")
    class PathDat {

      @Test
      @DisplayName("Deve retornar true")
      void deveRetornarTrue() {
        var parametro = Paths.get("src/test/resources/in/arquivoTesteDat.dat");
        assertTrue(StringUtil.isFileDat(parametro));
      }
    }

    @Nested
    @DisplayName("Dado que o Path passado seja diferente do tipo '.dat'...")
    class PathDiferenteDat {

      @Test
      @DisplayName("Deve retornar false")
      void deveRetornarFalse() {
        var parametro = Paths.get("src/test/resources/in/arquivoTesteTxt.txt");
        assertFalse(StringUtil.isFileDat(parametro));
      }
    }
  }
}
