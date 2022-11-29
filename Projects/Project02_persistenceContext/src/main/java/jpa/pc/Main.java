package jpa.pc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            System.out.println("\n 0. Show Original Table");
            showTable(em);

            tx.begin();

            System.out.println("\n 1. Create Entity and insert into DB");
            persistEntity(em);

            tx.commit();

            showTable(em);

            em.clear();

            System.out.println("\n 2. Select Entity and Compare m1 with m2");

            Member m1 = findEntity(em, "member1");
            Member m2 = findEntity(em, "member1");

            if (m1 == m2) {
                System.out.println("m1 == m2");
            } else {
                System.out.println("m1 != m2");
            }

            em.clear();
            tx.begin();

            System.out.println("\n 3. Update Entity");

            m1.setUsername("회원1");
            em.merge(m1);

            tx.commit();

            showTable(em);

            tx.begin();

            System.out.println("\n 4. Remove Entity");
            deleteEntity(em);

            tx.commit();

            Member m3 = findEntity(em, "member3");
            System.out.println("member3 = " + m3 + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void showTable(EntityManager em){
        List<Member> members = findAllEntities(em);
        System.out.println("| ID | NAME | AGE |");
        for (Member m : members) {
            System.out.println(m);
        }
    }

    private static void persistEntity(EntityManager em) {
        Member member = new Member();
        member.setId("member3");
        member.setUsername("회원3");

        em.persist(member);
    }

    private static List<Member> findAllEntities(EntityManager em) {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    private static Member findEntity(EntityManager em, String id) {
        return em.find(Member.class, id);
    }

    private static void deleteEntity(EntityManager em) {
        Member findMember = findEntity(em, "member3");
        em.remove(findMember);

        return;
    }
}