package br.com.analysis.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AnalysiConfiguration {

  @Value("${path.reading}")
  private String pathReading;

  @Value("${path.recording}")
  private String pathRecording;

  @Value("${batch.job}")
  private String nameJob;

  @Value("${batch.step}")
  private String nameStep;
}
