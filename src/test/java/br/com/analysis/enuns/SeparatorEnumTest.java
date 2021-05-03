package br.com.analysis.enuns;

import static br.com.analysis.enuns.SeparatorEnum.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("SeparatorEnum")
public class SeparatorEnumTest {

  @Nested
  @DisplayName("Dado que o DATA seja chamado...")
  class ChamadaData {

    @Test
    @DisplayName("Deve ter value 'ç'")
    void deveTerValueCedilha() {
      assertEquals("ç", DATA.getValue());
    }
  }

  @Nested
  @DisplayName("Dado que o ITEM seja chamado...")
  class ChamadaItem {

    @Test
    @DisplayName("Deve ter value ','")
    void deveTerValueVirgula() {
      assertEquals(",", ITEM.getValue());
    }
  }

  @Nested
  @DisplayName("Dado que o INFO_ITEM seja chamado...")
  class ChamadaInfoItem {

    @Test
    @DisplayName("Deve ter value '-'")
    void deveTerValueTraco() {
      assertEquals("-", INFO_ITEM.getValue());
    }
  }
}
