package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Role;
import com.example.SpringDataJpa.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class ManyToManyUnidirectionalTest {

    private final UserRepository userRepository;

    @Autowired
    public ManyToManyUnidirectionalTest(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // JUnit test for save user with role
    @Test
    @DisplayName("JUnit test for save user with role")
    public void givenUserAndRoleObject_whenSaveUser_thenReturnUserObject() {

        // given - precondition or setup
        User user = User.builder()
                .firstName("Mohammad")
                .lastName("Ranjbar")
                .email("mohammad@gmail.com")
                .password("12356")
                .build();

        Role admin = Role.builder()
                .name("ROLE_ADMIN")
                .build();

        Role customer = Role.builder()
                .name("ROLE_CUSTOMER")
                .build();

        user.setRoles(Set.of(admin,customer));

        // when - action or the behavior that we are going test
        User savedUser = userRepository.save(user);

        // then - verify the output
        Assertions.assertThat(savedUser).isNotNull();

    }

    // JUnit test for update user with role
    @Test
    @DisplayName("JUnit test for update user with role")
    public void givenUserId_whenUpdateUser_thenReturnUserObject() {

        // given - precondition or setup
        Long id = 3L;
        User user = null;
        if(userRepository.findById(id).isPresent()){
            user = userRepository.findById(id).get();
            user.setFirstName("Hossein");
        }

        Role userRole = Role.builder()
                .name("ROLE_USE")
                .build();

        Objects.requireNonNull(user).getRoles().add(userRole);

        // when - action or the behavior that we are going test
        User savedUser = userRepository.save(user);

        // then - verify the output
        Assertions.assertThat(savedUser).isNotNull();

    }

    // JUnit test for get user
    @Test
    @DisplayName("JUnit test for get user")
    public void givenUserId_whenGetUser_thenReturnUserObject() {

        // given - precondition or setup
        Long id = 3L;

        // when - action or the behavior that we are going test
        User user = null;
        if(userRepository.findById(id).isPresent()){
            user = userRepository.findById(id).get();
        }

        // then - verify the output
        Assertions.assertThat(user).isNotNull();
        System.out.println(Objects.requireNonNull(user).getFirstName());
        user.getRoles().forEach(role -> System.out.println(role.getName()));

    }

    // JUnit test for delete user
    @Test
    @DisplayName("JUnit test for delete user")
    public void givenUserId_whenDeleteUser_thenDeletedUser() {

        // given - precondition or setup
        Long id = 3L;

        // when - action or the behavior that we are going test
        userRepository.deleteById(id);
        Optional<User> user = userRepository.findById(id);

        // then - verify the output
        Assertions.assertThat(user).isEmpty();

    }

}
