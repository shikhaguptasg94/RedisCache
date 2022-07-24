package com.sheduler.sheduler.services;

import com.sheduler.sheduler.model.AddUserDTO;
import com.sheduler.sheduler.model.AuthUserDto;
import com.sheduler.sheduler.model.GanericResponse;
import com.sheduler.sheduler.model.RedisUser;
import com.sheduler.sheduler.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServise {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RedisUtility redisUtility;

    public GanericResponse addUser(AddUserDTO addUserDTO){
        RedisUser user = new RedisUser();
        user.setEmailId(addUserDTO.getEmailId());
        user.setPassword(addUserDTO.getPassword());
        GanericResponse ganericResponse = new GanericResponse("User successfully registereed!", userRepository.save(user));
        return ganericResponse;
    }

    public GanericResponse authUser(AuthUserDto authUserDto){
        log.info("authUser#####"+authUserDto.getEmailId()+":"+authUserDto.getPassword());
        AuthUserDto authUserDto1 = redisUtility.getValue(authUserDto.getEmailId());
        log.info("authUserDto1#####"+authUserDto1);
        if(authUserDto1 == null){
            List<RedisUser> resultList = userRepository.findByEmailIdAndPassword(authUserDto.getEmailId(), authUserDto.getPassword());
            log.info("resultList#####"+resultList);
            if( resultList.size() > 0){
                //redisUtility.setValue(authUserDto.getEmailId(),authUserDto);
                return new GanericResponse("Getting value from <--- Database server --->", authUserDto);
            }
            return new GanericResponse(" <--- No value found --->", authUserDto);
        }
        return new GanericResponse("Getting value from <--- Redis server --->", authUserDto1);
    }

}
