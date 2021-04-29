package br.com.analysis.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ItemModel extends GenericModel {
  private Integer amount;
  private BigDecimal price;

  @Builder
  public ItemModel(String id, Integer amount, BigDecimal price) {
    super(id);
    this.amount = amount;
    this.price = price;
  }
}