package alex.valker91.service;

import alex.valker91.domain.Order;
import alex.valker91.service.dto.OrderDto;

public interface OrderService {
    Order save(OrderDto clientDto);
}
