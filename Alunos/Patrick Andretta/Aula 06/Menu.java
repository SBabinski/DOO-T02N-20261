import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scan;
    private final History history;
    private final Change change;
    private final List<Store> stores;
    private double lastTotalValue;

    public Menu(Scanner scan, History history, Change change, List<Store> stores) {
        this.scan = scan;
        this.history = history;
        this.change = change;
        this.stores = stores;
    }

    public void callMenu() {
        int escolha;
        do {
            System.out.println("Bem-vindo a floricultura da Dona Gabrielinha");
            System.out.println("Escolha uma opção\n");
            System.out.println("1- Calcular Preço total");
            System.out.println("2- Calcular troco");
            System.out.println("3- Histórico de vendas");
            System.out.println("4- Filtrar histórico por dia");
            System.out.println("5- Filtrar histórico por mês");
            System.out.println("6- Menu lojas");
            System.out.println("0- Sair");
            escolha = scan.nextInt();
            scan.nextLine();

            if (escolha == 1) {
                Purchase purchase = new Purchase(scan);
                double total = purchase.calculateTotalPrice();
                if (total == 0) {
                } else {
                    history.add(purchase);
                    lastTotalValue = purchase.getTotalValue();
                }
            } else if (escolha == 2) {
                if (lastTotalValue == 0) {
                    System.out.println("Seu carrinho está vazio, faça uma compra primeiro!\n");
                    System.out.println("Enter para voltar ao menu");
                    scan.nextLine();
                } else {
                    change.change(lastTotalValue);
                    System.out.println("Enter para voltar ao menu");
                    scan.nextLine();
                }
            } else if (escolha == 3) {
                history.printHistory();
                System.out.println("Enter para voltar ao menu");
                scan.nextLine();
            } else if (escolha == 4) {
                LocalDate date = readDate();
                history.printByDate(date);
                System.out.println("Enter para voltar ao menu");
                scan.nextLine();
            } else if (escolha == 5) {
                YearMonth month = readMonth();
                history.printByMonth(month);
                System.out.println("Enter para voltar ao menu");
                scan.nextLine();
            } else if (escolha == 6) {
                storeMenu();
            }
        } while (escolha != 0);

        System.out.println("Obrigado por utilizar nosso sistema, volte sempre!!");
    }

    private void storeMenu() {
        int escolha;
        do {
            System.out.println("\nMenu Lojas");
            if (stores.isEmpty()) {
                System.out.println("Nenhuma loja cadastrada.");
                System.out.println("Enter para voltar ao menu principal");
                scan.nextLine();
                return;
            }

            for (int i = 0; i < stores.size(); i++) {
                System.out.println((i + 1) + "- " + stores.get(i).introduce() + "\n");
            }
            System.out.println("0- Voltar ao menu principal");
            System.out.print("Escolha uma loja: ");

            if (!scan.hasNextInt()) {
                scan.nextLine();
                System.out.println("Opção inválida.");
                continue;
            }
            escolha = scan.nextInt();
            scan.nextLine();

            if (escolha == 0) {
                return;
            }

            if (escolha < 1 || escolha > stores.size()) {
                System.out.println("Loja inválida.");
                continue;
            }

            Store store = stores.get(escolha - 1);
            printStoreDetails(store);
            System.out.println("Enter para voltar ao menu de lojas");
            scan.nextLine();
        } while (true);
    }

    private void printStoreDetails(Store store) {
        System.out.println("\nDetalhes da Loja");
        System.out.println(store.introduce());

        System.out.println("\nVendedores - Total: " + store.getSellersQuantity());
        if (store.getSellers().isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado.");
        } else {
            for (Seller seller : store.getSellers()) {
                System.out.println(seller.introduce());
            }
        }

        System.out.println("\nClientes - Total: " + store.getClientsQuantity());
        if (store.getClients().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Client client : store.getClients()) {
                System.out.println(client.introduce());
            }
        }
    }

    private LocalDate readDate() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Digite a data (dd/MM/aaaa): ");
            String input = scan.nextLine().trim();
            try {
                return LocalDate.parse(input, fmt);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Exemplo válido: 18/03/2026");
            }
        }
    }

    private YearMonth readMonth() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/yyyy");
        while (true) {
            System.out.print("Digite o mês e ano (MM/aaaa): ");
            String input = scan.nextLine().trim();
            try {
                return YearMonth.parse(input, fmt);
            } catch (DateTimeParseException e) {
                System.out.println("Mês inválido. Exemplo válido: 03/2026");
            }
        }
    }
}
