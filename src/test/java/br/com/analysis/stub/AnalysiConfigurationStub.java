package br.com.analysis.stub;

import br.com.analysis.configuration.AnalysiConfiguration;
import java.nio.file.Path;
import org.springframework.test.util.ReflectionTestUtils;

public class AnalysiConfigurationStub {

  public static AnalysiConfiguration config(Path path) {
    var analysiConfiguration = new AnalysiConfiguration();
    ReflectionTestUtils.setField(analysiConfiguration, "pathRecording", String.valueOf(path));
    return analysiConfiguration;
  }
}
