package tw.brad.spring03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import tw.brad.spring03.entity.Member;
import tw.brad.spring03.repository.MemberRepository;
import tw.brad.spring03.util.BCrypt;

@Service
public class MemberService {
    @Autowired
    private MemberRepository repository;

    public boolean checkEmail(String email) {
        return repository.existsByEmail(email);
    }

    public boolean register(Member member) {
        if (repository.existsByEmail(member.getEmail())) {
            return false;
        }

        member.setPw(BCrypt.hashpw(member.getPw(), BCrypt.gensalt()));
        Member m = repository.save(member);
        System.out.println(m.getId());
        return m != null;
    }

    public boolean login(String email, String pw) {
        Member member = repository.findByEmail(email).orElse(null);
        if (member != null && BCrypt.checkpw(pw, member.getPw())){
            return true;
        }
        return false;
    }

    public boolean loginV2(String email, String pw) {
        Member member = new Member();
        member.setEmail(email);
        Example<Member> ex = Example.of(member);
        if (repository.exists(ex)) {
            List<Member> members = repository.findAll(ex);
            Member dbMember = members.get(0);
            if ( BCrypt.checkpw(pw, dbMember.getPw())) {
                return true;
            }
        }
        return false;
    }
}
