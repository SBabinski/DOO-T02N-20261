import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Date {
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

    public static String dateToString(LocalDate date){
        String dataString = dateFormatter.format(date);
        return dataString;
        }
}
