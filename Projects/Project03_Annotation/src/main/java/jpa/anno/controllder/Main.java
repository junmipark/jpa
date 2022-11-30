package jpa.anno.controllder;

import jpa.anno.dto.*;

import javax.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Member m = new Member();
        Item i = new Item();
        Orders o = new Orders();

        try {
            tx.begin();

            em.persist(m);
            em.persist(i);
            em.persist(o);

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}