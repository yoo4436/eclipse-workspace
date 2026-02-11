package tw.brad.spring04.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email, pwd;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    //-----------
    @OneToOne(
        mappedBy = "member",
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
    )
    private Profile profile;

    public void setProfile(Profile profile) {
        this.profile = profile;
        if(profile != null) {
            profile.setMember(this);
        }
    }


}
