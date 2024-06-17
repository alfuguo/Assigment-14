package com.coderscampus.al_assigment_14.repository;

import com.coderscampus.al_assigment_14.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import com.coderscampus.al_assigment_14.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface messageRepository extends JpaRepository<Message, Long>{
    public List<Message> findByChannel(Channel channel);
}
