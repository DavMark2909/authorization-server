package authorization.service;

import authorization.dto.AuthMessage;
import authorization.dto.CreateUserDto;
import authorization.entity.ChatRoom;
import authorization.entity.Message;
import authorization.entity.Role;
import authorization.entity.User;
import authorization.exception.MyException;
import authorization.repository.ChatRepository;
import authorization.repository.MessageRepository;
import authorization.repository.RoleRepository;
import authorization.repository.UserRepository;
import authorization.security.SecurityUser;
import authorization.security.UserToSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(UserToSecurityUser::from).orElseThrow();
    }

    @Transactional
    public AuthMessage createUser(CreateUserDto dto) {
        if (userExists(dto.username()))
            return new AuthMessage(HttpStatus.BAD_REQUEST, "User with username " + dto.username() + " already exists");
        User user = new User();
        user.setName(dto.name());
        user.setUsername(dto.username());
        user.setLastName(dto.lastname());
        user.setFullname(String.format("%s %s", dto.name(), dto.lastname()));
        user.setPassword(passwordEncoder.encode(dto.password()));
        Set<Role> roles = new HashSet<>();
        for (String name : dto.roles()){
//            Role role = roleRepository.findByName(name).orElseThrow(() -> new NoRoleException(name));
//            roles.add(role);
            Role role = roleRepository.findByName(name).orElseGet(() -> {
                Role newRole = new Role();
                newRole.setName(name);
                return roleRepository.save(newRole);
            });
            roles.add(role);
        }
        ChatRoom chat = new ChatRoom();
        chat.setPersonal(true);
        chat.setSystematic(true);
        chat.setName(String.format("%s_system", dto.username()));
        ChatRoom chatRoom = chatRepository.save(chat);
        Message greetingMsg = new Message();
        greetingMsg.setRequestBased(false);
        greetingMsg.setContent("Welcome to the best application in the world");
        greetingMsg.setDate(LocalDateTime.now());
        greetingMsg.setUsername("system");
        greetingMsg.setChat(chatRoom);
        messageRepository.save(greetingMsg);
        user.setRoles(roles);
        user.setChats(Set.of(chatRoom));
        user.setChatId(chatRoom.getId());
        userRepository.save(user);
        return new AuthMessage(HttpStatus.CREATED, "User was created");
    }

    private boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
