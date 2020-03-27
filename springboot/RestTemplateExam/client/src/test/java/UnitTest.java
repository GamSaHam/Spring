import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class UnitTest {

    public final static String fooResourceUrl = "http://localhost:8080/spring-rest/foos";

    @Test
    public void requestGetFoo(){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void getFoo(){

        RestTemplate restTemplate = new RestTemplate();

        Foo foo = restTemplate
                .getForObject(fooResourceUrl + "/1", Foo.class);

        assertThat(foo.getName(), is("Foo"));
        assertThat(foo.getId(), is(1L));

    }

    @Test
    public void getHeaders(){

        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = restTemplate.headForHeaders(fooResourceUrl);

        System.out.println(headers);

    }

    @Test
    public void postFoosObject(){

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Foo> request = new HttpEntity<>(new Foo(1L,"bar"));

        System.out.println(restTemplate.postForObject(fooResourceUrl, request, Foo.class));

    }

    @Test
    public void postForLocation(){
        RestTemplate restTemplate = new RestTemplate();


        HttpEntity<Foo> request = new HttpEntity<>(new Foo(1L, "bar"));
        URI location = restTemplate
                .postForLocation(fooResourceUrl+"/uri", request);

        System.out.println(location);

    }

    @Test
    public void postExchange(){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Foo> request = new HttpEntity<>(new Foo(1L, "bar"));
        ResponseEntity<Foo> response = restTemplate
                .exchange(fooResourceUrl+"/object", HttpMethod.POST, request, Foo.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        Foo foo = response.getBody();

        assertThat(foo.getName(), is("bar"));
    }

    @Test
    public void submitFormData(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("id", "1");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                fooResourceUrl+"/form", request , String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    }

    @Test
    public void getAllowOptionals(){
        RestTemplate restTemplate = new RestTemplate();

        Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(fooResourceUrl);

//        HttpMethod[] supportedMethods
//                = {HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};

        System.out.println(optionsForAllow);
    }

    @Test
    public void updateFoo(){
        RestTemplate restTemplate = new RestTemplate();

        Foo foo = new Foo(1L,"newName");
        foo.setId(2L);

        String resourceUrl =
                fooResourceUrl + '/' + 1;

        HttpEntity<Foo> request = new HttpEntity<>(foo);
        ResponseEntity responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.PUT, request, Foo.class);

        Foo responseFoo = (Foo)responseEntity.getBody();

        assertThat(responseFoo.getId(), is(2L));
    }

    public static RequestCallback requestCallback(final Foo updatedInstance) {
        return clientHttpRequest -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(clientHttpRequest.getBody(), updatedInstance);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.AUTHORIZATION, "Basic ");
        };
    }


    @Test
    public void postRequest(){
        RestTemplate restTemplate = new RestTemplate();

        Foo updatedInstance = new Foo(1L, "newName");
        updatedInstance.setId(2L);

        String resourceUrl =fooResourceUrl + '/' + 2;
        restTemplate.execute(
                resourceUrl,
                HttpMethod.PUT,
                requestCallback(updatedInstance),
                clientHttpResponse -> {
                    System.out.println(clientHttpResponse.getStatusCode());
                    System.out.println(clientHttpResponse.getHeaders());

                    return null;
                });
    }

    @Test
    public void deleteFoo(){
        RestTemplate restTemplate = new RestTemplate();

        String entityUrl = fooResourceUrl + "/" + 1L;

        restTemplate.delete(entityUrl);
    }


    private SimpleClientHttpRequestFactory getClientHttpRequestFactory()
    {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(1000);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(1000);
        return clientHttpRequestFactory;
    }

    @Test
    public void makeTimeout(){
        ClientHttpRequestFactory clientHttpRequestFactory = getClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        try {
            ResponseEntity<String> response
                    = restTemplate.exchange(fooResourceUrl + "/timeout", HttpMethod.GET, null, String.class);
        }catch (Exception e){
            System.out.println(e);
        }

    }















}
