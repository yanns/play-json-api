package models.java;

public class UserGroup {
    private final Long id;
    private final String group;

    public UserGroup(Long id, String group) {
        this.id = id;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }
}
