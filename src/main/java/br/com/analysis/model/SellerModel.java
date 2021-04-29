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
  private Long cpf;
  private String name;
  private BigDecimal salary;

  @Builder
  public SellerModel(String id, Long cpf, String name, BigDecimal salary) {
    super(id);
    this.cpf = cpf;
    this.name = name;
    this.salary = salary;
  }
}
