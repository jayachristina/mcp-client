package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/mcp")
public class MCPClientResource {

    @Inject
    MCPClient myAnalyserClient;

    
    @GET
    @Path("/analyze-text")
    @Produces(MediaType.TEXT_PLAIN)
    public String analyzeSentimentAsText(@QueryParam("text") String text) {
        System.out.println("Received text from customer: " + text);
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Text parameter is required");
        }
        
        return myAnalyserClient.respond(text);
    }


}
