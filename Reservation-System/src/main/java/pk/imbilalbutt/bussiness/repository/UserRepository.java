package pk.imbilalbutt.bussiness.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pk.imbilalbutt.bussiness.model.User;

import java.util.Optional;

@Repository(value = "UserRepository")
public interface UserRepository extends BaseRepository<User> {

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    @Query(" UPDATE User u " +
            " SET u.status = :status " +
            " WHERE u.id = :id ")
    void updateActiveStatus(Long id, Boolean status);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String userName);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByEmailAndPassword(String email, String password);
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    UserRepository findByEmailAndPasswordAndAccountNonExpired(String email, String password);
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Query("SELECT ucd.user " +
//            "FROM UserCredentials ucd " +
//            "WHERE ucd.email = :email AND ucd.password = : password")
//    User findByEmailAndPassword(String email, String password);
//
//    @Modifying
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Query("UPDATE UserCredentials uc " +
//            " SET uc.password = :password " +
//            " WHERE uc.id = :id AND uc.email = :email ")
//    void updatePassword(Long id, String email, String password);
//
//    @Modifying
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Query("UPDATE UserCredentials uc " +
//            "SET uc.password = :newPassword " +
//            "WHERE uc.email = :email AND uc.reconfirmedPassword= :oldPassword AND uc.id = :id ")
//    void resetPassword(Long id, String email, String oldPassword, String newPassword);
//
//    @Modifying
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Query("UPDATE UserCredentials uc " +
//            " SET uc.reconfirmedPassword= :password " +
//            " WHERE uc.email = :email")
//    void updateConfirmPassword(String email, String password);

}
