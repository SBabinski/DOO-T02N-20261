package tvtracker.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AppUser {

    private String name;
    private final Map<ListType, List<Show>> lists;

    public AppUser(String name) {
        this.name = name;
        this.lists = new EnumMap<>(ListType.class);
        for (ListType type : ListType.values()) {
            lists.put(type, new ArrayList<>());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Show> getList(ListType type) {
        return lists.get(type);
    }

    public boolean isInList(ListType type, int showId) {
        for (Show s : lists.get(type)) {
            if (s.getId() == showId) {
                return true;
            }
        }
        return false;
    }

    public void addToList(ListType type, Show show) {
        List<Show> list = lists.get(type);
        if (!isInList(type, show.getId())) {
            list.add(show);
        }
    }

    public void removeFromList(ListType type, int showId) {
        lists.get(type).removeIf(s -> s.getId() == showId);
    }

    public Map<ListType, List<Show>> getAllLists() {
        return lists;
    }


    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", name);
        Map<String, Object> listsMap = new LinkedHashMap<>();
        for (ListType type : ListType.values()) {
            List<Object> showMaps = new ArrayList<>();
            for (Show s : lists.get(type)) {
                showMaps.add(s.toMap());
            }
            listsMap.put(type.name(), showMaps);
        }
        map.put("lists", listsMap);
        return map;
    }

    @SuppressWarnings("unchecked")
    public static AppUser fromMap(Map<String, Object> map) {
        String name = String.valueOf(map.get("name"));
        AppUser user = new AppUser(name);
        Object listsObj = map.get("lists");
        if (listsObj instanceof Map) {
            Map<String, Object> listsMap = (Map<String, Object>) listsObj;
            for (ListType type : ListType.values()) {
                Object arr = listsMap.get(type.name());
                if (arr instanceof List) {
                    for (Object showObj : (List<Object>) arr) {
                        if (showObj instanceof Map) {
                            Show show = Show.fromMap((Map<String, Object>) showObj);
                            user.addToList(type, show);
                        }
                    }
                }
            }
        }
        return user;
    }
}
