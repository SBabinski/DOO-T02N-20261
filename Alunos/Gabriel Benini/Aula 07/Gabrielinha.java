package CalculadoraLoja;

import java.time.LocalDate;

public class Gabrielinha {

        private final int registro;

        private float valorTotal;
        private int quantidade;
        private float desconto;
        private float dinheiro;
        private float troco;

        private final LocalDate dataVenda;

        public Gabrielinha(int registro, float valorTotal, int quantidade, float desconto, LocalDate dataVenda) {

            this.registro = registro;
            this.valorTotal = valorTotal;
            this.quantidade = quantidade;
            this.desconto = desconto;
            this.dataVenda = dataVenda;

        }

        public LocalDate getDataVenda() {
            return dataVenda;
        }

        public float getValorTotal() {
            return valorTotal;
        }

        public float getDesconto() {
            return desconto;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public float getDinheiro() {
            return dinheiro;
        }

        public void setDinheiro(float dinheiro) {
            this.dinheiro = dinheiro;
        }

        public float getTroco() {
            return troco;
        }

        public void setTroco(float troco) {
            this.troco = troco;
        }

        public int getRegistro() {
            return registro;
        }

        @Override
        public String toString() {

            String string = "registro: " + registro + " Quantidade: " + quantidade;

            if (quantidade < 10) {

                string = string + " Valor total: " + valorTotal;

            } else {

                string = string + " Valor inicial: " + (valorTotal + desconto) + " / Valor descontado: " + desconto + " / Valor total: " + valorTotal;

            }

            if (dinheiro > 0)
                string = string + " Dinheiro: " + dinheiro;

            if (troco > 0)
                string = string + " Troco: " + troco;

            string = string + " data venda: " + Main.DATE_TIME_FORMATTER.format(dataVenda);

            return string;

        }

}
