package br.com.analysis.factory;

import br.com.analysis.enuns.SeparatorEnum;
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
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelFactory {

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
    var information = listSeparatorEnumData(data);
    return SellerModel.builder()
        .id(information.get(0))
        .taxId(Long.parseLong(information.get(1)))
        .name(information.get(2))
        .salary(BigDecimal.valueOf(Double.parseDouble(information.get(3))))
        .build();
  }

  private static ClientModel client(String data) {
    var information = listSeparatorEnumData(data);
    return ClientModel.builder()
        .id(information.get(0))
        .cnpj(Long.parseLong(information.get(1)))
        .name(information.get(2))
        .businessArea(information.get(3))
        .build();
  }

  private static SaleModel sale(String data) {
    var information = listSeparatorEnumData(data);
    return SaleModel.builder()
        .id(information.get(0))
        .saleCode(information.get(1))
        .itens(items(information.get(2)))
        .sellerName(information.get(3))
        .build();
  }

  private static List<ItemModel> items(String data) {
    data = StringUtil.removeBrackets(data);
    var information = StringUtil.separatorData(data, SeparatorEnum.ITEM);

    return information.stream()
        .map(item -> item.split(SeparatorEnum.INFO_ITEM.getValue()))
        .map(mapItem())
        .collect(Collectors.toList());
  }

  private static Function<String[], ItemModel> mapItem() {
    return (String[] data) ->
        ItemModel.builder()
            .id(data[0])
            .amount(Integer.valueOf(data[1]))
            .price(BigDecimal.valueOf(Double.parseDouble(data[2])))
            .build();
  }

  private static List<String> listSeparatorEnumData(String data) {
    return StringUtil.separatorData(data, SeparatorEnum.DATA);
  }
}
