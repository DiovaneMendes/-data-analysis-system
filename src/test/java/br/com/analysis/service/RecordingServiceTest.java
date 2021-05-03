package br.com.analysis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.analysis.configuration.AnalysiConfiguration;
import br.com.analysis.stub.AnalysiConfigurationStub;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@DisplayName("RecordingService")
public class RecordingServiceTest {

  @TempDir Path tempPath;
  private Path path;
  private AnalysiConfiguration analysiConfiguration;
  private RecordingService recordingService;

  @BeforeEach
  void setup() {
    path = tempPath.resolve("teste.dat");
    recordingService = new RecordingService(AnalysiConfigurationStub.config(path));
  }

  @Nested
  @DisplayName("Dado que o método [recordingReport] seja chamado...")
  class ChamadaRecordingReport {

    @Test
    @DisplayName("Deve criar arquivo no diretório com dado igual ao passado por parametro")
    void deveCriarArquivo() throws IOException {
      var parametro = "TESTE GRAVACAO";
      var esperado = List.of(parametro);

      recordingService.recordingReport(parametro);

      var resultado = Files.readAllLines(path);

      assertEquals(esperado, resultado);
    }
  }
}
