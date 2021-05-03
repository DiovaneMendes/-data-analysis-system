package br.com.analysis.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SeparatorEnum {
  DATA("ç"),
  ITEM(","),
  INFO_ITEM("-");

  private final String value;
}
