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

        init(em, tx);

        try {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    public static void init(EntityManager em, EntityTransaction tx) {
        // CHAP 05
        // 회원 생성
        Member m = new Member();
        m.setName("USER1");

        // CHAP 07
        // 상품 생성 : 음반, 책, 영화
        List<Item> il = new ArrayList<>();
        il.add(new Album());
        il.add(new Book());
        il.add(new Movie());

        // 주문 생성
        Order o = new Order();
        o.setMember(m);

        // 상품 주문
        for (Item i : il
        ) {
            i.setPrice(1000);
            i.setStockQuantity(100);

            OrderItem oi = new OrderItem();
            oi.setItem(i);
            oi.setCount(1);
            oi.setOrderPrice(i.getPrice() * oi.getCount());

            o.addOrderItem(oi);
        }

        // CHAP 06
        // 배송 정보 생성
        Delivery d = new Delivery();
        d.setStatus(DeliveryStatus.READY);

        // 배송 정보 추가
        o.setDelivery(d);

        // 상품 카테고리 설정
        Category c = new Category();
        c.setName("CATEGORY");

        try {
            tx.begin();
            em.persist(m);
            em.persist(o);
            for (Item i : il) {
                em.persist(i);
            }
            for (OrderItem oi : o.getOrderItems()
            ) {
                em.persist(oi);
            }
            em.persist(d);
            em.persist(c);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
}
