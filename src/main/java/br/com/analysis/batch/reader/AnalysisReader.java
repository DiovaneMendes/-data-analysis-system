package br.com.analysis.batch.reader;

import br.com.analysis.service.ReadingService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnalysisReader implements ItemReader<List<String>> {

  private Integer listSize;
  private final ReadingService readingService;

  public AnalysisReader(ReadingService readingService) {
    listSize = 0;
    this.readingService = readingService;
  }

  @Override
  public List<String> read() {
    var pathList = readingService.fileSearch();
    var sizePathList = pathList.size();

    if (sizePathList == listSize) return null;

    log.info("Reading files");

    listSize = sizePathList;

    return pathList.stream()
        .map(readingService.readData())
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }
}
