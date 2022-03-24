package service;

import dao.MemberDao;
import dto.member.VipMemberDto;
import exception.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MemberRegisterService {

    private MemberDao memberDao;

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Transactional
    public Long register(VipMemberDto memberDto) {
        if (memberDao.isMemberExist(memberDto)) {
            throw new DuplicateMemberException("duplicate " + memberDto.getEmail());
        }else{
            VipMemberDto newMember = new VipMemberDto(
                    memberDto.getId(), memberDto.getPassword(),memberDto.getEmail(),memberDto.getName()
            );
            memberDao.insert(newMember);
            return newMember.getNo();
        }
    }
}
