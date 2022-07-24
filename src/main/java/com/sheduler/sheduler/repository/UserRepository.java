package com.sheduler.sheduler.repository;
import com.sheduler.sheduler.model.AddUserDTO;
import com.sheduler.sheduler.model.RedisUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<RedisUser, Integer>
{

    //@Query("select s from RedisUser s where s.emailId = :emailId and s.password = :password")
    public List<RedisUser> findByEmailIdAndPassword(String emailId, String password);
}
