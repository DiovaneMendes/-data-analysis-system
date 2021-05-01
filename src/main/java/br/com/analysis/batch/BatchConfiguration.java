package br.com.analysis.batch;

import br.com.analysis.batch.processor.AnalysisProcessor;
import br.com.analysis.batch.reader.AnalysisReader;
import br.com.analysis.batch.writer.AnalysisWriter;
import br.com.analysis.configuration.AnalysiConfiguration;
import br.com.analysis.model.ReportModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

  private final AnalysisReader analysisReader;
  private final AnalysisWriter analysisWriter;
  private final AnalysisProcessor analysisProcessor;
  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final AnalysiConfiguration analysiConfiguration;

  @Bean
  public Job analysisSyncronizationJob() {
    return jobBuilderFactory
            .get(analysiConfiguration.getNameJob())
            .start(analysisSyncronizationStep())
            .build();
  }

  @Bean
  public Step analysisSyncronizationStep() {
    var stepName = analysiConfiguration.getNameStep();
    log.info("Initializing step {}", stepName);

    return stepBuilderFactory
        .get(stepName)
        .<List<String>, ReportModel>chunk(5)
        .reader(analysisReader)
        .processor(analysisProcessor)
        .writer(analysisWriter)
        .build();
  }
}
