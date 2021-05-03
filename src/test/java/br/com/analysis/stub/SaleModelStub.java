package br.com.analysis.stub;

import br.com.analysis.model.ItemModel;
import br.com.analysis.model.SaleModel;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class SaleModelStub {

  public static Set<SaleModel> list() {
    return Set.of(
        SaleModel.builder()
            .id("001")
            .sellerName("Seller Name 01")
            .saleCode("1")
            .itens(itensUm())
            .build(),
        SaleModel.builder()
            .id("002")
            .sellerName("Seller Name 02")
            .saleCode("2")
            .itens(itensDois())
            .build());
  }

  private static List<ItemModel> itensUm() {
    return List.of(
        ItemModel.builder().id("10").amount(3).price(BigDecimal.valueOf(150)).build(),
        ItemModel.builder().id("11").amount(1).price(BigDecimal.valueOf(87)).build());
  }

  private static List<ItemModel> itensDois() {
    return List.of(
        ItemModel.builder().id("20").amount(2).price(BigDecimal.valueOf(13)).build(),
        ItemModel.builder().id("22").amount(10).price(BigDecimal.valueOf(75)).build());
  }

  public static Set<SaleModel> listNoInformation() {
    return Set.of(
        SaleModel.builder().id("001").saleCode("1").itens(itensUm()).build(),
        SaleModel.builder().id("002").sellerName("Seller Name 02").itens(itensDois()).build());
  }

  public static SaleModel retornoCreate() {
    return SaleModel.builder()
        .id("3")
        .saleCode("10")
        .itens(listCreate())
        .sellerName("Pedro")
        .build();
  }

  private static List<ItemModel> listCreate() {
    return List.of(
        ItemModel.builder().id("1").amount(10).price(BigDecimal.valueOf(100.0)).build(),
        ItemModel.builder().id("2").amount(30).price(BigDecimal.valueOf(2.5)).build(),
        ItemModel.builder().id("3").amount(40).price(BigDecimal.valueOf(3.10)).build());
  }
}
