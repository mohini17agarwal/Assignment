# Assignment

Craft demo
The Accounting team would like to quickly look up country codes to speed up processing of expense reports, they currently have a website bookmarked and the process is manual and tedious. Help them speed things up by creating a simple cli.
•	Write a country lookup service using your favorite programming language.
•	Explore the following API https://www.travel-advisory.info/api
•	Given country code -> return country name example:
lookup --countryCode=AU
Australia


•	Save the data from https://www.travel-advisory.info/api to a file called data.json and add functionality to your program to work with a file instead of real api endpoint
•	Make sure your program supports multiple country codes as input
•	Be ready to demo execution of your program in the terminal
Bonus
Demand for your tool is increasing and there is an ask from multiple teams to be able to use county code lookup automation.Decision has been made to extend your tool to expose its functionality via REST.
•	Convert code written in Craft demo to a REST service with two routes
•	/health returns health of your service
•	/diag check returns status of the apihttps://www.travel-advisory.info/api return {"api_status":{... "code":200,"status":"ok"} that you obtained from hitting an API
•	/convert – converts country name to country code
•	Create local k8s cluster on your workstation
•	Deploy your service to local k8s cluster
The Operations team likes your service, but to make their job easier and keep things operating at an optimal level
•	Setup basic monitoring


Bonus

Step1 : Define the REST Controller:
Create a new Java class annotated with @RestController to define the endpoints and their functionality.

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CountryCodeRestController {

    private static final String API_URL = "https://www.travel-advisory.info/api";
    private static final String FILE_PATH = "data.json";

    // Health check endpoint
    @GetMapping("/health")
    public String healthCheck() {
        return "Service is healthy!";
    }

    // Diagnostic endpoint
    @GetMapping("/diag")
    public String diagCheck() {
        try {
            URL url = new URL(https://www.travel-advisory.info/api);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            
            return "{\"api_status\":{\"code\":200,\"status\":\"ok\"}}"; // Replace with actual API call
        } catch (Exception e) {
            return "Error checking API status.";
        }
    }

    // Convert country name to country code endpoint
    @GetMapping("/convert")
    public String convertCountryCode(@RequestParam("countryName") String countryName) {
        try {
            
            // Return the country code for the given country name
            return "Country code for " + countryName + " is XYZ"; // Replace XYZ with the actual code
        } catch (Exception e) {
            return "Error converting country name to code.";
        }
    }
}


Step 2: Build and run the application
Build the project using your IDE or build tools like Maven or Gradle.
Run the application, and it will start an embedded Tomcat server hosting your REST service on default port 8080.


Step 3: Test the endpoints
Test the endpoints using tools like cURL, Postman, or simply a web browser:

Health check: http://localhost:8080/api/health
Diag check: http://localhost:8080/api/diag
Convert country code: http://localhost:8080/api/convert?countryName=United%20States

Step 4: Containerise the application using jar/war package

step 5: write pipeline using Jenkins/Gitlab to deploy the app following below steps

Stages: steps

Repo checkout >>Build using maven CLI  into pckg >> validate code using Sonar Qube >> push Artifact to Ngnix repo >> Doce
ker containrise >> deploy to K8s (define required manifest to integrate with SQL DB clusterIp and nodeport services)


