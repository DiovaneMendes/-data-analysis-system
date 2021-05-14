package br.com.analysis.util;

import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

  private static final String DAT = ".dat";
  private static final String REGEX_ID = "\\D*(\\d*).*";
  private static final String REGEX_REMOVE_BRACKET = "\\*?\\]|\\*?\\[";

  public static String getIdentifier(String data) {
    return data.replaceFirst(REGEX_ID, "$1");
  }

  public static String removeBrackets(String data) {
    return data.replaceAll(REGEX_REMOVE_BRACKET, "");
  }

  public static Boolean isFileDat(Path path) {
    return path.getFileName().toString().endsWith(DAT);
  }
}
