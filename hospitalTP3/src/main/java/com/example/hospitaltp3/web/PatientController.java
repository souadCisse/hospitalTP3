package com.example.hospitaltp3.web;


import com.example.hospitaltp3.enteties.Patient;
import com.example.hospitaltp3.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0")int p,
                        @RequestParam(name = "size",defaultValue = "4")int s,
                        @RequestParam(name = "keyword",defaultValue = "")String kw){
        Page<Patient> patientPage= patientRepository.findByNomContains(kw,PageRequest.of(p,s));
        model.addAttribute("listPatients",patientPage.getContent());
        model.addAttribute("pages",new int[patientPage.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",kw);
        return"patients";
    }
    @GetMapping("/admin/delete")
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }

    @GetMapping("/admin/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }
    @PostMapping("/admin/save")
    public String save(Model model, @Valid Patient patient,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue ="" ) String keyword,
                       @RequestParam(defaultValue = "0") int page){
        if(bindingResult.hasErrors())return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editPatient")
    public String editPatients(Model model, Long id,String keyword,int page){
        Patient patient=patientRepository.findById(id).get();
        model.addAttribute("patient",patient);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "editPatients";
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
