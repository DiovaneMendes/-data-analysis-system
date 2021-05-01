package br.com.analysis.stub;

import br.com.analysis.model.ClientModel;

import java.util.Set;

public class ClientModelStub {

  public static Set<ClientModel> list() {
    return Set.of(
      ClientModel.builder().name("teste_01").build(),
      ClientModel.builder().name("teste_02").build()
    );
  }

  public static ClientModel retornoCreate() {
    return ClientModel.builder()
      .id("2")
      .cnpj(2345675434544345L)
      .name("Jose da Silva")
      .businessArea("Rural")
      .build();
  }
}
