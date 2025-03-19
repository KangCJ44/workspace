package com.skala.springbootaopsample.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AOPService {

    public void doAction() {
        log.debug("###### doAction call");
        System.out.println("--> Executing performAction method in ExampleService.");
    }
}