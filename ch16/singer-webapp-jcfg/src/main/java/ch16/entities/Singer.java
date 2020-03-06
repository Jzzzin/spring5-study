package ch16.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "singer")
public class Singer implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @NotBlank(message = "{validation.firstName.NotBlank.message}")
    @Size(min = 3, max = 60, message = "{validation.firstName.Size.message}")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotBlank(message = "{validation.lastName.NotBlank.message}")
    @Size(min = 1, max = 40, message = "{validation.lastName.Size.message}")
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "PHOTO")
    private byte[] photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Transient
    public String getBirthDateString() {
        String birthDateString = "";
        if (birthDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            birthDateString = sdf.format(birthDate);
        }
        return birthDateString;
    }

    @Override
    public String toString() {
        return "Singer Id: " + id + ", First name: " + firstName
                + ", Last name: " + lastName + ", Birthday: " + birthDate
                + ", Description: " + description;
    }
}
