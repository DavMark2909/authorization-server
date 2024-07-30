package authorization.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    private long id;

    private String content;

    private boolean requestBased;

    private String username;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private ChatRoom chat;
}