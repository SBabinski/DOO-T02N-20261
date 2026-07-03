# Atividade Extra: Conceitos Kotlin

## Conceito 1: Coroutines

**Nome:** Felipe Donatoni

**Conceito escolhido:** Coroutines (Corrotinas)

**Timestamp do vídeo que menciona o conceito:** ~2:55 a 3:15
> "Parallel programming made intuitive with coroutines / Unleashing the power of suspending functions"

### O que é?

Coroutines são componentes de software que generalizam sub-rotinas para múltiplos pontos de entrada e saída. Em Kotlin, especificamente, são um mecanismo que permite que você escreva código assíncrono de forma sequencial e legível, sem callbacks complexos. Elas são "leves" - você pode ter milhares delas rodando simultaneamente sem consumir muitos recursos, diferente de threads tradicionais.

### Pra que serve?

Coroutines servem para:
- Fazer operações de I/O (rede, arquivo, banco de dados) sem bloquear a thread principal
- Implementar paralelismo e concorrência de forma elegante
- Simplificar código que seria muito complexo com callbacks ou futures
- Melhorar a responsividade de aplicações mobile e web

### Como é normalmente utilizado?

Em Kotlin, coroutines são utilizadas com funções `suspend` e builders como `launch` e `async`. A palavra-chave `suspend` marca uma função que pode ser pausada e retomada. Os builders `launch` e `async` criam escopos de coroutine para executar código assincronamente.

### Exemplo de código:

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000)
        println("Olá mundo!")
    }
    println("Olá")
}

// Saída:
// Olá
// Olá mundo! 
```

Outro exemplo com `async`:

```kotlin
suspend fun buscarDados(id: Int): String {
    delay(1000) // Simula operação de rede
    return "Dados do usuário $id"
}

fun main() = runBlocking {
    val resultado1 = async { buscarDados(1) }
    val resultado2 = async { buscarDados(2) }
    
    println(resultado1.await())
    println(resultado2.await())
    // Ambas as operações rodam em paralelo!
}
```

---

## Conceito 2: Extension Functions

**Nome:** Felipe Donatoni

**Conceito escolhido:** Extension Functions (Funções de Extensão)

**Timestamp do vídeo que menciona o conceito:** ~3:35 a 3:45
> "Syntax shortcuts / With infix and extension functions / Paving the way to write your custom DSLs"

### O que é?

Extension Functions são funções que você pode adicionar a uma classe já existente sem herdar dela ou usar padrões como Decorator. Elas permitem adicionar novos métodos a classes do Kotlin, da biblioteca padrão ou até mesmo do Java, como se fossem parte da classe original. Isso é feito de forma estática durante a compilação, portanto não afeta a classe real.

### Pra que serve?

Extension Functions servem para:
- Adicionar funcionalidades a classes que você não controla (como classes da biblioteca padrão)
- Melhorar a legibilidade do código tornando-o mais expressivo
- Criar DSLs (Domain Specific Languages) customizadas
- Evitar herança desnecessária e manter o código mais limpo

### Como é normalmente utilizado?

Define-se uma extension function escrevendo `fun NomeDaClasse.nomeFuncao()` fora da classe. Você tem acesso a `this` (a instância da classe) e pode usar todos os membros públicos da classe.

### Exemplo de código:

```kotlin
// Extension function para String
fun String.contarPalavras(): Int {
    return this.trim().split(Regex("\\s+")).size
}

// Usando a extension function
val texto = "Kotlin é incrível"
println(texto.contarPalavras()) // Saída: 3

// Extension function para List
fun <T> List<T>.segundoElemento(): T? {
    return if (this.size >= 2) this[1] else null
}

val numeros = listOf(10, 20, 30)
println(numeros.segundoElemento()) // Saída: 20

// Extension function com receiver implícito (útil para DSLs)
fun StringBuilder.appendBold(text: String) {
    this.append("<b>$text</b>")
}

val sb = StringBuilder()
sb.appendBold("Olá") // Saída no StringBuilder: <b>Olá</b>
```

Isso é especialmente poderoso para criar DSLs:

```kotlin
// Criando um HTML builder simples com extension functions
fun html(init: HtmlBuilder.() -> Unit): String {
    val builder = HtmlBuilder()
    builder.init() // Chamando a lambda com receiver
    return builder.build()
}

class HtmlBuilder {
    private val elements = mutableListOf<String>()
    
    fun p(text: String) {
        elements.add("<p>$text</p>")
    }
    
    fun build() = elements.joinToString("\n")
}

val page = html {
    p("Bem-vindo!")
    p("Kotlin é incrível")
}
```

---

## Resumo

Esses dois conceitos - **Coroutines** e **Extension Functions** - são características poderosas e únicas do Kotlin que o diferenciam de linguagens como Java, permitindo escrever código mais conciso, legível e eficiente.
