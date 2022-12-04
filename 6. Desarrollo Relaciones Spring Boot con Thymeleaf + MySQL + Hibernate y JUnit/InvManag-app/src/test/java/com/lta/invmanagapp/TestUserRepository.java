package com.lta.invmanagapp;

import com.lta.invmanagapp.Model.Role;
import com.lta.invmanagapp.Model.User;
import com.lta.invmanagapp.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class TestUserRepository {
    @Autowired
    private UserRepository ur;
    @Autowired
    private TestEntityManager entityManager;    //Persistencia sin intervencio de jpa

    @Test
    public void TestSaveRole(){
        Role roleA = new Role("Admin");
        Role roleE = new Role("Editor");
        Role roleV = new Role("Visitor");

        entityManager.persist(roleA);
        entityManager.persist(roleE);
        entityManager.persist(roleV);
    }

    @Test
    public void TestSaveUser(){
        Role role = entityManager.find(Role.class,1L);
        User user = new User("carlinhos@gmail.com","123456");
        user.addRole(role);
        User userN = ur.save(user);
        assertThat(userN.getId()).isGreaterThan(0);
    }

    @Test
    public void TestSaveUserRoleMore(){
        Role roleA = entityManager.find(Role.class,1L);
        Role roleE = entityManager.find(Role.class,2L);
        User user = new User("marito@gmail.com","123456");
        user.addRole(roleA);    user.addRole(roleE);
        User userN = ur.save(user);
        assertThat(userN.getId()).isGreaterThan(0);
    }

    @Test
    public void TestSaveUserandRole(){  //GUARDA USER Y ROLE NUEVOS
        User user = new User("papas@gmail.com","123456");
        Role role = new Role("Seller");
        user.addRole(role);
        User userN = ur.save(user);
        assertThat(userN.getId()).isGreaterThan(0);
    }

    @Test
    public void TestUpdateUserRoles(){ //AGREGA ROLE DE UN USER SIN EMPLEAR REPOSITORY DIRECTAMENTE
        User user = ur.findById(1L).get();
        Role roleE = entityManager.find(Role.class,2L);
        user.addRole(roleE);        //Agregar y guardar roles segun un usuario sin hacer save.
    }

    @Test
    public void TestGetUser(){
        User user = ur.findById(1L).get();
        entityManager.detach(user); //No esta gestionado por entityManager, los cambios que hagas en el
        System.out.println(user.getEmail());  //no se guardaran en la BDD.
        System.out.println(user.getRoles());
    }

    @Test
    public void TestRemoveUserRole(){   //REMUEVE ROLE DE UN USER SIN EMPLEAR REPOSITORY DIRECTAMENTE
        User user = ur.findById(1L).get();
        Role role = new Role();
        role.setId(2L);
        user.removeRole(role);  //Remueve y guardar roles segun un usuario sin hacer save.
    }

    @Test
    public void TestDeleteUser(){
        ur.deleteById(2L);  //SE COMENTO CASCADE Y FETCH PARA PODER LLEVAR A CABO SOLO ESTA PRUEBA
    }
}
