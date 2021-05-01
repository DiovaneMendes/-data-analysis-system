package br.com.analysis.util;

import br.com.analysis.enuns.SeparatorEnum;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtil {

  private static final String REGEX_ID = "\\D*(\\d*).*";
  private static final String REGEX_REMOVE_BRACKET = "\\*?\\]|\\*?\\[";
  private static final String DAT = ".dat";

  public static String getIdentifier(String data) {
    return data.replaceFirst(REGEX_ID, "$1");
  }

  public static List<String> separatorData(String data, SeparatorEnum separatorEnum) {
    return Stream.of(data.split(separatorEnum.getValue()))
      .collect(Collectors.toList());
  }

  public static String removeBrackets(String data) {
    return data.replaceAll(REGEX_REMOVE_BRACKET, "");
  }

  public static Boolean isFileDat(Path path) {
    return path.getFileName().toString().endsWith(DAT);
  }
}
