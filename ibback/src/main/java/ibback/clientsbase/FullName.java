package ibback.clientsbase;

import lombok.Getter;

@Getter
public class FullName {
    private String lastname;
    private String firstname;
    private String middlename;

    public FullName(String lastname, String firstname, String middlename) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
    }

    @Override
    public String toString() {
        return lastname + firstname + middlename;
    }

}
