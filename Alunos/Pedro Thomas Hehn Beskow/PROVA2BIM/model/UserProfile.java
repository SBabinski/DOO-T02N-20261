package tvtracker.model;

public class UserProfile {
    private String name;
    private String nickname;

    public UserProfile() {
        this.name = "";
        this.nickname = "";
    }

    public UserProfile(String name, String nickname) {
        this.name = name != null ? name : "";
        this.nickname = nickname != null ? nickname : "";
    }

    public String getName()     { return name; }
    public String getNickname() { return nickname; }

    public void setName(String name)         { this.name = name != null ? name : ""; }
    public void setNickname(String nickname) { this.nickname = nickname != null ? nickname : ""; }

    public String getDisplayName() {
        if (!nickname.isBlank()) return nickname;
        if (!name.isBlank())     return name;
        return "Usuário";
    }

    public boolean isConfigured() {
        return !name.isBlank() || !nickname.isBlank();
    }
}
