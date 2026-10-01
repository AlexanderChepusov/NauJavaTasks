package TaskJson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Task4 {
    public static void main(String[] args) throws Exception{
        String[] data = {"https://httpbin.org/ip",
                "https://httpbin.org/user-agent",
                "https://httpbin.org/headers",
                "https://httpbin.org/anything",
                "https://httpbin.org/get"};
        List<String> tasks = Arrays.asList(data);
        //try (HttpClient client = HttpClient.newHttpClient())`
        /*

Варианты заданий.
1. Вывести только  значение  IP адреса с которого был сделан запрос
(запрос выполняется по адресу “https://httpbin.org/ip”).
2. Вывести только значение идентификационной строки приложения
с  которого  выполняется  запрос  (запрос  выполняется  по  адресу
“https://httpbin.org/user-agent”).
3. Вывести  только  заголовки  запроса в виде списка  значений через
запятую (запрос выполняется по адресу “https://httpbin.org/headers”).
4.  Вывести  только  допустимые  типы  ответа  (поле  “Accept”)  из
заголовков (запрос выполняется по адресу “https://httpbin.org/anything”).
5.  Вывести  только  значение  хоста  сервера  (поле  “Host”)  из
заголовков (запрос выполняется по адресу “https://httpbin.org/get”).

Выходные  данные:  в  консоль  напечатан  результат  выполнения  запроса
обработанный в соответствии с вариантом задания.

         */
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(tasks.get(2)))
                    .build();
            HttpResponse<String> response = client.send(request
                    , HttpResponse.BodyHandlers.ofString());
            System.out.println("Response status code: " + response.statusCode());
            ObjectMapper mapper = new ObjectMapper();
            String site = mapper.writeValueAsString(response.body());
            JsonNode node = mapper.readTree(response.body());
            //List<JsonNode> nodesList =
            node = mapper.readTree(String.valueOf(node.findValues("headers").get(0)));
            System.out.println("NodesList (" + node.size() + " elements :" + node);
            for (JsonNode x : node
                 ) {
                //x.g
            }

            //System.out.println(nodesList.get(1));
            System.out.println();
            System.out.println("Site: " + site);



//                try {
//
//                    JsonNode nodes = mapper.readTree(response.body());
//                    nodes.findValues("headers");
//                    System.out.println("HEADERS:");
//                    System.out.println(nodes.get("headers"));
//
//                } catch (Exception e) {
//                    System.out.println("problem..");
//                }
//
//            }


            //ObjectMapper om = new

//            ObjectMapper objectMapper = new ObjectMapper();
//            //objectMapper.
//
//            try {
//                String jsonString = objectMapper.writeValueAsString(response.body());
//                System.out.println("JSON String: " + jsonString);
//                System.out.println(objectMapper.readValue("origin", String.class));
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                e.printStackTrace();
//            }

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
