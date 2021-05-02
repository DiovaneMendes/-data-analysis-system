package br.com.analysis;

import br.com.analysis.batch.BatchConfiguration;
import br.com.analysis.configuration.AnalysiConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@EnableBatchProcessing
@RequiredArgsConstructor
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {

	private final JobLauncher jobLauncher;
	private final BatchConfiguration batchConfig;
	private final AnalysiConfiguration analysiConfig;

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}

	@SneakyThrows
	@Scheduled(fixedDelay = 3000)
	public void executeProcedure() {
		log.info("### Executing procedure ###");

		var params = new JobParametersBuilder()
			.addString(analysiConfig.getNameJob(), String.valueOf(System.currentTimeMillis()))
			.toJobParameters();

		jobLauncher.run(batchConfig.analysisSyncronizationJob(), params);
	}
}
