package com.order;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.order.queue.OrderConsumerTest;
import com.order.service.OrderMetricServiceTest;
import com.order.service.OrderServiceTest;

@Suite
@SelectClasses({
    OrderConsumerTest.class,
    OrderServiceTest.class,
    OrderMetricServiceTest.class
})
class OrderMsApplicationTests {

}
