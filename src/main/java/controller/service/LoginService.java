package controller.service;

import dao.MemberDao;
import exception.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dto.member.MemberDto;

@Component
public class LoginService {

    private MemberDao memberDao;

    @Autowired
    public void setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public MemberDto getUserInfo(String id, String password) throws WrongIdPasswordException {
        MemberDto member = memberDao.login(id,password);
        if (member == null){
            throw new WrongIdPasswordException();
        }
        return member;
    }
}
