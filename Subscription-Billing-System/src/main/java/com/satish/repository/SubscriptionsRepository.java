package com.satish.repository;

import com.satish.entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscriptions,Long> {

    List<Subscriptions> findSubscriptionsByUserId(Long userId);

}
