package com.event_registration.lk.repository;

import com.event_registration.lk.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository <OrderEntity,String> {

}
