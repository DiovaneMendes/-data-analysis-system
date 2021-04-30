package br.com.analysis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportModel {
  private Integer amountClient;
  private Integer amountSeller;
  private String idSaleExpensive;
  private String worstSeller;
}
