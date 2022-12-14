package ru.bykov.insidetest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.bykov.insidetest.model.Role;
import ru.bykov.insidetest.repository.RoleRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@DataJpaTest
class JpaRoleEMTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    RoleRepository repository;

    @Test
    void findByNameTest() {
        Role role = new Role();
        role.setName("ROLE_ADMIN2");
        entityManager.persist(role);
        entityManager.flush();

        Role roleGet = repository.findByName("ROLE_ADMIN2").get();
        assertThat(roleGet.getName()).isEqualTo("ROLE_ADMIN2");
    }
}
