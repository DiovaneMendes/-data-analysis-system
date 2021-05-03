package br.com.analysis.service;

import br.com.analysis.configuration.AnalysiConfiguration;
import br.com.analysis.util.StringUtil;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadingService {

  private final AnalysiConfiguration configuration;

  public List<Path> fileSearch() {
    try {
      var path = Paths.get(configuration.getPathReading());

      return Files.list(path).filter(StringUtil::isFileDat).collect(Collectors.toList());

    } catch (Exception ex) {
      log.error("There was an error reading the file: {}", ex.getMessage());
      return Collections.emptyList();
    }
  }

  public Function<Path, List<String>> readData() {
    return (Path path) -> {
      try {
        return Files.readAllLines(path);
      } catch (Exception e) {
        log.error("There was an error reading the data from the file: {}", path.getFileName());
        return Collections.emptyList();
      }
    };
  }
}
