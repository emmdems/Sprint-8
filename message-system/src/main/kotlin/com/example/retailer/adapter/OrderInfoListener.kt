package com.example.retailer.adapter

import com.example.retailer.api.distributor.OrderInfo
import com.example.retailer.service.OrderService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderInfoListener : MessageListener {
    @Autowired
    private lateinit var orderService: OrderService

    @RabbitListener(queues = ["\${spring.rabbitmq.queue}"])
    override fun readResponse(message: OrderInfo) {
        orderService.updateOrderInfo(message)
    }
}