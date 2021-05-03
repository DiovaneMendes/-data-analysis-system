package br.com.analysis.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.analysis.model.ClientModel;
import br.com.analysis.stub.ClientModelStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ClientRepository")
public class ClientRepositoryTest {

  @BeforeEach
  void setup() {
    ClientRepository.getInstance().clear();
  }

  @Nested
  @DisplayName("Dado que o método [add] seja chamado...")
  class ChamadaAdd {

    @Test
    @DisplayName("Deve retornar true")
    void deveRetornarTrue() {
      var resultado = ClientRepository.getInstance().add(new ClientModel());
      assertTrue(resultado);
    }
  }

  @Nested
  @DisplayName("Dado que o método [clear] seja chamado...")
  class ChamadaClear {

    @Test
    @DisplayName("Deve limpar a lista")
    void deveLimparLista() {
      ClientRepository.getInstance().getAll().addAll(ClientModelStub.list());
      ClientRepository.getInstance().clear();
      var resultado = ClientRepository.getInstance().getAll().size();

      assertEquals(0, resultado);
    }
  }

  @Nested
  @DisplayName("Dado que o método [getAll] seja chamado...")
  class ChamadaGetAll {

    @Test
    @DisplayName("Deve retornar lista")
    void deveRetornarLista() {
      var esperado = ClientModelStub.list();
      ClientRepository.getInstance().getAll().addAll(esperado);

      var resultado = ClientRepository.getInstance().getAll();

      assertEquals(esperado, resultado);
    }
  }
}
