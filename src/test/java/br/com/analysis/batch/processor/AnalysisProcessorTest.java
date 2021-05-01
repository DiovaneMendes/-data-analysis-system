package br.com.analysis.batch.processor;

import br.com.analysis.producer.SaleProducer;
import br.com.analysis.stub.ReportModelStub;
import br.com.analysis.stub.StringStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("AnalysisProcessor")
public class AnalysisProcessorTest {

  private AnalysisProcessor analysisProcessor;

  @BeforeEach
  void setup() {
    analysisProcessor = new AnalysisProcessor(new SaleProducer());
  }

  @Nested
  @DisplayName("Dado que o método [process] seja chamado...")
  class ChamadaProcess {

    @Test
    @DisplayName("Deve retornar ReportModel")
    void deveRetornarReportModel() {
      var esperado = ReportModelStub.retornoProcess();
      var parametro = StringStub.listaDados();

      assertEquals(esperado, analysisProcessor.process(parametro));
    }
  }
}
