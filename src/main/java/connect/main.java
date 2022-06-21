package connect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class main {

    static final String url = "http://94.198.50.185:7081/api/users";
    static RestTemplate restTemplate = new RestTemplate();
    static HttpHeaders headers = new HttpHeaders();
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws JsonProcessingException {

//--------------------get------------------------
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity(headers);
        headers.add("Cookie", getMethod(requestEntity));

//--------------------post------------------------
        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 25);

        requestEntity = new HttpEntity(user, headers);
        postMethod(requestEntity);

//--------------------put------------------------
        User userPut = new User();
        userPut.setId(3L);
        userPut.setName("Thomas");
        userPut.setLastName("Shelby");
        userPut.setAge((byte) 25);

        requestEntity = new HttpEntity(userPut, headers);
        putMethod(requestEntity);

//--------------------delete------------------------
        deleteMethod(requestEntity);

//--------------------result------------------------
        System.out.println("Ответ: " + sb);

    }

    private static String getMethod(HttpEntity entity) {
        ResponseEntity responseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                entity,
                List.class);

        HttpHeaders headers = responseEntity.getHeaders();
        String cookie = headers.getFirst(headers.SET_COOKIE);
        System.out.println(cookie);
        return cookie;
    }

    private static void postMethod(HttpEntity<String> entity) {


        ResponseEntity response = restTemplate.exchange(url,
                HttpMethod.POST,
                entity,
                String.class);
        String result = (String) response.getBody();
        sb.append(result);
    }

    private static void putMethod(HttpEntity<String> entity) {

        ResponseEntity response = restTemplate.exchange(url,
                HttpMethod.PUT,
                entity,
                String.class);
        String result = (String) response.getBody();
        sb.append(result);
    }

    private static void deleteMethod(HttpEntity<String> entity) {

        ResponseEntity response = restTemplate.exchange(url + "/3",
                HttpMethod.DELETE,
                entity,
                String.class);
        String result = (String) response.getBody();
        sb.append(result);
    }
}