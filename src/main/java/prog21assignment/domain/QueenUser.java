package prog21assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
public class QueenUser {
    @Id
    @GeneratedValue
    @Column
    private long id;

    @Getter @Setter
    @Column(nullable = false)
    private String username;

    @Getter @Setter
    @Column(nullable = false)
    private String secret;

    protected QueenUser() {

    }
}
