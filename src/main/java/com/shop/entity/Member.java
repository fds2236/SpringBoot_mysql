package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter @ToString
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true) // 유일한 값이 와야함
    private String email;
    private String password;
    private String address;
    @Enumerated(EnumType.STRING) // DB에 저장될 때 문자열로 저장됨
    private Role role;

    // Member 엔티티를 생성하는 메소드. Member 엔티티에 회원을 생성하는 메소드를 만들어서 관리하면
    // 코드가 변경되더라도 한 군데만 수정하면 되는 이점이 있음.
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }
}
