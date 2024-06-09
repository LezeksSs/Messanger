package com.example.MessangerServer;

import com.example.MessangerServer.entity.User;
import com.example.MessangerServer.repository_dao.UserRepository;
import com.example.MessangerServer.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetByNickname_UserFound() {
        User expectedUser = new User();
        expectedUser.setNickname("testuser");

        when(userRepository.findByNickname("testuser")).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.getByNickname("testuser");

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetByNickname_UserNotFound() {
        when(userRepository.findByNickname(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.getByNickname("nonexistentuser"));
    }

    @Test
    public void testCreateUser_UserCreated() {
        User expectedUser = new User();
        expectedUser.setNickname("existuser");
        expectedUser.setEmail("existuser@mail.com");

        when(userRepository.existsByNickname(expectedUser.getNickname())).thenReturn(false);
        when(userRepository.existsByEmail(expectedUser.getEmail())).thenReturn(true);
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        assertThat(userService.createUser(expectedUser)).isEqualTo(expectedUser);

//        assertThrows(RuntimeException.class, () -> userService.createUser(expectedUser));

    }
}
