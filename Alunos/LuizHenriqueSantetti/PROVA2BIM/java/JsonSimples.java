import java.util.ArrayList;

public class JsonSimples {
    private String texto;
    private int posicao;

    public Object ler(String json) throws Exception {
        if (json == null || json.trim().isEmpty())
            throw new Exception("JSON vazio");
        texto = json;
        posicao = 0;
        Object valor = lerValor();
        pularEspacos();
        if (posicao != texto.length())
            throw erro("Conteudo depois do final");
        return valor;
    }

    private Object lerValor() throws Exception {
        pularEspacos();
        if (posicao >= texto.length())
            throw erro("Valor esperado");
        char c = texto.charAt(posicao);
        if (c == '{')
            return lerObjeto();
        if (c == '[')
            return lerArray();
        if (c == '"')
            return lerString();
        if (c == 't') {
            lerPalavra("true");
            return Boolean.TRUE;
        }
        if (c == 'f') {
            lerPalavra("false");
            return Boolean.FALSE;
        }
        if (c == 'n') {
            lerPalavra("null");
            return null;
        }
        if (c == '-' || Character.isDigit(c))
            return lerNumero();
        throw erro("Valor invalido");
    }

    private JsonObjeto lerObjeto() throws Exception {
        JsonObjeto objeto = new JsonObjeto();
        posicao++;
        pularEspacos();
        if (proximo('}'))
            return objeto;
        while (true) {
            pularEspacos();
            if (posicao >= texto.length() || texto.charAt(posicao) != '"')
                throw erro("Chave esperada");
            String chave = lerString();
            pularEspacos();
            exigir(':');
            objeto.adicionar(chave, lerValor());
            pularEspacos();
            if (proximo('}'))
                return objeto;
            exigir(',');
        }
    }

    private ArrayList<Object> lerArray() throws Exception {
        ArrayList<Object> lista = new ArrayList<Object>();
        posicao++;
        pularEspacos();
        if (proximo(']'))
            return lista;
        while (true) {
            lista.add(lerValor());
            pularEspacos();
            if (proximo(']'))
                return lista;
            exigir(',');
        }
    }

    private String lerString() throws Exception {
        exigir('"');
        StringBuilder resultado = new StringBuilder();
        while (posicao < texto.length()) {
            char c = texto.charAt(posicao++);
            if (c == '"')
                return resultado.toString();
            if (c == '\\') {
                if (posicao >= texto.length())
                    throw erro("Escape incompleto");
                char escape = texto.charAt(posicao++);
                if (escape == '"' || escape == '\\' || escape == '/')
                    resultado.append(escape);
                else if (escape == 'b')
                    resultado.append('\b');
                else if (escape == 'f')
                    resultado.append('\f');
                else if (escape == 'n')
                    resultado.append('\n');
                else if (escape == 'r')
                    resultado.append('\r');
                else if (escape == 't')
                    resultado.append('\t');
                else if (escape == 'u')
                    resultado.append(lerUnicode());
                else
                    throw erro("Escape invalido");
            } else
                resultado.append(c);
        }
        throw erro("String nao terminada");
    }

    private char lerUnicode() throws Exception {
        if (posicao + 4 > texto.length())
            throw erro("Unicode incompleto");
        try {
            char c = (char) Integer.parseInt(texto.substring(posicao, posicao + 4), 16);
            posicao += 4;
            return c;
        } catch (NumberFormatException e) {
            throw erro("Unicode invalido");
        }
    }

    private Number lerNumero() throws Exception {
        int inicio = posicao;
        if (texto.charAt(posicao) == '-')
            posicao++;
        while (posicao < texto.length() && Character.isDigit(texto.charAt(posicao)))
            posicao++;
        if (posicao < texto.length() && texto.charAt(posicao) == '.') {
            posicao++;
            while (posicao < texto.length() && Character.isDigit(texto.charAt(posicao)))
                posicao++;
        }
        if (posicao < texto.length() && (texto.charAt(posicao) == 'e' || texto.charAt(posicao) == 'E')) {
            posicao++;
            if (posicao < texto.length() && (texto.charAt(posicao) == '+' || texto.charAt(posicao) == '-'))
                posicao++;
            while (posicao < texto.length() && Character.isDigit(texto.charAt(posicao)))
                posicao++;
        }
        try {
            String numero = texto.substring(inicio, posicao);
            if (numero.contains(".") || numero.contains("e") || numero.contains("E"))
                return Double.parseDouble(numero);
            return Long.parseLong(numero);
        } catch (NumberFormatException e) {
            throw erro("Numero invalido");
        }
    }

    private void lerPalavra(String palavra) throws Exception {
        if (!texto.startsWith(palavra, posicao))
            throw erro("Palavra invalida");
        posicao += palavra.length();
    }

    private void pularEspacos() {
        while (posicao < texto.length() && Character.isWhitespace(texto.charAt(posicao)))
            posicao++;
    }

    private boolean proximo(char esperado) {
        if (posicao < texto.length() && texto.charAt(posicao) == esperado) {
            posicao++;
            return true;
        }
        return false;
    }

    private void exigir(char esperado) throws Exception {
        if (!proximo(esperado))
            throw erro("Esperado: " + esperado);
    }

    private Exception erro(String mensagem) {
        return new Exception(mensagem + " na posicao " + posicao);
    }

    public String escrever(Object valor) {
        if (valor == null)
            return "null";
        if (valor instanceof String)
            return escapar((String) valor);
        if (valor instanceof Number || valor instanceof Boolean)
            return valor.toString();
        if (valor instanceof JsonObjeto) {
            JsonObjeto objeto = (JsonObjeto) valor;
            StringBuilder json = new StringBuilder("{");
            for (int i = 0; i < objeto.tamanho(); i++) {
                if (i > 0)
                    json.append(',');
                json.append(escapar(objeto.pegarNome(i)));
                json.append(':');
                json.append(escrever(objeto.pegarValor(i)));
            }
            return json.append('}').toString();
        }
        if (valor instanceof Iterable) {
            StringBuilder json = new StringBuilder("[");
            boolean primeiro = true;
            for (Object item : (Iterable<?>) valor) {
                if (!primeiro)
                    json.append(',');
                json.append(escrever(item));
                primeiro = false;
            }
            return json.append(']').toString();
        }
        return escapar(String.valueOf(valor));
    }

    private String escapar(String valor) {
        StringBuilder resultado = new StringBuilder("\"");
        for (int i = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            if (c == '"')
                resultado.append("\\\"");
            else if (c == '\\')
                resultado.append("\\\\");
            else if (c == '\n')
                resultado.append("\\n");
            else if (c == '\r')
                resultado.append("\\r");
            else if (c == '\t')
                resultado.append("\\t");
            else if (c < 32)
                resultado.append(String.format("\\u%04x", (int) c));
            else
                resultado.append(c);
        }
        return resultado.append('"').toString();
    }
}
