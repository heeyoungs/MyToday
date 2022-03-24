package controller.vip;

import dto.member.VipMemberDto;
import dto.vipInfo.CityDto;
import dto.vipInfo.LocationDto;
import dto.vipInfo.WeatherDto;
import exception.LocationCodeNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import controller.service.vip.GetLocationCodeService;
import controller.service.vip.GetWeatherInfoService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/weatherRegister")
public class WeatherRegisterController {

    private GetWeatherInfoService getWeatherInfoService;
    private GetLocationCodeService getLocationCodeService;

    @Autowired
    public void setGetLocationCodeService(GetLocationCodeService getLocationCodeService) {
        this.getLocationCodeService = getLocationCodeService;
    }

    @Autowired
    public void setGetWeatherInfoService(GetWeatherInfoService getWeatherInfoService) {
        this.getWeatherInfoService = getWeatherInfoService;
    }

    @GetMapping("/step1")
    public String handleStep1(@ModelAttribute("city") CityDto cityDto) {
        return "weatherRegister/step1";
    }

    // 도시 등록
    @PostMapping("/step2")
    @Transactional
    public String handleStep2(@ModelAttribute("city") @Valid CityDto cityDto, Errors errors, HttpSession session) {
        if (errors.hasErrors())
            return "weatherRegister/step1";
        try {
            VipMemberDto memberInfo = (VipMemberDto) session.getAttribute("memberInfo");

            LocationDto locationCode = getLocationCodeService.getLocationCode(cityDto); // location 정보를 가져옴
            if (locationCode == null) throw new LocationCodeNotFountException();

            WeatherDto weatherInfo = getWeatherInfoService.getWeatherInfo(locationCode, memberInfo); // 날씨 정보를 가져옴
            session.setAttribute("weatherInfo", weatherInfo);

            memberInfo.setLocationCode(locationCode.getLocationCode());
            session.setAttribute("memberInfo", memberInfo);
            return "weatherRegister/step2";
        } catch (LocationCodeNotFountException e) {
            errors.reject("locationCodeError");
            return "weatherRegister/step1";
        }
    }

}
