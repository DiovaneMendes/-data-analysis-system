package br.com.analysis.producer;

import br.com.analysis.repository.SaleRepository;
import br.com.analysis.stub.SaleModelStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("SaleProducer")
@ExtendWith(SpringExtension.class)
public class SaleProducerTest {

  @InjectMocks private SaleProducer saleProducer;

  @Nested
  @DisplayName("Dado que o método [monstExpensiveSale] seja chamado...")
  class ChamadaMonstExpensiveSale {

    @Nested
    @DisplayName("Dado que o retorno seja definido...")
    class RetornoDefinido {

      @BeforeEach
      void setup() {
        clearList();
        SaleRepository.getInstance().getAll().addAll(SaleModelStub.list());
      }

      @Test
      @DisplayName("Deve retornar o id da venda mais cara: 2")
      void deveRetornarIdVendaCara() {
        assertEquals("2", saleProducer.monstExpensiveSale());
      }
    }

    @Nested
    @DisplayName("Dado que a venda mais cara não tenha id definido...")
    class RetornoIdIndefinido {

      @BeforeEach
      void setup() {
        clearList();
        SaleRepository.getInstance().getAll().addAll(SaleModelStub.listNoInformation());
      }

      @Test
      @DisplayName("Deve retornar o id da venda mais cara: No information")
      void deveRetornarIdVendaCara() {
        assertEquals("No information", saleProducer.monstExpensiveSale());
      }
    }

    @Nested
    @DisplayName("Dado que o retorno seja indefinido...")
    class RetornoIndefinido {

      @BeforeEach
      void setup() {
        clearList();
      }

      @Test
      @DisplayName("Deve gerar Exception com a mensagem: 'Error when looking for more expensive sale.'")
      void deveGerarException() {
        assertThatThrownBy(() -> saleProducer.monstExpensiveSale())
          .isInstanceOf(Exception.class)
          .hasMessage("Error when looking for more expensive sale.");
      }
    }
  }

  @Nested
  @DisplayName("Dado que o método [worstSeller] seja chamado...")
  class ChamadaWorstSeller {

    @Nested
    @DisplayName("Dado que o retorno seja definido...")
    class RetornoDefinido {

      @BeforeEach
      void setup() {
        clearList();
        SaleRepository.getInstance().getAll().addAll(SaleModelStub.list());
      }

      @Test
      @DisplayName("Deve retornar o nome do pior vendedor: Seller Name 01")
      void deveRetornarNomePiorVendedor() {
        assertEquals("Seller Name 01", saleProducer.worstSeller());
      }
    }

    @Nested
    @DisplayName("Dado que o nome do pior vendedor seja indefinido...")
    class RetornoNomeIndefinido {

      @BeforeEach
      void setup() {
        clearList();
        SaleRepository.getInstance().getAll().addAll(SaleModelStub.listNoInformation());
      }

      @Test
      @DisplayName("Deve retornar o id da venda mais cara: No information")
      void deveRetornarIdVendaCara() {
        assertEquals("No information", saleProducer.worstSeller());
      }
    }

    @Nested
    @DisplayName("Dado que o retorno seja indefinido...")
    class RetornoIndefinido {

      @BeforeEach
      void setup() {
        clearList();
      }

      @Test
      @DisplayName("Deve gerar Exception com a mensagem: 'Error fetching the worst seller.'")
      void deveGerarException() {
        assertThatThrownBy(() -> saleProducer.worstSeller())
          .isInstanceOf(Exception.class)
          .hasMessage("Error fetching the worst seller.");
      }
    }
  }

  private void clearList() {
    SaleRepository.getInstance().clear();
  }
}
