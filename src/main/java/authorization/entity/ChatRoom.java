package authorization.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chat")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private boolean personal;

    private boolean systematic;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

    @ManyToMany(mappedBy = "chats")
    private Set<User> participants;
}
