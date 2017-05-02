package Model;

import java.io.Serializable;

/**
 * Created by Andrei on 30/04/2017.
 */
public class Person implements Serializable {

    private int id;
    private String name;
    private String username;
    private String password;
    private boolean logat;


    public Person(int id,String name,String username,String password)
    {
        this.id=id;
        this.username=username;
        this.name =name;
        this.password =password;
        this.logat=false;
    }

    public Person(String username)
    {
        this.username=username;
        this.logat=false;
    }

    public int hashCode()
    {
        return username.charAt(0)*username.charAt(1)*username.length();
    }

    public boolean equals(Object object)
    {
        Person person=(Person)object;
        if(person.username.equals(this.username))
            return true;
        return false;
    }


    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public int getId() {
        return id;
    }

    public boolean isLogat() {
        return logat;
    }

    public void setLogat(boolean logat) {
        this.logat = logat;
    }
}
