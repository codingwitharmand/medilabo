package com.cwa.medilabofront;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final GatewayClient gatewayClient;
    private static final String REDIRECT = "redirect:/";

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("patients", gatewayClient.getPatients());
        return "home";
    }

    @GetMapping("/patients/add")
    public String addPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute("patient") Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addPatient";
        }
        gatewayClient.createPatient(patient);
        return REDIRECT;
    }

    @GetMapping("/patients/edit/{id}")
    public String editPatient(Model model, @PathVariable Long id) {
        Patient patient = gatewayClient.getPatient(id);
        model.addAttribute("patient", patient);
        return "editPatient";
    }

    @PostMapping("/patients/edit/{id}")
    public String editPatient(@ModelAttribute("patient") Patient patient, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return "editPatient";
        }
        gatewayClient.updatePatient(patient, id);
        return REDIRECT;
    }

    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        gatewayClient.deletePatient(id);
        return REDIRECT;
    }
}
