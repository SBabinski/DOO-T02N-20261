import java.util.Scanner;

public class Client {
  private String name;
  private String cpf;
  private String documentNumber;

  public Client(String name, String cpf, String documentNumber) {
    this.name = name;
    this.cpf = cpf;
    this.documentNumber = documentNumber;
  }

  public static Client createClient(Scanner scan) {
    System.out.println("Informe o nome do cliente:");
    String name = scan.nextLine();

    System.out.println("Informe o CPF:");
    String cpf = scan.nextLine();

    System.out.println("Informe o numero do documento:");
    String documentNumber = scan.nextLine();

    return new Client(name, cpf, documentNumber);
  }

  public String getName() {
    return name;
  }

  public String getCpf() {
    return cpf;
  }

  public String getDocumentNumber() {
    return documentNumber;
  }

  public String describe() {
    return "Nome: " + name + " | CPF: " + cpf + " | Documento: " + documentNumber;
  }
}
