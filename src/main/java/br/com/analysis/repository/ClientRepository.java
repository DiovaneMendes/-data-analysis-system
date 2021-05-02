package br.com.analysis.repository;

import br.com.analysis.model.ClientModel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ClientRepository implements Repository<ClientModel> {

  private final Set<ClientModel> clients;
  private static ClientRepository instance = null;

  public ClientRepository() {
    clients = new HashSet<>();
  }

  public static ClientRepository getInstance() {
    if (Objects.isNull(instance)) instance = new ClientRepository();
    return instance;
  }

  @Override
  public boolean add(ClientModel client) {
    return (clients.add(client));
  }

  @Override
  public void clear() {
    clients.clear();
  }

  @Override
  public Set<ClientModel> getAll() {
    return clients;
  }
}
