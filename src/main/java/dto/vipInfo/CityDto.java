package dto.vipInfo;

import javax.validation.constraints.NotBlank;

public class CityDto {

    @NotBlank
    private String bigCity;
    private String middleCity;
    private String smallCity;

    public CityDto(String bigCity, String middleCity, String smallCity) {
        this.bigCity = bigCity;
        this.middleCity = middleCity;
        this.smallCity = smallCity;
    }

    public void setSmallCity(String smallCity) {
        this.smallCity = smallCity;
    }

    public String getBigCity() {
        return bigCity;
    }

    public void setBigCity(String bigCity) {
        this.bigCity = bigCity;
    }

    public String getMiddleCity() {
        return middleCity;
    }

    public void setMiddleCity(String middleCity) {
        this.middleCity = middleCity;
    }

    public String getSmallCity() {
        return smallCity;
    }
}
