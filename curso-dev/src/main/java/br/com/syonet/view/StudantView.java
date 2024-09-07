package br.com.syonet.view;

import java.util.List;
import java.util.Scanner;

import br.com.syonet.model.Studant;
import br.com.syonet.service.StudantService;

public class StudantView {

  private int selectedOption;
  private boolean exit;
  private Scanner scanner;

  private StudantService service;

  public StudantView(StudantService service, Scanner scanner) {
    this.service = service;
    this.scanner = scanner;
  }

  public void init() {
    System.out.println("Ola seja vem vindo ao nosso cadastro de estudantes.");
  }

  public void showOptions() {
    System.out.println("Por favor selecione uma operaçoes abaixo:");
    System.out.println();
    System.out.println("\t(1) - Criar novo estudantes");
    System.out.println("\t(2) - Listar estudantes");
    System.out.println("\t(3) - Atualizar estudante");
    System.out.println("\t(4) - Remover estudante");
    System.out.println("\t(5) - Buscar estudante por ID");
    System.out.println("\t(6) - Filtrar estudantes por nome");
    System.out.println("\t(0) - Sair");
  }

  public Integer getSelectedOption() {
    return selectedOption;
  }

  public boolean isExit() {
    return this.exit;
  }

  public void readSelectedOption() {
    String nextLine = this.scanner.nextLine();
    int answer = Integer.parseInt(nextLine);
    this.exit = answer == 0;
    this.selectedOption = answer;
  }

  public void executeSelectedOperation() {
    switch (this.selectedOption) {
      case 1:
        this.initCreationProcess();
        break;
      case 2:
        this.initListProcess();
        break;
      case 3:
        this.initUpdateProcess();
        break;
      case 4:
        this.initRemoveProcess();
        break;
      case 5:
        this.initFindByIdProcess();
        break;
      case 6:
        this.initFindByNameProcess();
        break;
      default:
        break;
    }
  }

  private void initListProcess() {
    List<Studant> studants = this.service.listAll();
    if (studants != null && !studants.isEmpty()) {
      System.out.println();
      System.out.println("\t\tid\t\t|\t\tnome\t\t|\t\tidade\t\t|\t\temail");
      for (int i = 0; i < studants.size(); i++) {
        Studant studant = studants.get(i);
        System.out.println("\t\t%d\t\t\t\t%s\t\t\t\t%d\t\t\t\t%s".formatted(
          studant.getId(),
          studant.getName(),
          studant.getAge(),
          studant.getEmail()));
      }
      System.out.println();
    } else {
      System.out.println("Não há estudantes cadastrados!");
    }
  }

  private void initCreationProcess() {
    System.out.println("Ok, qual é o nome do estudante?");
    String name = this.scanner.nextLine();
    System.out.println("E o email do rapaz ou da moça?");
    String email = this.scanner.nextLine();
    System.out.println("Muito bom! agora qual a idade dela ou dele?");
    Integer idade = Integer.parseInt(this.scanner.nextLine());
    System.out.println("Obrigado temos todas as info, criando novo estudante!");
    Studant studant = new Studant(name, idade, email);
    long id = this.service.save(studant);
    System.out.println("O id do novo estudante é " + id);
  }

  private void initUpdateProcess() {
    System.out.println("Digite o ID do estudante que deseja atualizar:");
    long id = Long.parseLong(scanner.nextLine());
    Studant studant = service.findById(id);
    if (studant != null) {
        System.out.println("Nome atual: " + studant.getName() + ". Novo nome?");
        String name = scanner.nextLine();
        System.out.println("Email atual: " + studant.getEmail() + ". Novo email?");
        String email = scanner.nextLine();
        System.out.println("Idade atual: " + studant.getAge() + ". Nova idade?");
        int age = Integer.parseInt(scanner.nextLine());
        service.update(new Studant(id, name, age, email));
        System.out.println("Estudante atualizado com sucesso.");
    } else {
        System.out.println("Estudante não encontrado.");
    }
}

private void initFindByIdProcess() {
  System.out.println("Digite o ID do estudante que deseja buscar:");
  long id = Long.parseLong(scanner.nextLine());
  Studant studant = service.findById(id);
  if (studant != null) {
      System.out.println("Estudante encontrado:");
      System.out.println("ID: " + studant.getId());
      System.out.println("Nome: " + studant.getName());
      System.out.println("Email: " + studant.getEmail());
      System.out.println("Idade: " + studant.getAge());
  } else {
      System.out.println("Estudante não encontrado.");
  }
}

private void initFindByNameProcess() {
  System.out.println("Digite o nome ou parte do nome do estudante que deseja buscar:");
  String name = scanner.nextLine();
  List<Studant> students = service.findByName(name);
  if (!students.isEmpty()) {
      System.out.println("Estudantes encontrados:");
      for (Studant studant : students) {
          System.out.println("ID: " + studant.getId());
          System.out.println("Nome: " + studant.getName());
          System.out.println("Email: " + studant.getEmail());
          System.out.println("Idade: " + studant.getAge());
          System.out.println("------------");
      }
  } else {
      System.out.println("Nenhum estudante encontrado com esse nome.");
  }
}

private void initRemoveProcess() {
  System.out.println("Digite o ID do estudante que deseja remover:");
  long id = Long.parseLong(scanner.nextLine());
  boolean removed = service.removeStudant(id);
  if (removed) {
      System.out.println("Estudante removido com sucesso.");
  } else {
      System.out.println("Estudante não encontrado.");
  }
}

}
