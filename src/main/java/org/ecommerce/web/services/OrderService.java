package org.ecommerce.web.services;

import org.springframework.stereotype.Component;

/**
 * @author sergio
 */
@Component
public interface OrderService {
	Long getNewOrders();

	Double getTotalProfit();
}
