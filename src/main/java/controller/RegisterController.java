package controller;

import dto.member.VipMemberDto;
import exception.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.MemberRegisterService;
import dto.member.MemberDto;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private MemberRegisterService memberRegisterService;

    @Autowired
    public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

    @RequestMapping("/step1")
    public String handleStep1() {
        return "register/step1";
    }

    @PostMapping("/step2")
    public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree, @ModelAttribute("member") MemberDto member) {
        if (!agree) {
            return "register/step1";
        }
        return "register/step2";
    }

    @PostMapping("/step3")
    public String handleStep3(@ModelAttribute("member") @Valid VipMemberDto member , Errors errors) {
        if (errors.hasErrors())
            return "register/step2";
        try {
            memberRegisterService.register(member);
            return "register/step3";
        } catch (DuplicateMemberException ex) {
            errors.rejectValue("email", "duplicate");
            errors.rejectValue("id","duplicate");
            return "register/step2";
        }
    }

    @GetMapping({"/step2","/step3"})
    public String handleStep2Get() {
        return "redirect:step1";
    }

}
