package facades;

import Exceptions.PersonNotFoundException;
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public Long getPersonCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long renameMeCount = (Long) em.createQuery("SELECT COUNT(r) FROM Person r").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }

    }

    @Override
    public PersonDTO addPerson(String fName, String lName, int phone) {
        EntityManager em = emf.createEntityManager();
        Person person = new Person(fName, lName, phone);

        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    @Override
    public PersonDTO deletePerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new PersonNotFoundException(String.format("person with id: {%d} not found", id));
        }else{
        try {
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        return new PersonDTO(person);
        }
    }

    @Override
    public PersonDTO getPerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Person person = em.find(Person.class, id);
            if (person == null) {
               throw new PersonNotFoundException("Requested Person does not exist");
            } else {
                return new PersonDTO(person);
            }
        }finally {
            em.close();
        }
    }
  @Override
    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, p.getId());
        if (person == null) {
            throw new PersonNotFoundException("Requested Person with "+ p.getId() + " does not exist");
        }
        person.setfName(p.getfName());
        person.setlName(p.getlName());
        person.setPhone(p.getPhone());
        person.setLastEdited(new Date());

        try {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        } finally {
            em.close();
        }
    }


    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            return new PersonsDTO(query.getResultList());
        } finally {
            em.close();
        }

    }

}
