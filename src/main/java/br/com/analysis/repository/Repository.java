package br.com.analysis.repository;

import java.util.Set;

public interface Repository<T> {

  boolean add(T t);

  void clear();

  Set<T> getAll();
}
