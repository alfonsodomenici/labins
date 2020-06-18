package it.arpa.piemonte.labins.business;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("api")
@LoginConfig(authMethod = "MP-JWT", realmName = "testrealm")
@DeclareRoles({"admins","users"})
public class JAXRSConfiguration extends Application {

}
