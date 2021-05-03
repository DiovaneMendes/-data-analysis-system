package br.com.analysis.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
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
