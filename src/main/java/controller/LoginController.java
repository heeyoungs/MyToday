package controller;

import dto.member.VipMemberDto;
import dto.vipInfo.LocationDto;
import dto.vipInfo.WeatherDto;
import exception.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.vip.GetLocationCodeService;
import service.vip.GetWeatherInfoService;
import service.LoginService;
import dto.login.LoginDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;
    private GetLocationCodeService getLocationCodeService;
    private GetWeatherInfoService getWeatherInfoService;

    @Autowired
    public void setGetLocationCodeService(GetLocationCodeService getLocationCodeService) {
        this.getLocationCodeService = getLocationCodeService;
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setGetWeatherInfoService(GetWeatherInfoService getWeatherInfoService){
        this.getWeatherInfoService = getWeatherInfoService;
    }

    @GetMapping
    public String form(@ModelAttribute("login") LoginDto login, @CookieValue(value = "REMEMBER",required = false) Cookie rCookie) {
        if (rCookie !=null){
            login.setId(rCookie.getValue());
            login.setRememberId(true);
        }
        return "login/loginForm";
    }

    @PostMapping
    public String submit(@ModelAttribute("login") LoginDto login, Errors errors,
                         HttpSession session, HttpServletResponse response) {
        if (errors.hasErrors()) {
            return "login/loginForm";
        }
        try {
            VipMemberDto memberInfo = (VipMemberDto) loginService.getUserInfo(
                    login.getId(),
                    login.getPassword());

            session.setAttribute("memberInfo", memberInfo);

            if (memberInfo.getLocationCode() != null){
                LocationDto locationCode = getLocationCodeService.getLocationCode(memberInfo.getLocationCode());
                WeatherDto weatherInfo = getWeatherInfoService.getWeatherInfo(locationCode,memberInfo);
                session.setAttribute("weatherInfo", weatherInfo);
            }

            Cookie rememberCookie = new Cookie("REMEMBER", login.getId());
            rememberCookie.setPath("/");
            if (login.isRememberId()){
                rememberCookie.setMaxAge(60 * 60 * 24 * 30);
            }else{
                rememberCookie.setMaxAge(0);
            }
            response.addCookie(rememberCookie);

            return "myToday";
        } catch (WrongIdPasswordException e) {
            errors.reject("idPasswordNotMatching");
            return "login/loginForm";
        }
    }
}
