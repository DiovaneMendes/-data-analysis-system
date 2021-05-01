package br.com.analysis.service;

import br.com.analysis.configuration.AnalysiConfiguration;
import br.com.analysis.stub.StringStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("ReadingService")
@ExtendWith(SpringExtension.class)
public class ReadingServiceTest {

  @Mock private AnalysiConfiguration analysiConfiguration;
  @InjectMocks private ReadingService readingService;

  @Nested
  @DisplayName("Dado que o método [fileSearch] seja chamado...")
  class ChamadaFileSearch {

    @Nested
    @DisplayName("Dado que não haja erro na busca dos arquivos...")
    class SemErroBuscaArquivo {

      @BeforeEach
      void setup() {
        when(analysiConfiguration.getPathReading()).thenReturn("src/test/resources/in");
      }

      @Test
      @DisplayName("Deve retornar lista com um path")
      void deveRetornarListaPath() {
        var esperado = List.of(Paths.get("src/test/resources/in/arquivoTesteDat.dat"));
        var resultado = readingService.fileSearch();

        assertEquals(esperado, resultado);
      }
    }

    @Nested
    @DisplayName("Dado que haja erro na busca dos arquivos...")
    class ErroBuscaArquivo {

      @BeforeEach
      void setup() {
        when(analysiConfiguration.getPathReading()).thenReturn("src/test/poc/resources");
      }

      @Test
      @DisplayName("Deve retornar lista vazia")
      void deveRetornarListaVazia() {
        var resultado = readingService.fileSearch();

        assertEquals(Collections.emptyList(), resultado);
      }
    }
  }

  @Nested
  @DisplayName("Dado que o método [readData] seja chamado...")
  class ChamadaReadData {

    @Nested
    @DisplayName("Dado que não haja erro na busca dos arquivos...")
    class SemErroBuscaArquivo {

      @BeforeEach
      void setup() {
        when(analysiConfiguration.getPathReading()).thenReturn("src/test/resources/in");
      }

      @Test
      @DisplayName("Deve retornar lista(String) com as linhas do arquivo")
      void deveRetornarListaPath() {
        var parametro = Paths.get("src/test/resources/in/arquivoTesteDat.dat");
        var resultado = readingService.readData().apply(parametro);
        var esperado = StringStub.listaDados();

        assertEquals(esperado, resultado);
      }
    }

    @Nested
    @DisplayName("Dado que haja erro na busca dos arquivos...")
    class ErroBuscaArquivo {

      @BeforeEach
      void setup() {
        when(analysiConfiguration.getPathReading()).thenReturn("src/test/resources");
      }

      @Test
      @DisplayName("Deve retornar lista vazia")
      void deveRetornarListaVazia() {
        var parametro = Paths.get("src/test/resources/arquivoTesteDat.dat");
        var resultado = readingService.readData().apply(parametro);

        assertEquals(Collections.emptyList(), resultado);
      }
    }
  }
}
