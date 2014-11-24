package models.java;

public class UserGroup {
    private Long id;
    private String group;

    public UserGroup() {
    }

    public UserGroup(Long id, String group) {
        this.id = id;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
