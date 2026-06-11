# Aplicativo de Clima — Aula 11

**Aluno:** Patrick Andretta  
**Disciplina:** Programação Orientada a Objetos  
**Professor:** Samuel babinski

## Sobre o projeto

Aplicativo de previsão do tempo com back-end em Java e front-end em HTML.  
O usuário digita o nome de uma cidade e vê as condições climáticas atuais: temperatura, máxima/mínima, umidade, precipitação e vento.

A API utilizada é a [Visual Crossing Timeline Weather API](https://www.visualcrossing.com/).  
A chave da API fica apenas no servidor — nunca exposta no navegador.

## Tecnologias

- Java
- HTML, CSS e JavaScript
- Visual Crossing Weather API

## Como executar

**1. Defina a chave da API como variável de ambiente:**

```bash
export VISUALCROSSING_API_KEY=sua_chave_aqui
```

> Para não precisar repetir isso toda vez, adicione a linha acima no seu `~/.zshrc`.

**2. Compile:**

```bash
javac -d out src/weather/*.java
```

**3. Execute:**

```bash
java -cp out weather.Main
```

**4. Acesse no navegador:**

```
http://localhost:8888
```