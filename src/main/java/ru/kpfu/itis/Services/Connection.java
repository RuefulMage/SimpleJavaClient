package ru.kpfu.itis.Services;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.Models.MarkerDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Connection {
    private ObjectMapper mapper;
    private RestTemplate restTemplate;
    public Connection(){
        mapper = new ObjectMapper();
        restTemplate = new RestTemplate();
    }

    public List<MarkerDTO> getAllMarkers() throws IOException{
        String MarkersDTOString = restTemplate.getForObject("http://localhost:8080/markers", String.class);
        MarkerDTO[] markersList = mapper.readValue(MarkersDTOString, MarkerDTO[].class);

        List<MarkerDTO> list = new ArrayList<MarkerDTO>(Arrays.asList(markersList));

        System.out.println(list);
        return list;
    }


    public List<MarkerDTO> getMyMarkers() throws IOException{
        String MarkersDTOString = restTemplate.getForObject("http://localhost:8080/markers", String.class);
        MarkerDTO[] markersList = mapper.readValue(MarkersDTOString, MarkerDTO[].class);

        List<MarkerDTO> list = new ArrayList<MarkerDTO>(Arrays.asList(markersList));
        return list;
    }

    public void saveMarker(String name) {
        final String uri = "http://localhost:8080/markers/add";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.postForObject(uri, name, String.class);
    }

//    public String login(String userName, String password){
//        UserDTO userDTO = new UserDTO(userName, password);
//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//        restTemplate.postForObject(uri + "login", userDTO, String.class);
//
//    }


}
