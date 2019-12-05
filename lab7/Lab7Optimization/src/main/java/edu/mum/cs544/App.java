package edu.mum.cs544;

import javax.persistence.*;
import java.util.List;

public class App {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        long start = System.nanoTime();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

//        TypedQuery<Owner> query = em.createQuery("from Owner", Owner.class);
//        TypedQuery<Owner> query = em.createQuery("select o from Owner o join fetch o.pets", Owner.class); //19282
//        TypedQuery<Owner> query = em.createNamedQuery("Owner.GetAll", Owner.class); //20796

        EntityGraph<Owner> graph = em.createEntityGraph(Owner.class);// took 18730 ms
        graph.addAttributeNodes("pets");
        TypedQuery<Owner> query = em.createQuery("from Owner", Owner.class);
        query.setHint("javax.persistence.fetchgraph", graph);

        List<Owner> ownerlist = query.getResultList();
        for (Owner o : ownerlist) {
            o.getPets().size();
        }

        em.getTransaction().commit();
        em.close();
        long stop = System.nanoTime();

        // stop time
        System.out.println("To fetch this data from the database took " + (stop - start) / 1000000 + " milliseconds.");
        System.exit(0);
    }

}
