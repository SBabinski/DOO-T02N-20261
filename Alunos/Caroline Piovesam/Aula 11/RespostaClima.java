package com.caroline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaClima {

     @JsonProperty("address")
     private String cidadeBuscada;

     private double latitude;
     private double longitude;

     @JsonProperty("currentConditions")
     private CondicoesAtuais condicoesAtuais;

     @JsonProperty("days")
     private List<PrevisaoDia> dias;

     public String getCidadeBuscada() {
          return cidadeBuscada;
     }

     public void setCidadeBuscada(String cidadeBuscada) {
          this.cidadeBuscada = cidadeBuscada;
     }

     public double getLatitude() {
          return latitude;
     }

     public void setLatitude(double latitude) {
          this.latitude = latitude;
     }

     public double getLongitude() {
          return longitude;
     }

     public void setLongitude(double longitude) {
          this.longitude = longitude;
     }

     public CondicoesAtuais getCondicoesAtuais() {
          return condicoesAtuais;
     }

     public void setCondicoesAtuais(CondicoesAtuais condicoesAtuais) {
          this.condicoesAtuais = condicoesAtuais;
     }

     public List<PrevisaoDia> getDias() {
          return dias;
     }

     public void setDias(List<PrevisaoDia> dias) {
          this.dias = dias;
     }
}