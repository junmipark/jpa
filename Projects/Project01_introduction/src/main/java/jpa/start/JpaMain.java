package jpa.start;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    // Business Logic
    private static void logic(EntityManager em){
        String id = "id1";
        String username = "jm";
        Integer age = 25;

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        member.setAge(age);

        // Insert
        em.persist(member);

        // Modify
        member.setAge(18);

        // Select
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember = " + findMember.getUsername() + ", age = " + findMember.getAge());

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size = " + members.size());

        // Delete
        em.remove(member);
    }
}