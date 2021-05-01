package br.com.analysis.batch.writer;

import br.com.analysis.configuration.AnalysiConfiguration;
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
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@DisplayName("AnalysisWriter")
public class AnalysisWriterTest {

  private AnalysisWriter analysisWriter;

  @BeforeEach
  void setup() {
    var recordingService = new RecordingService(AnalysiConfigurationStub.config());
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

    @AfterEach
    void after() {
      (new File("src/test/resources/out/result.done.dat")).delete();
    }

    @Test
    @DisplayName("Deve gravar arquivo 'result.done.dat' com relatório")
    void deveGravarArquivoComRelatorio() throws IOException {
      var esperado = StringStub.conteudoRelatorio();
      assertEquals(esperado, resultado());
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

  private List<String> resultado() throws IOException {
    var path = Paths.get("src/test/resources/out/");

    return Files.list(path)
      .map(p -> {
        try {
          return Files.readAllLines(p);
        } catch (IOException e) {
          return null;
        }
      })
      .filter(Objects::nonNull)
      .flatMap(List::stream)
      .collect(Collectors.toList());
  }

  private void popularRepositorios() {
    ClientRepository.getInstance().add(new ClientModel());
    SellerRepository.getInstance().add(new SellerModel());
    SaleRepository.getInstance().add(new SaleModel());
  }
}
