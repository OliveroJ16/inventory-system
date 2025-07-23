package com.inventory.inventorySystem.repositories;

import com.inventory.inventorySystem.enums.UserRole;
import com.inventory.inventorySystem.model.User;
import com.inventory.inventorySystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnMatchingUser() {
        // Arrange – Create and save a test user
        User testUser = new User(
                "Test Name",
                "Test Description",
                "test@gmail.com",
                UserRole.CASHIER,
                "123",
                true
        );

        User savedUser = userRepository.save(testUser);

        // Act – Search for the user by email
        Optional<User> retrievedUserOptional = userRepository.findByEmail(savedUser.getEmail());

        // Assert – Check that a user is returned
        assertTrue(
                retrievedUserOptional.isPresent(),
                "Expected user to be present in the database, but none was found."
        );

        User retrievedUser = retrievedUserOptional.get();

        // Assert – Verify that the email matches
        assertEquals(
                savedUser.getEmail(),
                retrievedUser.getEmail(),
                "Expected email to match the one used to save the user."
        );
    }

}
