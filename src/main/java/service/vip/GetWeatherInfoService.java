package service.vip;

import api.wheatherapi.WeatherAPI;
import dao.MemberDao;
import dao.weather.CityDao;
import dao.weather.WeatherDao;
import dto.member.VipMemberDto;
import dto.vipInfo.CityDto;
import dto.vipInfo.LocationDto;
import dto.vipInfo.WeatherDto;
import exception.LocationCodeNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetWeatherInfoService {

    private MemberDao memberDao;
    private WeatherDao weatherDao;
    private WeatherAPI weatherAPI;

    @Autowired
    public void setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    @Autowired
    public void setWeatherDao(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    @Autowired
    public void setWeatherAPI(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    @Transactional
    public WeatherDto getWeatherInfo(LocationDto locationCode, VipMemberDto member) {
        WeatherDto weatherInfo = weatherAPI.useApi(locationCode); // api 를 사용해 weather 객체를 불러옴
        try {
            // 위치 코드에 대한 날씨 값을 테이블에 저장
            if (!isWeatherInfoInTable(weatherInfo.getLocationCode())){ // 재설정
                setWeatherInfo(weatherInfo);
            }else{ // 로그인
                updateWeatherInfo(weatherInfo);
            }
            // 위치 정보를 등록
            if (member.getLocationCode() == null){
                member.setLocationCode(locationCode.getLocationCode());
                memberDao.locationUpdate(member);
            }
            return weatherInfo;
        }catch (LocationCodeNotFountException e){
            throw new LocationCodeNotFountException();
        }
    }

    private void setWeatherInfo(WeatherDto weatherDto) {
        weatherDao.insertWeatherInfo(weatherDto);
    }

    private void updateWeatherInfo(WeatherDto weatherDto) {
        weatherDao.updateWeatherInfo(weatherDto);
    }

    private boolean isWeatherInfoInTable(Long locationCode){
        return weatherDao.isWeatherInfoInTable(locationCode);
    }

}
