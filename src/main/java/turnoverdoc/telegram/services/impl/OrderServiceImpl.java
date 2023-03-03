package turnoverdoc.telegram.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import turnoverdoc.telegram.model.*;
import turnoverdoc.telegram.repositories.OrderRepository;
import turnoverdoc.telegram.services.ContactService;
import turnoverdoc.telegram.services.OrderService;
import turnoverdoc.telegram.services.UserService;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ContactService contactService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void createOrder(String username) {
        Order order = new Order();
        Contact contact = new Contact();
        contact.setMessenger(username);
        contact.setMessengerType(MessengerType.TELEGRAM);
        Contact savedContact = contactService.save(contact);

        User user = userService.findByTelegramUsername(username);

        order.setContact(savedContact);
        order.setUser(user);
        order.setStatus(OrderStatus.CONTACT_RECEIVED);

        orderRepository.save(order);
    }
}
