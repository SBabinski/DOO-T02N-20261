import objetos.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Principal {
    static Scanner scan = new Scanner(System.in);    
    static ArrayList<Leitor> leitores = new ArrayList<>();
    static ArrayList<Item> itens = new ArrayList<>();
    static ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        int escolha;
        do {
            System.out.println("\n--- BIBLIOTECA ACADÊMICA ---");
            System.out.println("1. Cadastrar Leitor");
            System.out.println("2. Cadastrar Item");
            System.out.println("3. Novo Empréstimo");
            System.out.println("4. Devolução");
            System.out.println("5. Listar Empréstimo");
            System.out.println("6. Listar Pendentes");
            System.out.println("7. Demonstração");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            escolha = scan.nextInt();
            scan.nextLine();

            validarEscolha(escolha);
        } while (escolha != 0);
    }

    private static void validarEscolha(int escolha) {
       switch (escolha) {
            case 1:
                cadastrarLeitor();
                break;
            case 2:
                cadastrarItem();
                break;
            case 3:
                cadastrarEmprestimo();
                break;
            case 4:
                realizarDevolucao(); 
                break;
            case 5:
                listarEmprestimo(); 
                break;
            case 6:
                listarPendentes();
                break;
            case 7:
                demonstracao(); 
                break;
            case 0:
                System.out.println("Obrigado por utilizar o sistema.");
                break;
            default:
                System.out.println("Selecione uma opção válida");
                break;
        }
    }

    private static void listarEmprestimo() {
        for(int i=0;i <emprestimos.size();i++){
          
            System.out.println((i+1)+"|  "+ emprestimos.get(i).toString());
            
        }
    }

    private static void demonstracao() {

        Leitor l1 = new Leitor("Ana Silva", "111.111.111-11", "ana@email.com");
        Leitor l2 = new Leitor("Bruno Souza", "222.222.222-22", "bruno@email.com");
        leitores.add(l1);
        leitores.add(l2);

        
        Livro liv = new Livro("Java Efetivo", LocalDate.of(2023, 5, 8), "Joshua Bloch");
        Revista rev = new Revista("Mundo Java", LocalDate.of(2024, 1, 1), 95);
        itens.add(liv);
        itens.add(rev);

        
        Emprestimo emp1 = new Emprestimo(liv, l1, LocalDate.now(), 7); // Ficará pendente
        Emprestimo emp2 = new Emprestimo(rev, l2, LocalDate.now(), 15); // Será devolvido
        
        
        emp2.registrarDevolucao();

        emprestimos.add(emp1);
        emprestimos.add(emp2);

        System.out.println("\n--- DEMONSTRAÇÃO INICIADA ---");
        System.out.println("\n FOORAM DEFINIDOS VALORES PARA OS LEITORES - ITENS E EMPRESTIMOS ");

        listarPendentes();

    }

    private static void listarPendentes() {
        for(int i=0;i <emprestimos.size();i++){
            if (emprestimos.get(i).isSituacao()==false) {
                System.out.println((i+1)+"|  "+ emprestimos.get(i).toString());
            }
        }
    }

    private static void realizarDevolucao() {
        System.out.println("--- Selecione o Emprestimo ---");
        for(int i=0;i <emprestimos.size();i++){
            System.out.println((i+1)+"|  "+ emprestimos.get(i).toString());
        }
        int iemp = scan.nextInt() -1;
        scan.nextLine();

        if (iemp >= 0 && iemp < emprestimos.size()) {
        emprestimos.get(iemp).registrarDevolucao();
        System.out.println("Devolução concluída!");
    }
    }

    private static void cadastrarEmprestimo() {
        if (emprestimos.size()>=10) {
            System.out.println("Erro: Limite de 10 empréstimos atingido!");
            return;
        }

        System.out.println("--- Selecione o Leitor ---");
        for(int i=0;i <leitores.size();i++){
            System.out.println((i+1)+"|  "+ leitores.get(i).getNome());
        }
        int iLeitor = scan.nextInt()-1;
        scan.nextLine();

        System.out.println("--- Selecione o Item ---");
        for(int i=0;i <itens.size();i++){
            System.out.println((i+1)+"|  "+ itens.get(i).getTitulo());
        }
        int iItem = scan.nextInt()-1;
        scan.nextLine();

        System.out.print("Dias de empréstimo: ");
        int dias = scan.nextInt();
        scan.nextLine();
        emprestimos.add(new Emprestimo(itens.get(iItem), leitores.get(iLeitor), LocalDate.now(), dias));
        System.out.println("Empréstimo registrado com sucesso!");
    }

    private static void cadastrarItem() {
        System.out.print("1-Livro / 2-Revista: ");
        int tipo = scan.nextInt();
        scan.nextLine();

        System.out.print("Título: "); 
        String titulo = scan.nextLine();

        System.out.println("Ano de publicação (dd/MM/yyyy):");
        String input = scan.nextLine();

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(input, formato);
        if (tipo == 1) {
            System.out.print("Autor: ");
            String autor = scan.nextLine();
            itens.add(new Livro(titulo, data, autor));
            System.out.println("Livro cadastrado com sucesso!");
        } else if (tipo == 2) {
            System.out.print("Número da Edição: ");
            int edicao = scan.nextInt();
            scan.nextLine(); // Limpa o buffer após o número
            itens.add(new Revista(titulo, data, edicao));
            System.out.println("Revista cadastrada com sucesso!");
        } else {
            System.out.println("Tipo inválido. Operação cancelada.");
        }
    }

    private static void cadastrarLeitor() {
        System.out.println("Nome:");
        String nome = scan.nextLine();
        System.out.println("CPF:");
        String cpf = scan.nextLine();
        System.out.println("E-mail:");
        String email = scan.nextLine();
        leitores.add(new Leitor(nome, cpf, email));
        System.out.println("Leitor cadastrado!");
    }
}
