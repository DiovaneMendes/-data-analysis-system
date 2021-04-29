package br.com.analysis.service;

import br.com.analysis.configuration.AnalysiConfiguration;
import br.com.analysis.factory.ModelFactory;
import br.com.analysis.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadingService {

  private final AnalysiConfiguration configuration;

  public void fileSearch() {
    try{
      var path = Paths.get(configuration.getPathReading());

      Files.list(path)
        .filter(StringUtil::isFileDat)
        .map(readData())
        .flatMap(List::stream)
        .forEach(ModelFactory::createModels);

    } catch (Exception ex) {
      log.error("There was an error reading the file: {}", ex.getMessage());
    }
  }

  private Function<Path, List<String>> readData() {
    return (Path path) -> {
      try{
        return Files.readAllLines(path);
      } catch (Exception e) {
        log.error("There was an error reading the data from the file: {}", path.getFileName());
        return Collections.emptyList();
      }
    };
  }
}