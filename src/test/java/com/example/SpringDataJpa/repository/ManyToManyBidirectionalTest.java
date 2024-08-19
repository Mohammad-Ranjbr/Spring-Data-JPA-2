package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Role;
import com.example.SpringDataJpa.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.Set;

@SpringBootTest
public class ManyToManyBidirectionalTest {

    private final RoleRepository roleRepository;

    @Autowired
    public ManyToManyBidirectionalTest(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    // JUnit test for save role
    @Test
    @DisplayName("JUnit test for save role")
    public void givenUserAndRoleObject_whenSaveUser_thenReturnUserObject() {

        // given - precondition or setup
        User user1 = User.builder()
                .firstName("admin")
                .lastName("admin")
                .email("admin@gmail.com")
                .password("admin")
                .build();

        User user2 = User.builder()
                .firstName("user")
                .lastName("user")
                .email("user@gmail.com")
                .password("user")
                .build();

        Role adminRole = Role.builder()
                .name("ROLE_ADMIN")
                .build();

        adminRole.setUsers(Set.of(user1,user2));

        user1.setRoles(Set.of(adminRole));
        user2.setRoles(Set.of(adminRole));

        // when - action or the behavior that we are going test
        Role savedRole = roleRepository.save(adminRole);

        // then - verify the output
        Assertions.assertThat(savedRole).isNotNull();

    }

    // JUnit test for get role
    @Test
    @DisplayName("JUnit test for get user")
    public void givenRoleId_whenGetUser_thenReturnUserObject() {

        // given - precondition or setup
        Long id = 8L;

        // when - action or the behavior that we are going test
        Role role = null;
        if(roleRepository.findById(id).isPresent()){
            role = roleRepository.findById(id).get();
        }

        // then - verify the output
        Assertions.assertThat(role).isNotNull();
        System.out.println(Objects.requireNonNull(role).getName());
        role.getUsers().forEach(user -> System.out.println(user.getEmail()));

    }

}
