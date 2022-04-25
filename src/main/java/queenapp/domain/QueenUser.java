package queenapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
public class QueenUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Getter @Setter
    @Column(nullable = false, unique = true)
    private java.lang.String username;

    @Getter @Setter
    @Column(nullable = false)
    private java.lang.String secret;

    @Column(nullable = false)
    @Getter
    private boolean isAdmin;

    protected QueenUser() {

    }

    public QueenUser(String username, String secret, boolean isAdmin) {
        this.username = username;
        this.secret = secret;
        this.isAdmin = isAdmin;
    }
}
