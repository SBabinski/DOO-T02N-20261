package fag;

import java.time.LocalDate;

public class Venda {

  double total;
  int qtd;
  double valor;
  double desconto;
  LocalDate data;

  public Venda(double total, double valor, int qtd, double desconto, LocalDate data) {
      this.total = total;
      if (total > 0) {
          this.total = total;
      } else {
          System.out.println("Valor total inválido. Por favor tente novamente.");
      }
      this.valor = valor;
      if (valor > 0) {
          this.valor = valor;
      } else {
          System.out.println("Valor unitário inválido. Por favor tente novamente.");
      }
      this.qtd = qtd;
      this.desconto = desconto;
      this.data = data;
  }

}
