
## Discord Block Checker


* This Java program prompts the user to enter their Discord API token and user ID, and then checks if the specified user has blocked them or if they have blocked the user.
* It sends an API request to the Discord API and parses the JSON response to determine if the user has blocked them or if they have blocked the user.

## 🔗 Links
> * Requirements: • Java 11+ 

> - **[Java 19](https://corretto.aws/downloads/latest/amazon-corretto-19-x64-windows-jdk.msi)**

> - **[Discord API](https://discord.com/developers/docs/intro)**

## API Reference

#### Get User Data

```https
 GET /api/v9/users/[user_id]/profile
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Authorization` | `Token` | **Required**. Your Discord Token |
| `User_id` | `String` | **Required**. User Id |

```java
URL url = new URL("https://discord.com/api/v9/users/" + userId + "/profile"); // Creating a new URL object with the Discord API endpoint for the specified user's profile
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Opening an HTTP connection to the API endpoint
            conn.setRequestMethod("GET"); // Setting the HTTP request method to GET
            conn.setRequestProperty("Authorization", token); // Setting the Authorization header with the user's API token
```


## Authors

- [@Yelloow-Dev](https://www.github.com/Yelloow-Dev)

