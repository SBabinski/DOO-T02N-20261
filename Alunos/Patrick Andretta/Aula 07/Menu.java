import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scan;
    private final History history;
    private final Change change;
    private final List<Store> stores;
    private final OrderProcessor orderProcessor;
    private double lastTotalValue;
    private int nextOrderId = 1;

    public Menu(Scanner scan, History history, Change change, List<Store> stores) {
        this.scan = scan;
        this.history = history;
        this.change = change;
        this.stores = stores;
        this.orderProcessor = new OrderProcessor();
    }

    public void callMenu() {
        int escolha;
        do {
            System.out.println("Bem-vindo a floricultura My Plant");
            System.out.println("Escolha uma opção\n");
            System.out.println("1- Calcular Preço total");
            System.out.println("2- Calcular troco");
            System.out.println("3- Histórico de vendas");
            System.out.println("4- Filtrar histórico por dia");
            System.out.println("5- Filtrar histórico por mês");
            System.out.println("6- Menu lojas");
            System.out.println("7- Criar pedido");
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
            } else if (escolha == 7) {
                createOrderMenu();
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
            System.out.print("Digite a data de vencimento da reserva (dd/MM/aaaa): ");
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

    private void createOrderMenu() {
        if (stores.isEmpty()) {
            System.out.println("Nenhuma loja cadastrada para criar pedido.");
            System.out.println("Enter para voltar ao menu");
            scan.nextLine();
            return;
        }

        Store store = selectStore();
        if (store == null) {
            return;
        }

        if (store.getClients().isEmpty() || store.getSellers().isEmpty()) {
            System.out.println("A loja selecionada precisa ter ao menos um cliente e um vendedor.");
            System.out.println("Enter para voltar ao menu");
            scan.nextLine();
            return;
        }

        Client client = selectClient(store);
        Seller seller = selectSeller(store);
        List<Item> items = readItems();
        LocalDate creationDate = LocalDate.now();
        LocalDate reservationDueDate = readReservationDueDate();

        try {
            Order order = orderProcessor.process(
                nextOrderId++,
                creationDate,
                reservationDueDate,
                client,
                seller,
                store,
                items
            );

            lastTotalValue = order.calculateTotalValue();
            System.out.println("\nPedido criado com sucesso.");
            System.out.println(order.createOrderDescription());
        } catch (IllegalStateException e) {
            System.out.println("Não foi possível criar o pedido: " + e.getMessage());
        }

        System.out.println("Enter para voltar ao menu");
        scan.nextLine();
    }

    private Store selectStore() {
        while (true) {
            System.out.println("\nSelecione a loja do pedido:");
            for (int i = 0; i < stores.size(); i++) {
                System.out.println((i + 1) + "- " + stores.get(i).introduce());
            }
            System.out.println("0- Cancelar");

            if (!scan.hasNextInt()) {
                scan.nextLine();
                System.out.println("Opção inválida.");
                continue;
            }

            int option = scan.nextInt();
            scan.nextLine();

            if (option == 0) {
                return null;
            }

            if (option >= 1 && option <= stores.size()) {
                return stores.get(option - 1);
            }

            System.out.println("Loja inválida.");
        }
    }

    private Client selectClient(Store store) {
        while (true) {
            System.out.println("\nSelecione o cliente:");
            List<Client> clients = store.getClients();
            for (int i = 0; i < clients.size(); i++) {
                System.out.println((i + 1) + "- " + clients.get(i).introduce());
            }

            if (!scan.hasNextInt()) {
                scan.nextLine();
                System.out.println("Opção inválida.");
                continue;
            }

            int option = scan.nextInt();
            scan.nextLine();

            if (option >= 1 && option <= clients.size()) {
                return clients.get(option - 1);
            }

            System.out.println("Cliente inválido.");
        }
    }

    private Seller selectSeller(Store store) {
        while (true) {
            System.out.println("\nSelecione o vendedor:");
            List<Seller> sellers = store.getSellers();
            for (int i = 0; i < sellers.size(); i++) {
                System.out.println((i + 1) + "- " + sellers.get(i).introduce());
            }

            if (!scan.hasNextInt()) {
                scan.nextLine();
                System.out.println("Opção inválida.");
                continue;
            }

            int option = scan.nextInt();
            scan.nextLine();

            if (option >= 1 && option <= sellers.size()) {
                return sellers.get(option - 1);
            }

            System.out.println("Vendedor inválido.");
        }
    }

    private List<Item> readItems() {
        List<Item> items = new ArrayList<>();

        while (true) {
            System.out.print("Digite o nome do item: ");
            String name = scan.nextLine().trim();

            System.out.print("Digite o tipo do item: ");
            String type = scan.nextLine().trim();

            Double value = readItemValue();
            items.add(new Item(items.size() + 1, name, type, value));

            System.out.print("Deseja adicionar outro item? (s/n): ");
            String answer = scan.nextLine().trim();
            if (!answer.equalsIgnoreCase("s")) {
                break;
            }
        }

        return items;
    }

    private Double readItemValue() {
        while (true) {
            System.out.print("Digite o valor do item: ");
            if (!scan.hasNextDouble()) {
                scan.nextLine();
                System.out.println("Valor inválido.");
                continue;
            }

            double value = scan.nextDouble();
            scan.nextLine();

            if (value > 0) {
                return value;
            }

            System.out.println("O valor deve ser positivo.");
        }
    }

    private LocalDate readReservationDueDate() {
        while (true) {
            LocalDate reservationDueDate = readDate();
            if (!reservationDueDate.isBefore(LocalDate.now())) {
                return reservationDueDate;
            }

            System.out.println("A data de vencimento da reserva não pode ser anterior à data atual.");
        }
    }
}
