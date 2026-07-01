package tvtracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import tvtracker.api.ApiException;
import tvtracker.api.TvMazeClient;
import tvtracker.model.AppUser;
import tvtracker.model.ListType;
import tvtracker.model.Show;
import tvtracker.persistence.DataStore;
import tvtracker.persistence.PersistenceException;

public class LibraryService {

    private final DataStore dataStore;
    private final TvMazeClient apiClient;
    private DataStore.StoredData storedData;
    private AppUser currentUser;

    public LibraryService() throws PersistenceException {
        this.dataStore = new DataStore();
        this.apiClient = new TvMazeClient();
        this.storedData = dataStore.load();
        resolveCurrentUser();
    }

    private void resolveCurrentUser() {
        if (storedData.lastUser != null && storedData.users.containsKey(storedData.lastUser)) {
            currentUser = storedData.users.get(storedData.lastUser);
        } else if (!storedData.users.isEmpty()) {
            currentUser = storedData.users.values().iterator().next();
            storedData.lastUser = currentUser.getName();
        } else {
            currentUser = new AppUser("Aluno");
            storedData.users.put(currentUser.getName(), currentUser);
            storedData.lastUser = currentUser.getName();
        }
    }

    

    public AppUser getCurrentUser() {
        return currentUser;
    }

    public Set<String> getAllUserNames() {
        return new TreeSet<>(storedData.users.keySet());
    }

   
    public void switchUser(String name) throws PersistenceException {
        String trimmed = name == null ? "" : name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("O nome de usuário não pode ser vazio.");
        }
        AppUser user = storedData.users.get(trimmed);
        if (user == null) {
            user = new AppUser(trimmed);
            storedData.users.put(trimmed, user);
        }
        currentUser = user;
        storedData.lastUser = trimmed;
        save();
    }

    

    public List<Show> search(String query) throws ApiException {
        return apiClient.search(query);
    }

    
    public void addToList(ListType type, Show show) throws PersistenceException {
        currentUser.addToList(type, show);
        save();
    }

    public void removeFromList(ListType type, int showId) throws PersistenceException {
        currentUser.removeFromList(type, showId);
        save();
    }

    public boolean isInList(ListType type, int showId) {
        return currentUser.isInList(type, showId);
    }

    
    public List<Show> getSortedList(ListType type, SortCriteria criteria) {
        List<Show> copy = new ArrayList<>(currentUser.getList(type));
        copy.sort(criteria.comparator());
        return copy;
    }

    

    public void save() throws PersistenceException {
        dataStore.save(storedData);
    }

    public String getDataFileLocation() {
        return dataStore.getFilePath().toString();
    }
}
