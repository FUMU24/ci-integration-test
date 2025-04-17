package com.example.KomponentIntegrationTestExempel.repository;

import com.example.KomponentIntegrationTestExempel.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
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
        User user = new User(null, "Test","test@mail.com");
        entityManager.persistAndFlush(user);
//        entityManager.persist(user);
//        //massa annat
//        entityManager.flush();

        //act
        Optional<User> foundUser = userRepository.findByEmail("test@mail.com");

        //assert
        assertTrue(foundUser.isPresent());
        assertEquals("Test", foundUser.get().getName());

    }

    @Test
    public void testFindByName(){
        //arrange
        User user = new User(null, "Test","test@mail.com");
        entityManager.persistAndFlush(user);

        //act
        Optional<User> foundUser = userRepository.findByName("Test");

        //assert
        assertTrue(foundUser.isPresent());
        assertEquals("test@mail.com", foundUser.get().getEmail());
    }

    @Test
    public void testFindAll(){
        //arrange
        User user1 = new User(null, "Test1","test1@mail.com");
        User user2 = new User(null, "Test2","test2@mail.com");
        User user3 = new User(null, "Test3","test3@mail.com");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.flush();

        //act
        List<User> rersultList = userRepository.findAll();


        //assert
        assertEquals(3,rersultList.size());
        assertTrue(rersultList.contains(user1));
        assertTrue(rersultList.contains(user2));
        assertTrue(rersultList.contains(user3));


    }

}