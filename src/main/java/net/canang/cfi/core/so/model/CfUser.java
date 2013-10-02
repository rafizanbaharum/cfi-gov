package net.canang.cfi.core.so.model;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
public interface CfUser extends CfPrincipal {

    String getUsername();

    void setUsername(String username);

    String getRealname();

    void setRealname(String realname);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    CfActor getActor();

    void setActor(CfActor actor);

}
