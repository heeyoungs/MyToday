package dto.member;

public class VipMemberDto extends MemberDto {

    protected Long locationCode;

    public VipMemberDto(String id, String password, String email, String name) {
        super(id, password, email, name);
    }

    public Long getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(Long locationCode) {
        this.locationCode = locationCode;
    }
}
