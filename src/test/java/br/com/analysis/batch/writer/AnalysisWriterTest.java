package br.com.analysis.batch.writer;

import br.com.analysis.model.ClientModel;
import br.com.analysis.model.SaleModel;
import br.com.analysis.model.SellerModel;
import br.com.analysis.repository.ClientRepository;
import br.com.analysis.repository.SaleRepository;
import br.com.analysis.repository.SellerRepository;
import br.com.analysis.service.RecordingService;
import br.com.analysis.stub.AnalysiConfigurationStub;
import br.com.analysis.stub.ReportModelStub;
import br.com.analysis.stub.StringStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("AnalysisWriter")
public class AnalysisWriterTest {

  private Path path;
  @TempDir Path tempPath;
  private AnalysisWriter analysisWriter;

  @BeforeEach
  void setup() {
    path = tempPath.resolve("resolv.done.dat");
    var recordingService = new RecordingService(AnalysiConfigurationStub.config(path));
    analysisWriter = new AnalysisWriter(recordingService);
  }

  @Nested
  @DisplayName("Dado que o método [write] seja chamado...")
  class ChamadaWrite {

    @BeforeEach
    void setup() {
      popularRepositorios();
      var parametro = List.of(ReportModelStub.parametroWrite());
      analysisWriter.write(parametro);
    }

    @Test
    @DisplayName("Deve gravar arquivo 'result.done.dat' com relatório")
    void deveGravarArquivoComRelatorio() throws IOException {
      var esperado = StringStub.conteudoRelatorio();
      assertLinesMatch(esperado, Files.readAllLines(path));
    }

    @Test
    @DisplayName("Deve limpar ClientRepository")
    void deveLimparClientRepository() {
      var result = ClientRepository.getInstance().getAll().isEmpty();
      assertTrue(result);
    }

    @Test
    @DisplayName("Deve limpar SellerRepository")
    void deveLimparSellerRepository() {
      var result = SellerRepository.getInstance().getAll().isEmpty();
      assertTrue(result);
    }

    @Test
    @DisplayName("Deve limpar SaleRepository")
    void deveLimparSaleRepository() {
      var result = SaleRepository.getInstance().getAll().isEmpty();
      assertTrue(result);
    }
  }

  private void popularRepositorios() {
    ClientRepository.getInstance().add(new ClientModel());
    SellerRepository.getInstance().add(new SellerModel());
    SaleRepository.getInstance().add(new SaleModel());
  }
}
