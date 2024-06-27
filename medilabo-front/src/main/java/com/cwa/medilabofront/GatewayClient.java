package com.cwa.medilabofront;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "gateway", url = "${gateway.server-url}")
public interface GatewayClient {

    @GetMapping("/getPatients")
    List<Patient> getPatients();

    @PostMapping("/createPatient")
    Patient createPatient(@RequestBody Patient patient);

    @GetMapping("/getPatient/{id}")
    Patient getPatient(@PathVariable("id") Long id);

    @PutMapping("/updatePatient/{id}")
    Patient updatePatient(@RequestBody Patient patient, @PathVariable("id") Long id);

    @DeleteMapping("/deletePatient/{id}")
    Patient deletePatient(@PathVariable("id") Long id);
}
