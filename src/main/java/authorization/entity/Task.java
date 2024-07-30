package authorization.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;
    private String title;

    private String priority;
    private LocalDateTime issuedTime;
    private LocalDateTime dueTime;

    @ManyToOne
    @JoinColumn(name = "issuer_id", nullable = false)
    private User issuer;

    @ManyToMany(mappedBy = "tasks")
    private List<User> receivers;

    @ManyToOne
    @JoinColumn(name = "performer_id")
    private User performer;

}
