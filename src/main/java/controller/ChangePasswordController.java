package controller;

import dto.changepassword.ChangePasswordDto;
import dto.member.MemberDto;
import dto.member.VipMemberDto;
import exception.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import controller.service.ChangeInfoService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/edit/changePwd")
public class ChangePasswordController {

    private ChangeInfoService changeInfoService;

    @Autowired
    public void setChangeInfoService(ChangeInfoService changeInfoService) {
        this.changeInfoService = changeInfoService;
    }

    @GetMapping
    public String form(
            @ModelAttribute("change") ChangePasswordDto pwdChange, HttpSession session) {
        MemberDto memberInfo = (MemberDto) session.getAttribute("memberInfo");
        if (memberInfo == null){
            return "redirect:myToday";
        }
        return "edit/changePwdForm";
    }

    @PostMapping
    public String submit(
            @ModelAttribute("change") ChangePasswordDto pwdChange, Errors errors, HttpSession session) {
        if (errors.hasErrors()) {
            return "edit/changePwdForm";
        }
        VipMemberDto memberInfo = (VipMemberDto) session.getAttribute("memberInfo");
        try {
            changeInfoService.changePwd(
                    memberInfo.getEmail(),
                    pwdChange.getCurrentPassword(),
                    pwdChange.getNewPassword());
            return "edit/changePwd";
        } catch (WrongIdPasswordException e) {
            errors.rejectValue("currentPassword", "notMatching");
            return "edit/changePwdForm";
        }
    }

}