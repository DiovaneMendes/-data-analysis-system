package br.com.analysis.stub;

import br.com.analysis.model.ReportModel;

public class ReportModelStub {

  public static ReportModel retornoProcess() {
    return ReportModel.builder()
      .amountClient(2)
      .amountSeller(2)
      .idSaleExpensive("10")
      .worstSeller("Paulo")
      .build();
  }

  public static ReportModel parametroWrite() {
    return ReportModel.builder()
      .amountClient(5)
      .amountSeller(25)
      .idSaleExpensive("13")
      .worstSeller("Teste")
      .build();
  }
}
