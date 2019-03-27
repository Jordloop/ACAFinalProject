package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserAccount
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String firstName;
    private String lastName;
    private String email;
    private String state;
    private String city;
    private String bio;
    private Byte[] picture;

    public int getUserAccountId()
    {
        return userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getState()
    {
        return state;
    }

    public String getCity()
    {
        return city;
    }

    public String getBio()
    {
        return bio;
    }

    public Byte[] getPicture()
    {
        return picture;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public String getCityState()
    {
        return city + ", " + state;
    }
}
