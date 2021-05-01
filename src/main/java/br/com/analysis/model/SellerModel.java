package br.com.analysis.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SellerModel extends GenericModel {
  private Long taxId;
  private String name;
  private BigDecimal salary;

  @Builder
  public SellerModel(String id, Long taxId, String name, BigDecimal salary) {
    super(id);
    this.taxId = taxId;
    this.name = name;
    this.salary = salary;
  }
}
