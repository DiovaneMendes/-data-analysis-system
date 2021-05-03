package br.com.analysis.stub;

import java.util.List;

public class StringStub {

  public static List<String> listaDados() {
    return List.of(
        "001ç1234567891234çPedroç50000",
        "001ç3245678865434çPauloç40000.99",
        "002ç2345675434544345çJose da SilvaçRural",
        "002ç2345675433444345çEduardo PereiraçRural",
        "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro",
        "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
  }

  public static String comecoUm() {
    return "001ç1234567891234çPedroç50000";
  }

  public static String comecoDois() {
    return "002ç2345675434544345çJose da SilvaçRural";
  }

  public static String comecoTres() {
    return "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
  }

  public static String comecoDiferente() {
    return "009ç2345675433444345çEduardo PereiraçRural";
  }

  public static List<String> conteudoRelatorio() {
    return List.of(
        "Quantidade de clientes: 5 ",
        "Quantidade de vendedores: 25 ",
        "ID venda mais cara: 13 ",
        "Pior vendedor: Teste");
  }
}
