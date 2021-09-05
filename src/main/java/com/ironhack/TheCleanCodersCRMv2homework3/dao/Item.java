package com.ironhack.TheCleanCodersCRMv2homework3.dao;

import com.ironhack.TheCleanCodersCRMv2homework3.output.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@MappedSuperclass
public abstract class Item {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @Column(name = "company_name")
    private String companyName;

    // Constructor

    public Item(String name, String email, String companyName, String phoneNumber) {
        setName(name);
        setEmail(email);
        setCompanyName(companyName);
        setPhoneNumber(phoneNumber);
    }

    // Setters

    public void setEmail(String email) {
        try{
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher m = p.matcher(email);
            boolean matchFound = m.matches();
            if (matchFound) {
                this.email = email;
            } else {
                throw new InputMismatchException();
            }
        } catch(InputMismatchException ex){
            System.out.println("Wrong email address");
        }
    }

}
