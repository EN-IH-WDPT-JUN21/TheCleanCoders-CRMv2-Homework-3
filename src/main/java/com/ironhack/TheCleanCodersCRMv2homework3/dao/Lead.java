package com.ironhack.TheCleanCodersCRMv2homework3.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "leads") // "lead" is a keyword of SQL, it cannot be used as table name
public class Lead {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Column(name = "company_name")
    private String companyName;
    @ManyToOne
    @JoinColumn(name = "sales_rep")
    private SalesRep salesRep;



    public Lead(String name, String phoneNumber, String email, String companyName, SalesRep salesRep) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
        setSalesRep(salesRep);
    }


    public static void removeItem(Lead lead) {
        //
    }


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
