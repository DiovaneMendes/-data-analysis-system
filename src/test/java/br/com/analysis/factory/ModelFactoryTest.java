package br.com.analysis.factory;

import br.com.analysis.exception.FactoryException;
import br.com.analysis.repository.ClientRepository;
import br.com.analysis.repository.SaleRepository;
import br.com.analysis.repository.SellerRepository;
import br.com.analysis.stub.ClientModelStub;
import br.com.analysis.stub.SaleModelStub;
import br.com.analysis.stub.SellerModelStub;
import br.com.analysis.stub.StringStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ModelFactory")
public class ModelFactoryTest {

  @Nested
  @DisplayName("Dado que o método [createModels] seja chamado...")
  class ChamadaCreateModels {

    @Nested
    @DisplayName("Dado que a string passada comece com '001'...")
    class ComecoUm {

      @BeforeEach
      void setup() {
        SellerRepository.getInstance().clear();
      }

      @Test
      @DisplayName("Deve adicionar um SellerModel no repository")
      void adicionarSellerRepository() {
        var parametro = StringStub.comecoUm();
        ModelFactory.createModels(parametro);

        var esperado = Set.of(SellerModelStub.retornoCreate());
        var resultado = SellerRepository.getInstance().getAll();

        assertEquals(esperado, resultado);
      }
    }

    @Nested
    @DisplayName("Dado que a string passada comece com '002'...")
    class ComecoDois {

      @BeforeEach
      void setup() {
        ClientRepository.getInstance().clear();
      }

      @Test
      @DisplayName("Deve adicionar um ClientModel no repository")
      void adicionarClientRepository() {
        var parametro = StringStub.comecoDois();
        ModelFactory.createModels(parametro);

        var esperado = Set.of(ClientModelStub.retornoCreate());
        var resultado = ClientRepository.getInstance().getAll();

        assertEquals(esperado, resultado);
      }
    }

    @Nested
    @DisplayName("Dado que a string passada comece com '003'...")
    class ComecoTres {

      @BeforeEach
      void setup() {
        SaleRepository.getInstance().clear();
      }

      @Test
      @DisplayName("Deve adicionar um SaleModel no repository")
      void adicionarSaleRepository() {
        var parametro = StringStub.comecoTres();
        ModelFactory.createModels(parametro);

        var esperado = Set.of(SaleModelStub.retornoCreate());
        var resultado = SaleRepository.getInstance().getAll();

        assertEquals(esperado, resultado);
      }
    }

    @Nested
    @DisplayName("Dado que a string passada não comece com '001', '002' ou '003'...")
    class ComecoDiferente {

      @Test
      @DisplayName("Deve gerar Exception com a mensagem: 'Could not generate the model: ' mais a string passada")
      void adicionarSellerRepository() {
        var parametro = StringStub.comecoDiferente();
        var mensagem = "Could not generate the model: ".concat(parametro);

        assertThatThrownBy(() -> ModelFactory.createModels(parametro))
          .isInstanceOf(FactoryException.class)
          .hasMessage(mensagem);
      }
    }
  }
}