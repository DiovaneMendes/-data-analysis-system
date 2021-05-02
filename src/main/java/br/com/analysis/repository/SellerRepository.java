package br.com.analysis.repository;

import br.com.analysis.model.SellerModel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SellerRepository implements Repository<SellerModel> {

  private final Set<SellerModel> sellers;
  private static SellerRepository instance = null;

  public SellerRepository() {
    sellers = new HashSet<>();
  }

  public static SellerRepository getInstance() {
    if (Objects.isNull(instance)) instance = new SellerRepository();
    return instance;
  }

  @Override
  public boolean add(SellerModel seller) {
    return (sellers.add(seller));
  }

  @Override
  public void clear() {
    sellers.clear();
  }

  @Override
  public Set<SellerModel> getAll() {
    return sellers;
  }
}