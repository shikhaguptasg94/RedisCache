package com.sheduler.sheduler.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
//@NamedNativeQuery(name = "RedisUser.findByEmailIdAndPassword", query = "select * from REDIS_USER where email_id = ?1 and password = ?2", resultClass = RedisUser.class)
public class RedisUser {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name ="uuid2", strategy = "uuid2")
    @Column(name="id")
    @Type(type = "uuid-char")
    private UUID id;
    private String emailId;
    private String password;
}
