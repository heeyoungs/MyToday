package dao;

import dto.member.VipMemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import dto.member.MemberDto;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class MemberDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<VipMemberDto> memberRowMapper =
            (rs, rowNum) -> {
                VipMemberDto member = new VipMemberDto(
                        rs.getString("id"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("name"));
                if (rs.getLong("locationCode") != 0L){
                    member.setLocationCode(rs.getLong("locationCode"));
                }
                member.setNo(rs.getLong("no"));
                return member;
            };


    public MemberDto login(String id, String password){
        List<VipMemberDto> results = jdbcTemplate.query(
                "select * from Member where ID = ? and PASSWORD = ?",
                memberRowMapper, id,password);

        return results.isEmpty() ? null : results.get(0);
    }

    public boolean isMemberExist(VipMemberDto memberDto){
        List<VipMemberDto> results1 = jdbcTemplate.query(
                "select * from Member where ID = ?",
                memberRowMapper, memberDto.getId());

        List<VipMemberDto> results2 = jdbcTemplate.query(
                "select * from Member where EMAIL = ?",
                memberRowMapper, memberDto.getEmail());

        if (results1.isEmpty() && results2.isEmpty()){
            return false;
        }else return true;
    }

    public void insert(VipMemberDto member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(
                    "insert into MEMBER (ID, PASSWORD, EMAIL, NAME) values (?,?,?,?)",
                    new String[]{"no"});
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getName());
            return pstmt;
        }, keyHolder); // update() 메서드에 두 번째 파라미터로 keyHolder 를 전달
        Number keyValue = keyHolder.getKey();
        member.setNo(keyValue.longValue());
    }

    public VipMemberDto selectByEmail(String email) {
        List<VipMemberDto> results = jdbcTemplate.query(
                "select * from Member where Email = ?",
                memberRowMapper, email);

        return results.isEmpty() ? null : results.get(0);
    }

    public void update(VipMemberDto member) {
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement("update MEMBER set PASSWORD = ? where EMAIL = ?");
            pstmt.setString(1, member.getPassword());
            pstmt.setString(2, member.getEmail());
            return pstmt;
        });

    }

    public void locationUpdate(VipMemberDto vipMember){
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement("update MEMBER set LOCATIONCODE = ? WHERE NO=?");
            pstmt.setLong(1,vipMember.getLocationCode());
            pstmt.setLong(2,vipMember.getNo());
            return pstmt;
        });
    }

}
