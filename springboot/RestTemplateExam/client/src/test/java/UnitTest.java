import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class UnitTest {


    @Test
    public void requestHello(){
        RestTemplate restTemplate = new RestTemplate();

        String fooResourceUrl = "http://localhost:8080/hello";
        ResponseEntity responseEntity =  restTemplate.getForEntity(fooResourceUrl , String.class);

        // <
        // 200
        // ,Hello World!
        // ,[Content-Type:"text/plain;charset=UTF-8", Content-Length:"12", Date:"Fri, 27 Mar 2020 02:13:41 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]
        // >
        System.out.println(responseEntity.toString());
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), is("Hello World!"));
        System.out.println(responseEntity.getHeaders());
    }

    @Test
    public void requestAge(){
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/age";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("yearOfBirth", "1991");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        System.out.println(responseEntity);
    }

    @Test
    public void requestCustomHeader(){
        String url = "http://localhost:8080/customHeader";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        assertThat(responseEntity.getBody(), is("Custom header set"));
        assertThat(responseEntity.getHeaders().get("Custom-Header").get(0), is("foo"));
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void requestHello2(){
        String url = "http://localhost:8080/hello2";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        assertThat(responseEntity.getBody(), is("Hello World!"));
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }






}
