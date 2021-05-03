package br.com.analysis.stub;

import br.com.analysis.model.SellerModel;
import java.math.BigDecimal;
import java.util.Set;

public class SellerModelStub {

  public static Set<SellerModel> list() {
    return Set.of(
        SellerModel.builder().name("teste_01").build(),
        SellerModel.builder().name("teste_02").build());
  }

  public static SellerModel retornoCreate() {
    return SellerModel.builder()
        .id("001")
        .taxId(1234567891234L)
        .name("Pedro")
        .salary(BigDecimal.valueOf(50000.0))
        .build();
  }
}
