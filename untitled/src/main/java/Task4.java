import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Task4 {
    public static void main(String[] args) throws Exception{
        //try (HttpClient client = HttpClient.newHttpClient())
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/ip"))
                    .build();
            HttpResponse<String> response = client.send(request
                    , HttpResponse.BodyHandlers.ofString());
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
            //ObjectMapper om = new

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                String jsonString = objectMapper.writeValueAsString(response.body());
                System.out.println("JSON String: " + jsonString);
                System.out.println(objectMapper.readValue("origin", String.class));

            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            //objectMapper.readValue()

            // Десериализация JSON в объект
            //User deserializedUser = objectMapper.readValue(jsonString, User.class);
            //System.out.println("Deserialized User: " + deserializedUser);




//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
    }
/* Синхронный запрос
 import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SyncHttpClientExample
{
   public static void main(String[] args) throws IOException, InterruptedException
   {
       try (HttpClient client = HttpClient.newHttpClient())
       {
           HttpRequest request = HttpRequest.newBuilder()
                   .uri(URI.create("https://httpbin.org"))
                   .build();
        HttpResponse<String> response = client.send(request,
HttpResponse.BodyHandlers.ofString());
               System.out.println("Response status code: " + response.statusCode());
               System.out.println("Response body: " + response.body());
       }
   }
}
 */

    /*

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class AsyncHttpClientExample
{
   public static void main(String[] args)
   {
       CompletableFuture<HttpResponse<String>> response;
       try (HttpClient client = HttpClient.newHttpClient())
       {
           HttpRequest request = HttpRequest.newBuilder()
                   .uri(URI.create("https://httpbin.org"))
                   .build();
           response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
       }

       response.thenAccept(resp ->
       {
           System.out.println("Response status code: " + resp.statusCode());
           System.out.println("Response body: " + resp.body());
       }).join();

       response.exceptionally(e ->
       {
           System.out.println(e.getMessage());
           return null;
       });
   }
}
     */
    }
}
