package br.com.analysis.facade;

import br.com.analysis.factory.ModelFactory;
import br.com.analysis.repository.ClientRepository;
import br.com.analysis.repository.SaleRepository;
import br.com.analysis.repository.SellerRepository;
import br.com.analysis.service.ReadingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataFacade {

  private final ReportFacade reportFacade;
  private final ReadingService readingService;

  @Scheduled(fixedDelay = 5000)
  public void loadInformation() {
    log.info("### Executing procedure ###");
    readingService.fileSearch();
    reportFacade.generateReport();
    clearRepositorys();
  }

  public void clearRepositorys() {
    ClientRepository.getInstance().clear();
    SellerRepository.getInstance().clear();
    SaleRepository.getInstance().clear();
  }
}
