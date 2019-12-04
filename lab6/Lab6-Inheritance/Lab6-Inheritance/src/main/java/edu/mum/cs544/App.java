package edu.mum.cs544;

import edu.mum.cs544.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class App {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product product1 = new CD("Roman Polansky", "Once upon a time", "Funny music CD Album.");
        Product product2 = new DVD("Horror", "Spider-man Blood Adventure", "DVD Movie");
        Product product3 = new Book("The Lord of the Rings", "Old Book");


        Customer customer = new Customer("Frank", "Brown");

        OrderLine orderLine1 = new OrderLine(1, product1);
        OrderLine orderLine2 = new OrderLine(2, product1);

        OrderLine orderLine3 = new OrderLine(1, product2);
        OrderLine orderLine4 = new OrderLine(1, product3);

        Order order = new Order(LocalDate.now());
        order.addLine(orderLine1);
        order.addLine(orderLine2);
        order.addLine(orderLine3);
        order.addLine(orderLine4);

        customer.addOrder(order);

        em.persist(customer);


        System.out.println();
        em.getTransaction().commit();

        em.getTransaction().begin();

        TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);

        List<Customer> customers = query.getResultList();

        for (Customer c : customers) {
            System.out.println("Customer name: " + c.getFirstname() + " " + c.getLastname());
            System.out.println("Order details: ");

            for (Order o : c.getOrders()) {
                System.out.println("  Date: " + o.getDate());
                System.out.println("  Order lines: ");
                for (OrderLine l : o.getLines()) {
                    System.out.println("    Quantity: " + l.getQuantity());

                    List<Product> products = em.createNativeQuery("select distinct p.name, p.description, p.id from OrderLine l join Product p on l.product_id = p.id where l.id = :id", Product.class)
                            .setParameter("id", l.getId())
                            .getResultList();


                    for (Product p : products) {
                        System.out.println("    Product info: ");
                        System.out.println("      Name: " + p.getName());
                        System.out.println("      Description: " + p.getDescription());
                    }
                }
            }
        }

        System.out.println();
        em.getTransaction().commit();
        em.close();
    }
}
