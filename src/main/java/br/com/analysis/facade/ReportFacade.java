package br.com.analysis.facade;

import br.com.analysis.repository.ClientRepository;
import br.com.analysis.repository.SellerRepository;
import br.com.analysis.service.RecordingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportFacade {

  private final SaleFacade saleFacade;
  private final RecordingService recordingService;

  public void generateReport() {
    var amountClient = ClientRepository.getInstance().getAll().size();
    var amountSellers = SellerRepository.getInstance().getAll().size();
    var idSaleExpensive = saleFacade.monstExpensiveSale();
    var worstSeller = saleFacade.worstSeller();

    var text = text(amountClient, amountSellers, idSaleExpensive, worstSeller);

    recordingService.recordingReport(text);
  }

  private String text(Integer amountClient, Integer amountSellers, String idSaleExpensive, String worstSeller) {
    return String.format(
      "Quantidade de clientes: %s \n"
    + "Quantidade de vendedores: %s \n"
    + "ID venda mais cara: %s \n"
    + "Pior vendedor: %s",
      amountClient, amountSellers, idSaleExpensive, worstSeller);
  }
}