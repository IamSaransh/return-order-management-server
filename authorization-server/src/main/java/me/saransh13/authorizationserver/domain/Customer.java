package me.saransh13.authorizationserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;
import java.io.Serializable;
/**
 * @author saransh
 */

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements  Serializable {

    @Id @NonNull
    @Column(updatable = false, nullable = false)
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String contactNumber;
    @NonNull
    private String password;

    private boolean isEnabled;


}
