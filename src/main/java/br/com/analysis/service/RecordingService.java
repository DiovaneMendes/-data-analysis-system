package br.com.analysis.service;

import br.com.analysis.configuration.AnalysiConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.PrintWriter;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordingService {

  private final AnalysiConfiguration configuration;

  public void recordingReport(String report) {
    try{
      var archive = new FileWriter(configuration.getPathRecording());
      var writeFile = new PrintWriter(archive);
      writeFile.printf(report);
      archive.close();
    } catch (Exception ex) {
      log.error("There was an error saving the file: ".concat(ex.getMessage()));
    }
  }
}