package com.a1st.banking.repositories;

import com.a1st.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByFirstName(String firstName);
    List<User> findAllByFirstNameContaining(String firstName);
    List<User> findAllByFirstNameContainingIgnoreCase(String firstName);
    List<User> findAllByAccountIban(String iban);
    User findByFirstNameContainingIgnoreCaseAndEmail(String firstName, String email);



    @Query("from User where firstName = :fn")
    List<User> searchByFirstName(@Param("fn") String firstName);


    @Query("from User where firstName = '%:firstName%'")
    List<User> searchByFirstNameContaining(String firstName);



    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByIban(String iban);
    @Query(value = "select * from _user_ u inner join Account a on u.id = a.user_id where a.iban = :iban", nativeQuery = true)
    List<User> searchByIbanNative(String iban);


    Optional<User> findByEmail(String email);
}
