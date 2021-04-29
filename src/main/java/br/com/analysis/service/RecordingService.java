package br.com.analysis.service;

import br.com.analysis.configuration.AnalysiConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordingService {

  private final AnalysiConfiguration configuration;

  public void recordingReport(String report) {
    try{
      var path = Path.of(configuration.getPathRecording());
      Files.write(path, report.getBytes());
    } catch (Exception ex) {
      log.error("There was an error saving the file: ".concat(ex.getMessage()));
    }
  }
}