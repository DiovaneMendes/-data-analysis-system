package br.com.analysis.facade;

import br.com.analysis.enuns.ReportEnum;
import br.com.analysis.model.SaleModel;
import br.com.analysis.repository.SaleRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Component
public class SaleFacade {

  private final Set<SaleModel> sales;
  private static final String NO_INFORMATION = "No information";

  public SaleFacade() {
    this.sales = SaleRepository.getInstance().getAll();;
  }

  @SneakyThrows
  public String monstExpensiveSale() {
    var map = informationSale(sales, ReportEnum.EXPENSIVE_SALE);

    return sales.parallelStream()
      .map(saleCode(map))
      .findFirst()
      .orElseThrow(() -> new Exception("Error when looking for more expensive sale."));
  }

  private HashMap<String, BigDecimal> informationSale(Set<SaleModel> sales, ReportEnum reportEnum) {
    var map = new HashMap<String, BigDecimal>();
    sales.forEach(sale -> map.put(informationType(sale, reportEnum), totalSalePrice(sale)));
    return map;
  }

  private String informationType(SaleModel sale, ReportEnum reportEnum) {
    if (reportEnum.equals(ReportEnum.EXPENSIVE_SALE)) return sale.getSaleCode();
    return sale.getSellerName();
  }

  private BigDecimal totalSalePrice(SaleModel vendaModel) {
    return vendaModel.getItens().parallelStream()
      .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getAmount())))
      .reduce(BigDecimal::add)
      .orElse(BigDecimal.ZERO);
  }

  private Function<SaleModel, String> saleCode(Map<String, BigDecimal> map) {
    return (SaleModel sale) -> map.entrySet()
        .stream()
        .filter(m -> m.getValue().equals(Collections.max(map.values())))
        .findFirst()
        .map(Map.Entry::getKey)
        .orElse(NO_INFORMATION);
  }

  @SneakyThrows
  public String worstSeller() {
    var map = informationSale(sales, ReportEnum.WORST_SELLER);

    return sales.parallelStream()
      .map(sellerName(map))
      .findFirst()
      .orElseThrow(() -> new Exception("Error fetching the worst seller."));
  }

  private Function<SaleModel, String> sellerName(HashMap<String, BigDecimal> map) {
    return (SaleModel sale) -> {
      map.put(sale.getSellerName(), totalSalePrice(sale));

      return map.entrySet()
        .parallelStream()
        .filter(m -> m.getValue().equals(Collections.min(map.values())))
        .findFirst()
        .map(Map.Entry::getKey)
        .orElse(NO_INFORMATION);
    };
  }
}
