package controller.service;

import dao.MemberDao;
import dto.member.VipMemberDto;
import exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ChangeInfoService {

    private MemberDao memberDao;

    @Autowired
    public void setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    @Transactional
    public void changePwd(String email, String oldPwd, String newPwd) {
        VipMemberDto member = memberDao.selectByEmail(email);
        if (member == null)
            throw new MemberNotFoundException();

        member.changePassword(oldPwd, newPwd);
        memberDao.update(member);
    }

}



