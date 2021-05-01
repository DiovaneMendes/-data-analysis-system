package br.com.analysis.stub;

import br.com.analysis.configuration.AnalysiConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

public class AnalysiConfigurationStub {

  public static AnalysiConfiguration config() {
    var analysiConfiguration= new AnalysiConfiguration();
    ReflectionTestUtils.setField(analysiConfiguration, "pathRecording", "src/test/resources/out/result.done.dat");
    return analysiConfiguration;
  }
}
