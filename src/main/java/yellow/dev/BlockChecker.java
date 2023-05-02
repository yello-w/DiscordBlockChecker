package yellow.dev;

import org.json.JSONObject; // Importing the JSONObject class from the org.json package
import java.net.HttpURLConnection; // Importing the HttpURLConnection class from the java.net package
import java.net.URL; // Importing the URL class from the java.net package
import java.util.Scanner; // Importing the Scanner class from the java.util package

public class BlockChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Creating a new Scanner object to read user input from the console
        System.out.print("Developed By Yellow [https://github.com/Yelloow-Dev] \n");
        char[] tokenChars = System.console().readPassword("Enter your token: "); // Prompting the user to enter their Discord API token and reading the input as a character array
        String token = new String(tokenChars); // Converting the character array to a String

        long userId = 0; // Initializing the user ID variable to 0
        boolean isValidInput = false; // Initializing the input validation flag to false
        while (!isValidInput) { // Looping until valid input is entered
            System.out.print("Enter your user ID (must be between 16 and 19 digits): "); // Prompting the user to enter their user ID
            String input = scanner.nextLine(); // Reading the user's input
            if (input.length() >= 16 && input.length() <= 19) { // Checking if the input is between 16 and 19 digits long
                try {
                    userId = Long.parseLong(input); // Parsing the input as a long integer and assigning it to the user ID variable
                    isValidInput = true; // Setting the input validation flag to true
                } catch (NumberFormatException e) { // Catching the exception if the input is not a valid integer
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            } else { // Executed if the input is not between 16 and 19 digits long
                System.out.println("Invalid input. Please enter a number between 16 and 19 digits.");
            }
        }

        try {
            URL url = new URL("https://discord.com/api/v9/users/" + userId + "/profile"); // Creating a new URL object with the Discord API endpoint for the specified user's profile
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Opening an HTTP connection to the API endpoint
            conn.setRequestMethod("GET"); // Setting the HTTP request method to GET
            conn.setRequestProperty("Authorization", token); // Setting the Authorization header with the user's API token

            int statusCode = conn.getResponseCode(); // Getting the HTTP response status code
            String statusmessage = conn.getResponseMessage(); // Getting the HTTP response status message
            if (statusCode == 200) { // Executed if the API request was successful
                Scanner responseScanner = new Scanner(conn.getInputStream()).useDelimiter("\\A"); // Creating a new Scanner object to read the API response from the connection's input stream
                String response = responseScanner.hasNext() ? responseScanner.next() : ""; // Reading the API response into a String
                JSONObject jsonObject = new JSONObject(response); // Parsing the API response as a JSON object
                if (!jsonObject.isNull("user_profile")) { // Executed if the user's profile is not null
                    System.out.println("The user has not been blocked & The user did not block you."); // Printing a message indicating that the user has not been blocked and did not block
                } else { // Executed if the user's profile is null
                    System.out.println("The user has not been blocked but has not shared their profile information."); // Printing a message indicating that the user has not been blocked but has not shared their profile information
                }
            } else if (statusCode == 401) { // Executed if the API request is unauthorized
                System.out.println("Unauthorized. Please check your API token and try again."); // Printing an error message indicating that the API token is unauthorized
            } else { // Executed if the API request is unsuccessful for any other reason
                System.out.println("API request unsuccessful. Status code: " + statusCode + " " + statusmessage); // Printing an error message with the HTTP status code and status message
            }
            conn.disconnect(); // Closing the HTTP connection
        } catch (Exception e) { // Catching any exceptions that occur during the API request
            System.out.println("Error: " + e.getMessage()); // Printing an error message with the exception message
        }
    }
}

/**
 * This Java program prompts the user to enter their Discord API token and user ID, and then checks if the specified user has blocked them or if they have blocked the user.
 * It sends an API request to the Discord API and parses the JSON response to determine if the user has blocked them or if they have blocked the user.
 */