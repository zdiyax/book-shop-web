package kz.epam.zd.model.user;

public class Locale {
    private String localeName;

    public Locale(String localeName) {
        this.localeName = localeName;
    }

    public String getLocaleName() {
        return localeName;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }

    @Override
    public String toString() {
        return localeName;
    }
}
