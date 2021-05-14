package br.com.analysis.factory;

import br.com.analysis.exception.FactoryException;
import br.com.analysis.model.ClientModel;
import br.com.analysis.model.ItemModel;
import br.com.analysis.model.SaleModel;
import br.com.analysis.model.SellerModel;
import br.com.analysis.repository.ClientRepository;
import br.com.analysis.repository.SaleRepository;
import br.com.analysis.repository.SellerRepository;
import br.com.analysis.util.StringUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelFactory {

  private static final String ITEMS_REGEX = "\\[(.*)\\]";
  private static final String ITEM_REGEX = "([0-9]+)-([0-9]+)-([0-9]\\/*.?[0-9]+)";
  private static final String SALE_REGEX = "(003)ç([0-9]{2})ç(\\[.*\\]+)ç([\\s\\S]+)";
  private static final String CLIENT_REGEX = "(002)ç([0-9]{16})ç([\\s\\S]+)ç([\\s\\S]+)";
  private static final String SELLER_REGEX = "(001)ç([0-9]{13})ç([\\s\\S]+)ç([0-9]*.?[0-9]+)";

  @SneakyThrows
  public static void createModels(String data) {
    switch (StringUtil.getIdentifier(data)) {
      case "001":
        SellerRepository.getInstance().add(seller(data));
        break;
      case "002":
        ClientRepository.getInstance().add(client(data));
        break;
      case "003":
        SaleRepository.getInstance().add(sale(data));
        break;
      default:
        throw new FactoryException(data);
    }
  }

  private static SellerModel seller(String data) {
    var matcher = getMatcher(data, SELLER_REGEX);
    return SellerModel.builder()
        .id(matcher.group(1))
        .taxId(Long.parseLong(matcher.group(2)))
        .name(matcher.group(3))
        .salary(BigDecimal.valueOf(Double.parseDouble(matcher.group(4))))
        .build();
  }

  private static ClientModel client(String data) {
    var matcher = getMatcher(data, CLIENT_REGEX);
    return ClientModel.builder()
        .id(matcher.group(1))
        .name(matcher.group(3))
        .cnpj(Long.parseLong(matcher.group(2)))
        .businessArea(matcher.group(4))
        .build();
  }

  private static SaleModel sale(String data) {
    var matcher = getMatcher(data, SALE_REGEX);
    return SaleModel.builder()
        .id(matcher.group(1))
        .saleCode(matcher.group(2))
        .itens(items(matcher.group(3)))
        .sellerName(matcher.group(4))
        .build();
  }

  private static List<ItemModel> items(String data) {
    var matcher = getMatcher(data, ITEMS_REGEX);
    return Stream.of(matcher.group(1).split(",")).map(mapItem()).collect(Collectors.toList());
  }

  private static Function<String, ItemModel> mapItem() {
    return (String data) -> {
      var matcher = getMatcher(data, ITEM_REGEX);
      return ItemModel.builder()
          .id(matcher.group(1))
          .amount(Integer.valueOf(matcher.group(2)))
          .price(BigDecimal.valueOf(Double.parseDouble(matcher.group(3))))
          .build();
    };
  }

  private static Matcher getMatcher(String line, String regex) {
    var matcher = Pattern.compile(regex).matcher(line);
    matcher.find();
    return matcher;
  }
}
