package br.com.analysis.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClientModel extends GenericModel {
  private Long cnpj;
  private String name;
  private String businessArea;

  @Builder
  public ClientModel(String id, Long cnpj, String name, String businessArea) {
    super(id);
    this.cnpj = cnpj;
    this.name = name;
    this.businessArea = businessArea;
  }
}
