package com.order.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.order.entity.OrderTracker;

public interface OrderTrackerDAO extends JpaRepository<OrderTracker, String> {

	@Query("SELECT o.orderStatus, o.ptime, COUNT(o) FROM OrderTracker o WHERE o.insertTimeStamp BETWEEN :startTime AND :endTime GROUP BY o.orderStatus, o.ptime")
	List<Object[]> getOrderStatusAndPtimeCount(@Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime);

	@Query("SELECT o.orderStatus, o.ptime, COUNT(o) FROM OrderTracker o WHERE o.insertTimeStamp <= :endTime GROUP BY o.orderStatus, o.ptime")
	List<Object[]> getOrderStatusAndPtimeCount(@Param("endTime") LocalDateTime endTime);

	@Query("SELECT o.orderStatus, o.ptime, COUNT(o) FROM OrderTracker o GROUP BY o.orderStatus, o.ptime")
	List<Object[]> getOrderStatusAndPtimeCount();
}
