package tvtracker.persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import tvtracker.json.Json;
import tvtracker.json.JsonException;
import tvtracker.model.AppUser;
import tvtracker.model.ListType;
import tvtracker.model.Show;


public class DataStore {

    private static final String APP_FOLDER = ".tvtracker";
    private static final String DATA_FILE = "data.json";

    private final Path filePath;

    public DataStore() {
        Path home = Paths.get(System.getProperty("user.home"));
        this.filePath = home.resolve(APP_FOLDER).resolve(DATA_FILE);
    }


    public DataStore(Path customPath) {
        this.filePath = customPath;
    }

   
    public static class StoredData {
        public final Map<String, AppUser> users;
        public String lastUser;

        public StoredData(Map<String, AppUser> users, String lastUser) {
            this.users = users;
            this.lastUser = lastUser;
        }
    }

   
    public StoredData load() throws PersistenceException {
        try {
            if (!Files.exists(filePath)) {
                StoredData seed = createSeedData();
                save(seed);
                return seed;
            }

            String content = Files.readString(filePath, StandardCharsets.UTF_8);
            if (content.isBlank()) {
                StoredData seed = createSeedData();
                save(seed);
                return seed;
            }

            Object parsed = Json.parse(content);
            if (!(parsed instanceof Map)) {
                throw new PersistenceException("Arquivo de dados em formato inválido: " + filePath);
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> root = (Map<String, Object>) parsed;

            Map<String, AppUser> users = new LinkedHashMap<>();
            Object usersObj = root.get("users");
            if (usersObj instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> usersMap = (Map<String, Object>) usersObj;
                for (Map.Entry<String, Object> entry : usersMap.entrySet()) {
                    if (entry.getValue() instanceof Map) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> userMap = (Map<String, Object>) entry.getValue();
                        users.put(entry.getKey(), AppUser.fromMap(userMap));
                    }
                }
            }

            String lastUser = root.get("lastUser") != null ? String.valueOf(root.get("lastUser")) : null;

            if (users.isEmpty()) {
                return createSeedData();
            }

            return new StoredData(users, lastUser);

        } catch (IOException e) {
            throw new PersistenceException("Não foi possível ler o arquivo de dados em " + filePath, e);
        } catch (JsonException e) {
            throw new PersistenceException("O arquivo de dados está corrompido ou em formato inválido: " + filePath, e);
        }
    }

    
    public void save(StoredData data) throws PersistenceException {
        try {
            Map<String, Object> root = new LinkedHashMap<>();
            Map<String, Object> usersMap = new LinkedHashMap<>();
            for (Map.Entry<String, AppUser> entry : data.users.entrySet()) {
                usersMap.put(entry.getKey(), entry.getValue().toMap());
            }
            root.put("users", usersMap);
            root.put("lastUser", data.lastUser);

            String json = Json.write(root);

            Files.createDirectories(filePath.getParent());
            
            Path tempFile = filePath.resolveSibling(DATA_FILE + ".tmp");
            Files.writeString(tempFile, json, StandardCharsets.UTF_8);
            Files.move(tempFile, filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new PersistenceException("Não foi possível salvar o arquivo de dados em " + filePath, e);
        }
    }

    public Path getFilePath() {
        return filePath;
    }

   
    private StoredData createSeedData() {
        AppUser defaultUser = new AppUser("Aluno");

        Show breakingBad = new Show(169, "Breaking Bad", "English",
                Arrays.asList("Drama", "Crime", "Thriller"), 9.3, "Ended",
                "2008-01-20", "2013-09-29", "AMC",
                "Um professor de química do ensino médio recorre à fabricação "
                        + "e venda de metanfetamina após receber um diagnóstico de câncer.",
                null);

        Show gameOfThrones = new Show(82, "Game of Thrones", "English",
                Arrays.asList("Drama", "Adventure", "Fantasy"), 9.2, "Ended",
                "2011-04-17", "2019-05-19", "HBO",
                "Sete famílias nobres lutam pelo controle das terras míticas de "
                        + "Westeros, enquanto um antigo inimigo retorna após estar adormecido por milênios.",
                null);

        Show friends = new Show(431, "Friends", "English",
                Arrays.asList("Comedy", "Romance"), 8.7, "Ended",
                "1994-09-22", "2004-05-06", "NBC",
                "Seis amigos vivendo em Manhattan dividem suas alegrias, dramas e "
                        + "desventuras no trabalho e nos relacionamentos.",
                null);

        Show strangerThings = new Show(2993, "Stranger Things", "English",
                Arrays.asList("Drama", "Fantasy", "Horror"), 8.6, "Running",
                "2016-07-15", null, "Netflix",
                "Quando um garoto desaparece, seus amigos, familiares e a polícia "
                        + "local se veem envolvidos com experimentos secretos, forças "
                        + "sobrenaturais aterrorizantes e uma garota muito estranha.",
                null);

        Show theBoys = new Show(1408, "The Boys", "English",
                Arrays.asList("Drama", "Science-Fiction", "Action"), 8.4, "Running",
                "2019-07-25", null, "Amazon Prime Video",
                "Um grupo de vigilantes decide enfrentar super-heróis corruptos que "
                        + "abusam de seus poderes.",
                null);

        Show theBigBangTheory = new Show(2, "The Big Bang Theory", "English",
                Arrays.asList("Comedy"), 7.8, "Ended",
                "2007-09-24", "2019-05-16", "CBS",
                "Cientistas brilhantes, porém socialmente desajeitados, têm sua "
                        + "rotina alterada quando uma jovem aspirante a atriz se muda "
                        + "para o apartamento ao lado.",
                null);

        defaultUser.addToList(ListType.FAVORITES, breakingBad);
        defaultUser.addToList(ListType.FAVORITES, gameOfThrones);

        defaultUser.addToList(ListType.WATCHED, breakingBad);
        defaultUser.addToList(ListType.WATCHED, friends);
        defaultUser.addToList(ListType.WATCHED, theBigBangTheory);

        defaultUser.addToList(ListType.TO_WATCH, strangerThings);
        defaultUser.addToList(ListType.TO_WATCH, theBoys);

        Map<String, AppUser> users = new LinkedHashMap<>();
        users.put(defaultUser.getName(), defaultUser);

        return new StoredData(users, defaultUser.getName());
    }
}
