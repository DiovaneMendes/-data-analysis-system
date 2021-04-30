package br.com.analysis.batch.writer;

import br.com.analysis.model.ReportModel;
import br.com.analysis.repository.ClientRepository;
import br.com.analysis.repository.SaleRepository;
import br.com.analysis.repository.SellerRepository;
import br.com.analysis.service.RecordingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnalysisWriter implements ItemWriter<ReportModel> {

  private static final Integer ZERO = 0;
  private final RecordingService recordingService;

  @Override
  public void write(List<? extends ReportModel> reports) {
    log.info("Recording report");

    var report = reports.get(ZERO);
    var dataReport = dataReport(report);

    recordingService.recordingReport(dataReport);
    clearRepositorys();
  }

  private String dataReport(ReportModel report) {
    return String.format(
      "Quantidade de clientes: %s \n" +
      "Quantidade de vendedores: %s \n" +
      "ID venda mais cara: %s \n" +
      "Pior vendedor: %s",
      report.getAmountClient(),
      report.getAmountSeller(),
      report.getIdSaleExpensive(),
      report.getWorstSeller());
  }

  public void clearRepositorys() {
    ClientRepository.getInstance().clear();
    SellerRepository.getInstance().clear();
    SaleRepository.getInstance().clear();
  }
}
