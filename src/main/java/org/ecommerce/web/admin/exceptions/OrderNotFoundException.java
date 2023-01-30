package org.ecommerce.web.admin.exceptions;

import org.ecommerce.config.web.admin.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author sergio
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order not found")
public class OrderNotFoundException extends NotFoundException {
}
