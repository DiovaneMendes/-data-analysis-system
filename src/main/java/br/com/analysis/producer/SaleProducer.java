package br.com.analysis.producer;

import br.com.analysis.enuns.ReportEnum;
import br.com.analysis.model.SaleModel;
import br.com.analysis.repository.SaleRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

import static br.com.analysis.enuns.ReportEnum.EXPENSIVE_SALE;
import static br.com.analysis.enuns.ReportEnum.WORST_SELLER;

@Component
public class SaleProducer {

  private final Set<SaleModel> sales;

  public SaleProducer() {
    this.sales = SaleRepository.getInstance().getAll();
  }

  @SneakyThrows
  public String monstExpensiveSale() {
    var map = informationSale(sales, EXPENSIVE_SALE);

    return sales.stream()
      .map(saleCode(map))
      .findFirst()
      .orElseThrow(() -> new SaleException("Error when looking for more expensive sale."));
  }

  private Map<String, BigDecimal> informationSale(Set<SaleModel> sales, ReportEnum reportEnum) {
    var map = new HashMap<String, BigDecimal>();
    sales.forEach(sale -> map.put(informationType(sale, reportEnum), totalSalePrice(sale)));
    return map;
  }

  private String informationType(SaleModel sale, ReportEnum reportEnum) {
    return (reportEnum.equals(EXPENSIVE_SALE)) ? sale.getSaleCode() : sale.getSellerName();
  }

  private BigDecimal totalSalePrice(SaleModel vendaModel) {
    return vendaModel.getItens()
      .stream()
      .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getAmount())))
      .reduce(BigDecimal::add)
      .orElse(BigDecimal.ZERO);
  }

  private Function<SaleModel, String> saleCode(Map<String, BigDecimal> map) {
    return (SaleModel sale) -> typeReport(EXPENSIVE_SALE)
      .andThen(getAttribute(map))
      .apply(map.values());
  }

  @SneakyThrows
  public String worstSeller() {
    var map = informationSale(sales, WORST_SELLER);

    return sales.stream()
      .map(sellerName(map))
      .findFirst()
      .orElseThrow(() -> new SaleException("Error fetching the worst seller."));
  }

  private Function<SaleModel, String> sellerName(Map<String, BigDecimal> map) {
    return (SaleModel sale) -> {
      map.put(sale.getSellerName(), totalSalePrice(sale));

      return typeReport(WORST_SELLER)
        .andThen(getAttribute(map))
        .apply(map.values());
    };
  }

  private Function<Collection<BigDecimal>, BigDecimal> typeReport(ReportEnum reportEnum) {
    return (Collection<BigDecimal> collection) -> (reportEnum == WORST_SELLER)
      ? Collections.min(collection)
      : Collections.max(collection);
  }

  private Function<BigDecimal, String> getAttribute(Map<String, BigDecimal> map) {
    return (BigDecimal bigDecimal) -> map.entrySet()
      .stream()
      .filter(m -> m.getValue().equals(bigDecimal))
      .findFirst()
      .map(Map.Entry::getKey)
      .orElse("No information");
  }
}