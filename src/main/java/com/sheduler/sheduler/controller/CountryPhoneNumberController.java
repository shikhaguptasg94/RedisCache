package com.sheduler.sheduler.controller;

import com.sheduler.sheduler.model.*;
import com.sheduler.sheduler.services.UserServise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("/redis/demo")
public class CountryPhoneNumberController {

    @Autowired
    private UserServise userServise;

    @PostMapping("/add")
    public GanericResponse addUser(@RequestBody AddUserDTO addUserDTO){
        return userServise.addUser(addUserDTO);
    }
    @PostMapping
    public GanericResponse authenticateUser(@RequestBody AuthUserDto authUserDto){
        return  userServise.authUser(authUserDto);
    }

    @GetMapping("/phonenumber/{phone}/{country}")
    public BaseResponse getPhoneNum(@PathVariable String phone, @PathVariable String country){
        String responseBody = null;
        BaseResponse baseResponse = new BaseResponse();
        try{
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CountryCodeResponse> response = restTemplate.getForEntity("https://jsonmock.hackerrank.com/api/countries?name=" + country, CountryCodeResponse.class);
            log.info("country response::"+response);
            CountryCodeResponse body = response.getBody();
            responseBody = body.getData().get(0).getCallingCodes().get(0) + " " + phone;
            baseResponse.setData(responseBody);
            baseResponse.setSuccess(true);
        }catch (Exception e){
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(100);
            baseResponse.setErrorMessage(e.getMessage());
        }
        return baseResponse;
    }

    @GetMapping("/testHttpResponse")
    public ResponseEntity<String> testHttpResponse(){
        return new ResponseEntity<>("Maulik", HttpStatus.ACCEPTED);
    }
}
