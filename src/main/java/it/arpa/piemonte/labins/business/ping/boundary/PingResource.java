package it.arpa.piemonte.labins.business.ping.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author airhacks.com
 */
@Path("ping")
public class PingResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String ping() {
        return "Enjoy Java EE 8!";
    }

}
