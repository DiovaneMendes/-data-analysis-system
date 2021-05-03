package br.com.analysis.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(SpringExtension.class)
@DisplayName("AnalysiConfiguration")
public class AnalysiConfigurationTest {

  private static final String PATH_READING = "/teste/in",
      PATH_RECORDING = "/teste/out",
      NAME_JOB = "BATCH_JOB",
      NAME_STEP = "BATCH_STEP";

  @InjectMocks private AnalysiConfiguration analysiConfiguration;

  @BeforeEach
  void setup() {
    ReflectionTestUtils.setField(analysiConfiguration, "pathReading", PATH_READING);
    ReflectionTestUtils.setField(analysiConfiguration, "pathRecording", PATH_RECORDING);
    ReflectionTestUtils.setField(analysiConfiguration, "nameJob", NAME_JOB);
    ReflectionTestUtils.setField(analysiConfiguration, "nameStep", NAME_STEP);
  }

  @Nested
  @DisplayName("Dado que [getPathReading] seja chamado...")
  class ChamadaPathReading {

    @Test
    @DisplayName("Deve retornar '/teste/in'")
    void deveRetornarPathIn() {
      assertEquals(PATH_READING, analysiConfiguration.getPathReading());
    }
  }

  @Nested
  @DisplayName("Dado que [getPathRecording] seja chamado...")
  class ChamadaPathRecording {

    @Test
    @DisplayName("Deve retornar '/teste/out'")
    void deveRetornarPathOut() {
      assertEquals(PATH_RECORDING, analysiConfiguration.getPathRecording());
    }
  }

  @Nested
  @DisplayName("Dado que [getNameJob] seja chamado...")
  class ChamadaNameJob {

    @Test
    @DisplayName("Deve retornar 'BATCH_JOB'")
    void deveRetornarBatchJob() {
      assertEquals(NAME_JOB, analysiConfiguration.getNameJob());
    }
  }

  @Nested
  @DisplayName("Dado que [getNameStep] seja chamado...")
  class ChamadaNameStep {

    @Test
    @DisplayName("Deve retornar 'BATCH_STEP'")
    void deveRetornarBatchStep() {
      assertEquals(NAME_STEP, analysiConfiguration.getNameStep());
    }
  }
}
