package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Repository
public class CommentRepository {

    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory factory;

    public Comment createCommet(Comment comment){
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(comment);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return comment;
    }
}
