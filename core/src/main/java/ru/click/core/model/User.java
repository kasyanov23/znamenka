package ru.click.core.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "users", schema = "znamenka")
@NamedEntityGraph(name = "user.graph",
        attributeNodes = {
                @NamedAttributeNode("authorities"),
                @NamedAttributeNode("trainer")
        })
@Getter
@Setter
public class User implements UserDetails {

    private static final long serialVersionUID = -8376436720803866112L;

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    private List<Role> authorities;

    @Column(name = "name")
    private String name;

    @PostLoad
    private void setNameUser() {
        if (getName() == null) {
            this.setName(getTrainer().getName());
        }
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
}
