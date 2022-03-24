package controller.service.vip;

import dao.weather.CityDao;
import dto.vipInfo.CityDto;
import dto.vipInfo.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetLocationCodeService {

    private CityDao cityDao;

    @Autowired
    public void setCityDao(CityDao cityDao){
        this.cityDao = cityDao;
    }

    public LocationDto getLocationCode(CityDto cityDto) {
        return cityDao.getLocationInfo(cityDto);
    }

    public LocationDto getLocationCode(Long locationCode){
        return cityDao.getLocationInfo(locationCode);
    }
}
