package br.com.analysis.repository;


import br.com.analysis.model.SaleModel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SaleRepository implements Repository<SaleModel>{

  private final Set<SaleModel> sales;
  private static SaleRepository instance = null;

  public SaleRepository() {
    sales = new HashSet<>();
  }

  public static SaleRepository getInstance() {
    if (Objects.isNull(instance)) instance = new SaleRepository();
    return instance;
  }

  @Override
  public boolean add(SaleModel sale) {
    return (sales.add(sale));
  }

  @Override
  public void clear() {
    sales.clear();
  }

  @Override
  public Set<SaleModel> getAll() {
    return sales;
  }
}