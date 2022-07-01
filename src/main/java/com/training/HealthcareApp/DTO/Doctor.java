package com.training.HealthcareApp.DTO;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Data
@Entity
//@Table(name="doctor")
@NoArgsConstructor
@AllArgsConstructor

public final class Doctor implements UserDetails {

    @Id
    @GenericGenerator(name = "id", strategy = "com.training.HealthcareApp.Misc.IdGenerator")
    @GeneratedValue(generator = "id")
    private String id;
    private String email;
    private String password;
    private String name;
    private String gender;

    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String qualification;
    private String speciality;
    private String experience;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_DOCTOR"));
        return authorities;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
