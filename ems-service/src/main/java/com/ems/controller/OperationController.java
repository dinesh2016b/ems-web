package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.exception.EMSException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.model.EmployeeRequest;
import com.ems.mq.JmsUtils;
//import com.ems.mq.configuration.JMSUtil;
import com.ems.util.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "https://localhost:8080", maxAge = 3600, allowCredentials = "true",
        allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@Slf4j
public class OperationController {

   // @Value("${activemq.destination}")
   // private String destination;

    @Autowired
    private JmsUtils jmsUtils;

    @PostMapping(path = ApplicationConstants.ENDPOINT_SEND_DEPARTMENT_CHANGE_REQUEST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendDepartmentChangeRequest(@RequestBody EmployeeRequest employeeRequest)
            throws EMSException, ResourceNotFoundException {

        log.info("------------> EMS_REQUEST_QUEUE - sendDepartmentChangeRequest()");
        
        jmsUtils.sendMessage("EMS_REQUEST_QUEUE", "EMS MQ Testing");
        return new ResponseEntity<String>("Success", new HttpHeaders(), HttpStatus.OK);
    }
}