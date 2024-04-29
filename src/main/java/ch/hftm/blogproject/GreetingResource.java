package ch.hftm.blogproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {


    private static int timesVisited = 0;
    

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String randomFact = readRandomFactFile("src/main/resources/randomFacts.txt");
        timesVisited++;
        return "Willkommen auf meinem Blog! \n" + 
        "Check out this: http://localhost:8080/duck\n " + 
        "This site has been reloaded " + timesVisited + " times.\n" +
        "\n" + 
        "Random Fact I copied from the internet:\n" + randomFact;
    }

    private String readRandomFactFile(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            Random random = new Random();
            return lines.get(random.nextInt(lines.size()));
        } catch (IOException e) {
            e.printStackTrace();
            return "Something failed. A team of highly skilled monkeys is working on the problem.";
        }
    }
}