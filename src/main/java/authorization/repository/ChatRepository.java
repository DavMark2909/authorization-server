package authorization.repository;

import authorization.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatRoom, Integer> {
}
