package jpa.model;

import jpa.model.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        setProxy(em, tx);

        try {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    public static void setProxy(EntityManager em, EntityTransaction tx) {
        // CHAP 08. Proxy
        Delivery delivery = new Delivery();

        OrderItem orderItem1 = new OrderItem();
        OrderItem orderItem2 = new OrderItem();

        Order order = new Order();
        order.setDelivery(delivery);
        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);

        em.persist(order); // delivery, orderItems는 플러시 시점에 영속성 전이 (CASCADE 설정을 통해)
    }
}
