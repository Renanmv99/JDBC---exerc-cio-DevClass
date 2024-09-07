package br.com.syonet.service;

import java.util.List;

import br.com.syonet.model.Studant;
import br.com.syonet.model.StudantRepository;

public class StudantService {
  private StudantRepository repository;

  public StudantService(StudantRepository repository) {
    this.repository = repository;
  }

  public long save(Studant studant) {
    if (studant.getId() == 0) {
      Studant newStudant = this.repository.create(studant);
      return newStudant.getId();
    }
    throw new IllegalArgumentException("Id não pode ser atribuido");
  }

  public List<Studant> listAll() {
    return this.repository.listAll();
  }

  public boolean update(Studant studant) {
    if (studant.getId() != 0) {
        return this.repository.update(studant);
    }
    throw new IllegalArgumentException("Id não pode ser zero");
}

public boolean removeStudant(long id) {
    return this.repository.removeStudant(id);
}

public Studant findById(long id) {
    return this.repository.findById(id);
}

public List<Studant> findByName(String name) {
    return this.repository.findByName(name);
}

}
