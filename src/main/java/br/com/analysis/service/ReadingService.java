package br.com.analysis.service;

import br.com.analysis.configuration.AnalysiConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadingService {

  private final AnalysiConfiguration configuration;

  public List<String> readData(Path path) {
    try{
      return Files.lines(path)
        .collect(Collectors.toList());
    } catch (Exception e) {
      log.error("There was an error reading the data from the file: ".concat(path.getFileName().toString()));
      return Collections.emptyList();
    }
  }

  public List<Path> fileSearch() {
    try{
      return Files.list(Paths.get(configuration.getPathReading()))
        .collect(Collectors.toList());
    } catch (Exception e) {
      log.error("There was an error reading the file: ".concat(e.getMessage()));
      return Collections.emptyList();
    }
  }
}