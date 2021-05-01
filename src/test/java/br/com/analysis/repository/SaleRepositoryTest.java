package br.com.analysis.repository;

import br.com.analysis.model.SaleModel;
import br.com.analysis.stub.SaleModelStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("SaleRepository")
public class SaleRepositoryTest {

  @BeforeEach
  void setup() {
    SaleRepository.getInstance().clear();
  }

  @Nested
  @DisplayName("Dado que o método [add] seja chamado...")
  class ChamadaAdd {

    @Test
    @DisplayName("Deve retornar true")
    void deveRetornarTrue() {
      var resultado = SaleRepository.getInstance().add(new SaleModel());
      assertTrue(resultado);
    }
  }

  @Nested
  @DisplayName("Dado que o método [clear] seja chamado...")
  class ChamadaClear {

    @Test
    @DisplayName("Deve limpar a lista")
    void deveLimparLista() {
      SaleRepository.getInstance().getAll().addAll(SaleModelStub.list());
      SaleRepository.getInstance().clear();
      var resultado = SaleRepository.getInstance().getAll().size();

      assertEquals(0, resultado);
    }
  }

  @Nested
  @DisplayName("Dado que o método [getAll] seja chamado...")
  class ChamadaGetAll {

    @Test
    @DisplayName("Deve retornar lista")
    void deveRetornarLista() {
      var esperado = SaleModelStub.list();
      SaleRepository.getInstance().getAll().addAll(esperado);

      var resultado = SaleRepository.getInstance().getAll();

      assertEquals(esperado, resultado);
    }
  }
}
