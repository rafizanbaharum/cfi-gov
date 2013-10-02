package net.canang.cfi.core.so.model;

/**
 * @author canang.technologies
 * @since 7/12/13
 */
public enum CfRoleType {

    ROLE_ADMINISTRATOR("ADMINISTRATOR"),
    ROLE_USER("USER"),
    ROLE_GUEST("GUEST");

    private String description;

    CfRoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
