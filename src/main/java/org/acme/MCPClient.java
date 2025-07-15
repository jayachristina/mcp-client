package org.acme;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox;


@RegisterAiService
@ApplicationScoped
public interface MCPClient {

    @SystemMessage("""
            You have tools via MCP servers to find the sentiment of a review submitted  with sentiment analysis tool or find the weather. 
            Return the response in the exact same format that receive from the toolbox. Don't change this format.
            If the user asks for anything else, tell them you don't have an answer for that.
            """
    )

    @McpToolBox
    String respond(@UserMessage String review);

}