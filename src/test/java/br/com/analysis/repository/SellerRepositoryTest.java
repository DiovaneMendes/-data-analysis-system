package br.com.analysis.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.analysis.model.SellerModel;
import br.com.analysis.stub.SellerModelStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("SellerRepository")
public class SellerRepositoryTest {

  @BeforeEach
  void setup() {
    SellerRepository.getInstance().clear();
  }

  @Nested
  @DisplayName("Dado que o método [add] seja chamado...")
  class ChamadaAdd {

    @Test
    @DisplayName("Deve retornar true")
    void deveRetornarTrue() {
      var resultado = SellerRepository.getInstance().add(new SellerModel());
      assertTrue(resultado);
    }
  }

  @Nested
  @DisplayName("Dado que o método [clear] seja chamado...")
  class ChamadaClear {

    @Test
    @DisplayName("Deve limpar a lista")
    void deveLimparLista() {
      SellerRepository.getInstance().getAll().addAll(SellerModelStub.list());
      SellerRepository.getInstance().clear();
      var resultado = SellerRepository.getInstance().getAll().size();

      assertEquals(0, resultado);
    }
  }

  @Nested
  @DisplayName("Dado que o método [getAll] seja chamado...")
  class ChamadaGetAll {

    @Test
    @DisplayName("Deve retornar lista")
    void deveRetornarLista() {
      var esperado = SellerModelStub.list();
      SellerRepository.getInstance().getAll().addAll(esperado);

      var resultado = SellerRepository.getInstance().getAll();

      assertEquals(esperado, resultado);
    }
  }
}
