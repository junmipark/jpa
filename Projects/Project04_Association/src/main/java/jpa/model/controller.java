package jpa.model;

import jpa.model.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class controller {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        init(em, tx);

        try {
            // CHAP 05
            Long orderId = Long.valueOf(4);
            Orders order = em.find(Orders.class, orderId);
            Member member = order.getMember();
            OrderItem orderItem = order.getOrderItems().get(0);
            Item item = orderItem.getItem();

            String mStr = "Member : (ID = " + member.getMemberId() + ", NAME = " + member.getName() + ")";
            String iStr = "Item : (ID = " + item.getItemId() + ", PRICE = " + item.getPrice() + ", StockQuantity = " + item.getStockQuantity() + ")";

            System.out.println(mStr);
            System.out.println(iStr);

            // CHAP 06
            Delivery delivery = order.getDelivery();

            String dStr = "Delivery : (ID = " + delivery.getDeliveryId() + ", STATUS = " + delivery.getStatus() + ")";

            System.out.println(dStr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    public static void init(EntityManager em, EntityTransaction tx){
        // CHAP 05
        // 회원 생성
        Member m = new Member();
        m.setName("USER1");

        // 상품 생성
        Item i = new Item();
        i.setName("ITEM1");
        i.setPrice(1000);
        i.setStockQuantity(100);

        // 주문 생성
        Orders o = new Orders();
        o.setMember(m);

        // 상품 주문
        OrderItem oi = new OrderItem();
        oi.setItem(i);
        oi.setCount(1);
        oi.setOrderPrice(i.getPrice() * oi.getCount());

        // 주문 목록에 상품 추가
        o.addOrderItem(oi);

        // CHAP 06
        // 배송 정보 생성
        Delivery d = new Delivery();
        d.setStatus(DeliveryStatus.READY);

        // 배송 정보 추가
        o.setDelivery(d);

        // 상품 카테고리 설정
        Category c = new Category();
        c.setName("CATEGORY");
        c.addItem(i);

        try{
            tx.begin();

            em.persist(m); // MEMBER_ID = 1
            em.persist(i); // ITEM_ID = 2
            em.persist(oi); // ORDER_ITEM_ID = 3
            em.persist(o); // ORDER_ID = 4
            em.persist(d); // DELIVERY_ID = 5
            em.persist(c); // CATEGORY_ID = 6

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
    }
}
