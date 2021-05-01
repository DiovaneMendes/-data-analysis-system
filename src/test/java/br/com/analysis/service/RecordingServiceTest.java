package br.com.analysis.service;

import br.com.analysis.configuration.AnalysiConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("RecordingService")
@ExtendWith(SpringExtension.class)
public class RecordingServiceTest {

  @Mock private AnalysiConfiguration analysiConfiguration;
  @InjectMocks private RecordingService recordingService;

  @Nested
  @DisplayName("Dado que o método [recordingReport] seja chamado...")
  class ChamadaRecordingReport {

    @BeforeEach
    void setup() {
      when(analysiConfiguration.getPathRecording()).thenReturn("src/test/resources/out/teste.dat");
    }

    @Test
    @DisplayName("Deve criar arquivo no diretório")
    void deveCriarArquivo() throws IOException {
      var diretorioOut = "src/test/resources/out/teste.dat";
      var parametro = "TESTE";
      var esperado = Paths.get(diretorioOut);

      recordingService.recordingReport(parametro);

      var resultado = Files.list(Paths.get("src/test/resources/out/")).findFirst().get();

      assertEquals(esperado, resultado);

      (new File(diretorioOut)).delete();
    }
  }
}
