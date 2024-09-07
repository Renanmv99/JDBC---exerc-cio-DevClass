package br.com.syonet.model;

import java.util.List;

public interface StudantRepository {
  Studant create(Studant studant);
  List<Studant> listAll();
  boolean update (Studant studant);
  boolean removeStudant(long id);
  Studant findById(long id);
  List<Studant> findByName(String name);

}
