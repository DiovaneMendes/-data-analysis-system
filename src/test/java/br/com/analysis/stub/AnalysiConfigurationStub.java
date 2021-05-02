package br.com.analysis.stub;

import br.com.analysis.configuration.AnalysiConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.file.Path;

public class AnalysiConfigurationStub {

  public static AnalysiConfiguration config(Path path) {
    var analysiConfiguration= new AnalysiConfiguration();
    ReflectionTestUtils.setField(analysiConfiguration, "pathRecording", String.valueOf(path));
    return analysiConfiguration;
  }
}
