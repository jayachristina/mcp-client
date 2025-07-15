# Intro

Not much of an intro. Here is my try outs of learning MCP (build an MCP servers and a client to consume those servers). This doesn't use a Chat usecase but is via a REST API call.

There could be a number of errors in this.. I am just exploring and learning 😁 .. please use with caution!! 😅

## MCP Client

This project is a Quarkus-based application that acts as a client for two Model Context Protocol (MCP) servers: one for *sentiment analysis* and one for *weather information*. The Weather information service is based on the Quarkus example from [here](https://quarkus.io/blog/mcp-server/)
In real life you might never call such disparate MCP servers.. but this just showcases how to call different MCP servers in a simplistic example.

## Stuff

- **Sentiment Analysis MCP Server:** 
   - Analyze the sentiment of user-provided text via an MCP server. [Git Repo here](https://github.com/jayachristina/weather-mcp-server) 
   - Uses OpenAI to analyse the sentiment and returns a well formatted JSON.

- **Weather MCP Server:** 
   - Retrieve weather data for US-based locations via an MCP server. This just returns text. [Git repo here](https://github.com/jayachristina/sentiment-mcp-server)
   - Make a call to https://api.weather.gov to get weather details of US based locations. There is no AI in this.
- **MCP Client**  
   - Uses OpenAI API for language understanding. 
   - The MCP Client uses this to figure out which MCP tool to use (weather or sentiment)
   - If you ask for anything other than weather in US or review of text, it tells you `I don't have an answer for that.` 

## Prerequisites

- Java 21+
- [Quarkus or Maven CLI](https://quarkus.io/guides/cli-tooling) (optional, for local dev)
- [jbang](https://www.jbang.dev/) (for MCP server runners)
- An OpenAI API key. I bought some credits for 5$ and have literally jsut used a few cents. 

## MCP Servers setup

* You will need to first git clone both the [weather-mcp-server](https://github.com/jayachristina/weather-mcp-server ) and [sentiment-mcp-server](https://github.com/jayachristina/sentiment-mcp-server) git repos. 
   * From the root of the projects run `mvn install` to make the MCP servers available in your local machine. 
   * In this example I use the `stdio` transport mode - IRL this would be a HTTP endpoint. 
   * I will update this once I have images created for the MCP servers so that you can simply run it on Podman Desktop and consume them via `http` transport mode
* You need [jbang](https://www.jbang.dev/) to make the MCP servers available to the MCP client

## Setup of this MCP Client

1. **Clone the repository:**
   ```bash
   git clone https://github.com/jayachristina/mcp-client
   cd mcp-client
   ```

2. **Set your OpenAI API key:**
     export OPENAI_API_KEY="your-actual-api-key-here"
   
  
3. ** Install jbang:**  
   MCP servers are started via jbang runners as configured in [`src/main/resources/application.properties`](src/main/resources/application.properties).

## API Endpoints

### Analyze Text

- **GET** `/mcp/analyze-text?text=<your-text>`
  - Analyzes the sentiment of the provided text or returns weather for US-based places.
  - For unsupported queries, responds with "I don't know the answer."


### Dev Mode (Live Coding)

```bash
./mvnw quarkus:dev
```

**Example:**
```bash
curl "http://localhost:8080/mcp/analyze-text?text=Analyse sentiment of I love this product"
```

or simply run `http://localhost:8080/mcp/analyze-text?text=Analyse sentiment of I love this product` or `http://localhost:8080/mcp/analyze-text?text=Weather in TX` in a browser.



## Related Guides

- [LangChain4j with Quarkus](https://docs.quarkiverse.io/quarkus-langchain4j/dev/index.html)
- [MCP Server with Quarkus](https://docs.quarkiverse.io/quarkus-mcp-server/dev/index.html)

---
**License:** Apache 2.0



