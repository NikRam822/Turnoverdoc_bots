package turnoverdoc.telegram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "passport_path")
    private String passportPath;

    @Column(name = "p45_path")
    private String p45Path;

    @Column(name = "p60_path")
    private String p60Path;

    @Column(name = "p80_path")
    private String p80Path;

    @Column(name = "contract_path")
    private String contractPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @OneToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name = "date")
    private Timestamp timestampDate;
}

