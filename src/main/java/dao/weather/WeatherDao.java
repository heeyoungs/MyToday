package dao.weather;

import dto.vipInfo.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class WeatherDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WeatherDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<WeatherDto> weatherRowMapper =
            (rs, rowNum) -> {
                WeatherDto weatherDto = new WeatherDto();
                weatherDto.setLocationCode(rs.getLong("locationCode"));
                weatherDto.setPTY(rs.getDouble("pty"));
                weatherDto.setREH(rs.getDouble("reh"));
                weatherDto.setRN1(rs.getDouble("rn1"));
                weatherDto.setT1H(rs.getDouble("t1h"));
                weatherDto.setUUU(rs.getDouble("uuu"));
                weatherDto.setVVV(rs.getDouble("vvv"));
                weatherDto.setVEC(rs.getDouble("vec"));
                weatherDto.setWSD(rs.getDouble("wsd"));
                return weatherDto;
            };

    public void insertWeatherInfo(WeatherDto weatherDto) {
        jdbcTemplate.update("insert into member_location values (?,?,?,?,?,?,?,?,?)",
                weatherDto.getLocationCode(),
                weatherDto.getPTY(),
                weatherDto.getREH(),
                weatherDto.getRN1(),
                weatherDto.getTH1(),
                weatherDto.getUUU(),
                weatherDto.getVVV(),
                weatherDto.getVEC(),
                weatherDto.getWSD()
        );
    }

    public void updateWeatherInfo(WeatherDto weatherDto) {
        jdbcTemplate.update("update member_location set pty = ?, reh =?,rn1 =?,t1h=?,uuu=?,vvv=?,vec=?,wsd=? where locationCode = ?",
                weatherDto.getPTY(),
                weatherDto.getREH(),
                weatherDto.getRN1(),
                weatherDto.getTH1(),
                weatherDto.getUUU(),
                weatherDto.getVVV(),
                weatherDto.getVEC(),
                weatherDto.getWSD(),
                weatherDto.getLocationCode()
        );

    }

    public boolean isWeatherInfoInTable(Long locationCode){
        List<WeatherDto> results = jdbcTemplate.query(
                "select * from member_location where locationCode = ?",
                weatherRowMapper,locationCode
        );
        return !results.isEmpty();
    }
}
