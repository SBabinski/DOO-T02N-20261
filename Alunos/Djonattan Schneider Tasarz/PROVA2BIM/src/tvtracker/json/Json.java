package tvtracker.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public final class Json {

    private Json() {
        
    }


    public static Object parse(String text) {
        Parser parser = new Parser(text);
        Object value = parser.parseValue();
        parser.skipWhitespace();
        if (!parser.isAtEnd()) {
            throw new JsonException("Caracteres inesperados após o JSON principal na posição " + parser.pos);
        }
        return value;
    }

    public static String write(Object value) {
        StringBuilder sb = new StringBuilder();
        writeValue(value, sb, 0);
        return sb.toString();
    }

    

    private static void writeValue(Object value, StringBuilder sb, int indent) {
        if (value == null) {
            sb.append("null");
        } else if (value instanceof String) {
            writeString((String) value, sb);
        } else if (value instanceof Double || value instanceof Integer || value instanceof Long) {
            double d = ((Number) value).doubleValue();
            if (d == Math.floor(d) && !Double.isInfinite(d)) {
                sb.append((long) d);
            } else {
                sb.append(d);
            }
        } else if (value instanceof Boolean) {
            sb.append(value.toString());
        } else if (value instanceof Map) {
            writeObject((Map<?, ?>) value, sb, indent);
        } else if (value instanceof List) {
            writeArray((List<?>) value, sb, indent);
        } else {
            
            writeString(String.valueOf(value), sb);
        }
    }

    private static void writeObject(Map<?, ?> map, StringBuilder sb, int indent) {
        if (map.isEmpty()) {
            sb.append("{}");
            return;
        }
        sb.append("{\n");
        int i = 0;
        int size = map.size();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            indent(sb, indent + 1);
            writeString(String.valueOf(entry.getKey()), sb);
            sb.append(": ");
            writeValue(entry.getValue(), sb, indent + 1);
            if (++i < size) {
                sb.append(",");
            }
            sb.append("\n");
        }
        indent(sb, indent);
        sb.append("}");
    }

    private static void writeArray(List<?> list, StringBuilder sb, int indent) {
        if (list.isEmpty()) {
            sb.append("[]");
            return;
        }
        sb.append("[\n");
        for (int i = 0; i < list.size(); i++) {
            indent(sb, indent + 1);
            writeValue(list.get(i), sb, indent + 1);
            if (i < list.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        indent(sb, indent);
        sb.append("]");
    }

    private static void indent(StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
    }

    private static void writeString(String s, StringBuilder sb) {
        sb.append('"');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"': sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                case '\n': sb.append("\\n"); break;
                case '\r': sb.append("\\r"); break;
                case '\t': sb.append("\\t"); break;
                default:
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
    }


    private static final class Parser {
        private final String text;
        private int pos = 0;

        Parser(String text) {
            this.text = text;
        }

        boolean isAtEnd() {
            return pos >= text.length();
        }

        void skipWhitespace() {
            while (pos < text.length() && Character.isWhitespace(text.charAt(pos))) {
                pos++;
            }
        }

        char peek() {
            if (pos >= text.length()) {
                throw new JsonException("Fim inesperado do JSON na posição " + pos);
            }
            return text.charAt(pos);
        }

        char next() {
            char c = peek();
            pos++;
            return c;
        }

        void expect(char c) {
            if (peek() != c) {
                throw new JsonException("Esperado '" + c + "' na posição " + pos + " mas encontrou '" + peek() + "'");
            }
            pos++;
        }

        Object parseValue() {
            skipWhitespace();
            char c = peek();
            switch (c) {
                case '{': return parseObject();
                case '[': return parseArray();
                case '"': return parseString();
                case 't':
                case 'f': return parseBoolean();
                case 'n': return parseNull();
                default: return parseNumber();
            }
        }

        Map<String, Object> parseObject() {
            Map<String, Object> map = new LinkedHashMap<>();
            expect('{');
            skipWhitespace();
            if (peek() == '}') {
                pos++;
                return map;
            }
            while (true) {
                skipWhitespace();
                String key = parseString();
                skipWhitespace();
                expect(':');
                Object value = parseValue();
                map.put(key, value);
                skipWhitespace();
                char c = next();
                if (c == ',') {
                    continue;
                } else if (c == '}') {
                    break;
                } else {
                    throw new JsonException("Esperado ',' ou '}' na posição " + (pos - 1));
                }
            }
            return map;
        }

        List<Object> parseArray() {
            List<Object> list = new ArrayList<>();
            expect('[');
            skipWhitespace();
            if (peek() == ']') {
                pos++;
                return list;
            }
            while (true) {
                Object value = parseValue();
                list.add(value);
                skipWhitespace();
                char c = next();
                if (c == ',') {
                    continue;
                } else if (c == ']') {
                    break;
                } else {
                    throw new JsonException("Esperado ',' ou ']' na posição " + (pos - 1));
                }
            }
            return list;
        }

        String parseString() {
            expect('"');
            StringBuilder sb = new StringBuilder();
            while (true) {
                char c = next();
                if (c == '"') {
                    break;
                }
                if (c == '\\') {
                    char escaped = next();
                    switch (escaped) {
                        case '"': sb.append('"'); break;
                        case '\\': sb.append('\\'); break;
                        case '/': sb.append('/'); break;
                        case 'n': sb.append('\n'); break;
                        case 'r': sb.append('\r'); break;
                        case 't': sb.append('\t'); break;
                        case 'b': sb.append('\b'); break;
                        case 'f': sb.append('\f'); break;
                        case 'u':
                            String hex = text.substring(pos, pos + 4);
                            pos += 4;
                            sb.append((char) Integer.parseInt(hex, 16));
                            break;
                        default:
                            throw new JsonException("Escape inválido '\\" + escaped + "' na posição " + pos);
                    }
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }

        Double parseNumber() {
            int start = pos;
            if (peek() == '-') pos++;
            while (pos < text.length() && Character.isDigit(text.charAt(pos))) pos++;
            if (pos < text.length() && text.charAt(pos) == '.') {
                pos++;
                while (pos < text.length() && Character.isDigit(text.charAt(pos))) pos++;
            }
            if (pos < text.length() && (text.charAt(pos) == 'e' || text.charAt(pos) == 'E')) {
                pos++;
                if (pos < text.length() && (text.charAt(pos) == '+' || text.charAt(pos) == '-')) pos++;
                while (pos < text.length() && Character.isDigit(text.charAt(pos))) pos++;
            }
            String numStr = text.substring(start, pos);
            if (numStr.isEmpty() || numStr.equals("-")) {
                throw new JsonException("Número inválido na posição " + start);
            }
            return Double.parseDouble(numStr);
        }

        Boolean parseBoolean() {
            if (text.startsWith("true", pos)) {
                pos += 4;
                return Boolean.TRUE;
            } else if (text.startsWith("false", pos)) {
                pos += 5;
                return Boolean.FALSE;
            }
            throw new JsonException("Valor booleano inválido na posição " + pos);
        }

        Object parseNull() {
            if (text.startsWith("null", pos)) {
                pos += 4;
                return null;
            }
            throw new JsonException("Valor inválido na posição " + pos);
        }
    }
}
