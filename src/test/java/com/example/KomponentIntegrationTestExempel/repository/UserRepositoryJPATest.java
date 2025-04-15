package com.example.KomponentIntegrationTestExempel.repository;

import com.example.KomponentIntegrationTestExempel.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
//@Rollback(value = false)
class UserRepositoryJPATest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail(){
        //arrange
        User user = new User(null, "Bill","bill@mail.com");
        entityManager.persistAndFlush(user);
//        entityManager.persist(user);
//        //massa annat
//        entityManager.flush();

        //act
        Optional<User> foundUser = userRepository.findByEmail("bill@mail.com");

        //assert
        assertTrue(foundUser.isPresent());
        assertEquals("Bill", foundUser.get().getName());


    }

}