import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;


public class Date {
    static HashMap<LocalDate, Double> valoresDiaEMes = new HashMap<>();
    
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    public static LocalDate stringToDate(String dataString){
        try {
            return LocalDate.parse(dataString, dateFormatter);
        } catch (Exception e) {
            System.out.println("Digite uma data válida: (dd/mm/aaaa");
            String novaData = Main.scan.nextLine().trim();
            return stringToDate(novaData);
        }
    }

    public static void EscolherDia(double venda_total){
        if (venda_total == 0){
            System.out.println("Você ainda não fez nenhuma venda hoje");
            Main.VoltarMenu();
        }else {
        System.out.println("Suas vendas totais: "+venda_total);
        while (true) {
            System.out.println("Escolha o dia para alocar as vendas totais: (dd/mm/aaaa)");
            String data = Main.scan.nextLine().trim();
            LocalDate dataValida = stringToDate(data);
            if (dataValida != null){
            System.out.printf("Alocado %.2f para o dia %s\n", venda_total, data);
            valoresDiaEMes.merge(dataValida, venda_total, Double::sum);
            
            Main.valor_de_venda_total = 0;
            break;
            }else{
            System.out.println("Tente novamente com uma data válida\n");
            }
        }
       
        
        }
        Main.VoltarMenu();
    }

    public static void BuscarVendaDia(){
        System.out.println("Digite a data para ver seu total de vendas (dd/mm/aaaa)");

        String data = Main.scan.nextLine().trim();
        LocalDate dataValida = stringToDate(data);

        if (dataValida != null){
            Double valor = valoresDiaEMes.get(dataValida);

            if (valor != null){
                System.out.println("Data "+ dataValida.format(dateFormatter) + " | Valor: " + valor + "\n");
            }else {
                System.out.println("Não tem vendas nessa data");
            }
        }else{
            System.out.println("Data inválida\n");
        }
        
        
        
        Main.VoltarMenu();
        }

        public static LocalDate dataAtualDate() {
            LocalDate dataAtual = LocalDate.now();
            return dataAtual;
        }

        public static String dataAtualString(){
            LocalDate dataAtual = LocalDate.now();
            return dateFormatter.format(dataAtual);
        }

        public static String dateToString(LocalDate date){
            String dataString = dateFormatter.format(date);
            return dataString;
        }
    }
