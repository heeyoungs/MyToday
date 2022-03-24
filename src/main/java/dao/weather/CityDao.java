package dao.weather;

import dto.vipInfo.CityDto;
import dto.vipInfo.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class CityDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CityDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<LocationDto> locationRowMapper =
            (rs, rowNum) -> {
                LocationDto locationDto = new LocationDto(
                        rs.getLong("locationCode"),
                        rs.getString("x"),
                        rs.getString("y"));
                return locationDto;
            };

    public LocationDto getLocationInfo(CityDto cityDto) {
        List<LocationDto> results = jdbcTemplate.query(
                "select locationCode,x,y from locationInfo where lev1 = ? and lev2= ? and lev3 = ?",
                locationRowMapper,
                cityDto.getBigCity(),
                cityDto.getMiddleCity(),
                cityDto.getSmallCity());
        return results.isEmpty() ? null : results.get(0);
    }

    public LocationDto getLocationInfo(Long locationCode) {
        List<LocationDto> results = jdbcTemplate.query(
                "select locationCode,x,y from locationInfo where locationCode=?",
                locationRowMapper,
                locationCode);
        return results.isEmpty() ? null : results.get(0);
    }

}
