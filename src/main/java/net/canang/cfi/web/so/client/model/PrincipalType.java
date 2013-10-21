package net.canang.cfi.web.so.client.model;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public enum PrincipalType {

    USER,
    GROUP;

    public static PrincipalType get(int index) {
        return values()[index];
    }

}
