package com.example.spring1.apis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring1.dto.Member;
import com.example.spring1.dto.MemberResponse;
import com.example.spring1.utils.BCrypt;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/brad09")
public class Brad09 {
    @Autowired
	private NamedParameterJdbcTemplate jdbc;

    @PostMapping("/test1")
    public void test1(@RequestBody Member member){
        System.out.println(member.getEmail());
        System.out.println(member.getPw());
        System.out.println(member.getName());
        
    }

    @PostMapping(value= {"/members", "/members/{isGetId}"})
    public MemberResponse addMember(@RequestBody @Valid Member member,
            @PathVariable(required = false) Boolean isGetId) {
        // System.out.println(member.getEmail());
        // System.out.println(member.getPw());
        // System.out.println(member.getName());
        
        isGetId = isGetId == null?false:isGetId;

        String sql = """
                insert into member
                    (email, pw, name)
                values
                    (:email, :pw, :name)
                """;
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("email", member.getEmail());
        map.put("pw",BCrypt.hashpw(member.getPw(), BCrypt.gensalt()));
        map.put("name", member.getName());
        
        // int n = jdbc.update(sql, map);
        // System.out.println(n);

        KeyHolder keyHolder = new  GeneratedKeyHolder();
        int n = jdbc.update(sql, new MapSqlParameterSource(map), keyHolder);
        System.out.println(n);
        System.out.println(keyHolder.getKey().intValue());

        MemberResponse response = new MemberResponse();
        if (n > 0) {
            int lastId = keyHolder.getKey().intValue();
            if (isGetId) {
                member.setId(lastId);
                response.setInsertId(lastId);
            }else
            

            response.setError(0);
            response.setMessage("新增成功");
            response.setMember(member);

        }else {
            response.setError(-1);
            response.setMessage("新增失敗");
        }

        return  response;
    }

    @PostMapping("/members/ss")
    public void addMembers(@RequestBody List<Member> members) {
        String sql = """
                insert into member
                    (email, pw, name)
                values
                    (:email, :pw, :name)
                """;

        
    }
}
