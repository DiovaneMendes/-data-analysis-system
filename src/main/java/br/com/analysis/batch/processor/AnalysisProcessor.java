package br.com.analysis.batch.processor;

import br.com.analysis.factory.ModelFactory;
import br.com.analysis.model.ReportModel;
import br.com.analysis.producer.SaleProducer;
import br.com.analysis.repository.ClientRepository;
import br.com.analysis.repository.SellerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnalysisProcessor implements ItemProcessor<List<String>, ReportModel> {

  private final SaleProducer saleProducer;

  @Override
  public ReportModel process(List<String> data) {
    log.info("Starting data processing");

    data.forEach(ModelFactory::createModels);

    return ReportModel.builder()
        .amountClient(ClientRepository.getInstance().getAll().size())
        .amountSeller(SellerRepository.getInstance().getAll().size())
        .idSaleExpensive(saleProducer.monstExpensiveSale())
        .worstSeller(saleProducer.worstSeller())
        .build();
  }
}
