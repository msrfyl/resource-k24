package com.msrfyl.k24.resource.entity.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msrfyl.k24.resource.AppContext;
import com.msrfyl.k24.resource.general.ConverterEnum;
import com.msrfyl.k24.resource.utility.Utility;
import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}, name = "uniqueEmail")
})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 256)
    String password;
    @Column(nullable = false, length = 100)
    String name;
    @Column(nullable = false, length = 15)
    String phoneNumber;
    @Column(nullable = false, length = 50)
    String email;
    @Column(nullable = false, length = 50)
    String numberKtp;

    @Column(nullable = false)
    @Convert(converter = GenderConverter.class)
    private Gender gender = Gender.MALE;

    @Column(length = 5000)
    String photo;

    public enum Gender { MALE, FEMALE, UNDEFINED }

    public Member() {
    }

    public Member(String password, String name, String phoneNumber, String email, String numberKtp, Gender gender) {
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.numberKtp = numberKtp;
        this.gender = gender;
    }

    public Member(String password, String name, String phoneNumber, String email, String numberKtp, Gender gender, String photo) {
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.numberKtp = numberKtp;
        this.gender = gender;
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberKtp() {
        return numberKtp;
    }

    public void setNumberKtp(String numberKtp) {
        this.numberKtp = numberKtp;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @PrePersist
    @PreUpdate
    private void beforeSaveData() {
        email = email.trim();
        password = AppContext.getBean(PasswordEncoder.class).encode(password);
        if (photo != null) {
            if (!photo.endsWith(".png")) {
                photo = new Utility().saveImagesToDirectory(photo, "member");
            }
        }
    }
}

@Converter
class GenderConverter extends ConverterEnum<Member.Gender> implements AttributeConverter<Member.Gender, String> {
    public GenderConverter() {
        super(Member.Gender.class);
    }
}
