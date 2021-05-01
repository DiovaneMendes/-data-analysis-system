package br.com.analysis.batch.reader;

import br.com.analysis.service.ReadingService;
import br.com.analysis.stub.StringStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@DisplayName("AnalysisReader")
@ExtendWith(SpringExtension.class)
public class AnalysisReaderTest {

  @Mock private ReadingService readingService;
  @InjectMocks private AnalysisReader analysisReader;

  @Nested
  @DisplayName("Dado que o método [read] seja chamado...")
  class ChamadaRead {

    @Nested
    @DisplayName("Dado que o retorno do [fileSearch] seja uma lista vazia...")
    class ListaVazia {

      @BeforeEach
      void setup() {
        when(readingService.fileSearch()).thenReturn(Collections.emptyList());
      }

      @Test
      @DisplayName("Deve retornar null")
      void deveRetornarNull() {
        assertNull(analysisReader.read());
      }
    }

    @Nested
    @DisplayName("Dado que o retorno do [fileSearch] não seja uma lista vazia...")
    class ListaComPath {

      @BeforeEach
      void setup() {
        var paths = List.of(Paths.get("src/test/resources/in/arquivoTesteDat.dat"));
        when(readingService.fileSearch()).thenReturn(paths);
        when(readingService.readData()).thenCallRealMethod();
      }

      @Test
      @DisplayName("Deve retornar deve retornar lista com conteudo de cada linha do arquivo")
      void deveRetornarNull() {
        var resultado = analysisReader.read();
        var esperado = StringStub.listaDados();

        assertEquals(esperado, resultado);
      }
    }
  }
}
