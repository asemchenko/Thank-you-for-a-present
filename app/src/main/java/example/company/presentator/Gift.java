package example.company.presentator;

public class Gift {
    public String key;
    public String description;

    public Gift(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public Gift() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }
}
