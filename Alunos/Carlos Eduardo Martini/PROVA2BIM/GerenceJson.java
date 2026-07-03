public class GerenceJson {
    
    public String extraCampo(String jsonText, String nomeCamp) {
        String chave = "\"" + nomeCamp + "\":\"";
        int posicInicio = jsonText.indexOf(chave);

        if (posicInicio == -1) {
            return "nao encontrado";
        }

        posicInicio += chave.length();
        int posicFim = jsonText.indexOf("\"", posicInicio);
        String infoResult = jsonText.substring(posicInicio, posicFim);
        
        return infoResult;
    }

    public String extraiNota(String jsonText) {
        String chaveBloco = "\"rating\":";
        int posicBloco = jsonText.indexOf(chaveBloco);
        if (posicBloco == -1) return "nao encontrado";

        String chaveMedia = "\"average\":";
        int posicInicio = jsonText.indexOf(chaveMedia, posicBloco);
        if (posicInicio == -1) return "nao encontrado";

        posicInicio += chaveMedia.length();
        
        int posicFim = jsonText.indexOf(",", posicInicio);
        if (posicFim == -1) {
            posicFim = jsonText.indexOf("}", posicInicio);
        }

        if (posicFim == -1) return "nao encontrado";

        // Código corrigido e unificado:
        String infoResult = jsonText.substring(posicInicio, posicFim).trim();

        if (infoResult.contains("}")) {
            infoResult = infoResult.replace("}", "").trim();
        }
        
        if (infoResult.equals("null") || infoResult.isEmpty()) {
            return "nao encontrado";
        }
        
        return infoResult;
    }

    public String extraiEmissora(String jsonText) {
        String chaveNet = "\"network\":";
        int posicNet = jsonText.indexOf(chaveNet);
        
        if (posicNet != -1 && !jsonText.substring(posicNet, posicNet + 15).contains("null")) {
            String chaveNome = "\"name\":\"";
            int posicInicio = jsonText.indexOf(chaveNome, posicNet) + chaveNome.length();
            int posicFim = jsonText.indexOf("\"", posicInicio);
            return jsonText.substring(posicInicio, posicFim);
        }

        String chaveWeb = "\"webChannel\":";
        int posicWeb = jsonText.indexOf(chaveWeb);
        
        if (posicWeb != -1 && !jsonText.substring(posicWeb, posicWeb + 15).contains("null")) {
            String chaveNome = "\"name\":\"";
            int posicInicio = jsonText.indexOf(chaveNome, posicWeb) + chaveNome.length();
            int posicFim = jsonText.indexOf("\"", posicInicio);
            return jsonText.substring(posicInicio, posicFim);
        }

        return "nao encontrado";
    }

    public String extraiGeneros(String jsonText) {
        String chave = "\"genres\":[";
        int posicInicio = jsonText.indexOf(chave);
        
        if (posicInicio == -1) return "nao encontrado";

        posicInicio += chave.length();
        
        int posicFim = jsonText.indexOf("]", posicInicio);
        String infoResult = jsonText.substring(posicInicio, posicFim);

        if (infoResult.trim().isEmpty()) {
            return "nao encontrado";
        }

        infoResult = infoResult.replace("\"", "");
        infoResult = infoResult.replace(",", ", "); 

        return infoResult;
    }

    public String extraiImagem(String jsonText) {
        String chaveBloco = "\"image\":";
        int posicBloco = jsonText.indexOf(chaveBloco);
        
        if (posicBloco == -1 || jsonText.substring(posicBloco, posicBloco + 15).contains("null")) {
            return "nao encontrado";
        }

        String chaveMedium = "\"medium\":\"";
        int posicInicio = jsonText.indexOf(chaveMedium, posicBloco);
        if (posicInicio == -1) return "nao encontrado";

        posicInicio += chaveMedium.length();
        int posicFim = jsonText.indexOf("\"", posicInicio);
        
        String infoResult = jsonText.substring(posicInicio, posicFim);
        
        infoResult = infoResult.replace("\\/", "/");
        
        return infoResult;
    }
}