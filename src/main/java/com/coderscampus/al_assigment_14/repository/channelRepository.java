package com.coderscampus.al_assigment_14.repository;

import com.coderscampus.al_assigment_14.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface channelRepository extends JpaRepository<Channel, Long>{
}
