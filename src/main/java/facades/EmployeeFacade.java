package facades;

import dtos.EmployeeDTO;
import entities.Employee;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import utils.EMF_Creator;

/**
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }


    public EmployeeDTO getEmployeeById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            return new EmployeeDTO(employee);
        } finally {
            em.close();
        }
    }

//    public EmployeeDTO getById(long id) {
//        EntityManager em = emf.createEntityManager();
//        return new EmployeeDTO(em.find(Employee.class, id));
//    }

    public EmployeeDTO getEmployeesByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
//            Employee employee = em.find(Employee.class, "Irene");
            TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.name = :name", Employee.class).setParameter("name", name);
//            CreateQuery("SELECT a FROM Animal a where a.animal = :animal_", Animal.class).setParameter("animal_", animal);
            return new EmployeeDTO(query.getSingleResult());

        } finally {
            em.close();
        }
    }

    public List<EmployeeDTO> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("Select e from Employee e", Employee.class);
            List<Employee> employees = query.getResultList();
            return EmployeeDTO.getDtos(employees);
        } finally {
            em.close();
        }
    }

    public EmployeeDTO addEmployee(String name, String address, int salary) {
        Employee employee = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return new EmployeeDTO(employee);
        } finally {
            em.close();
        }
    }

    public List<EmployeeDTO> getEmployeeWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.salary = (select max(e.salary) from Employee e)", Employee.class);
            List<Employee> employees = query.getResultList();
            return EmployeeDTO.getDtos(employees);

        } finally {
            em.close();
        }
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


//    public EmployeeDTO create(EmployeeDTO employeeDTO) {
//        Employee employee = new Employee(employeeDTO.getDummyStr1(), employeeDTO.getDummyStr2(), employeeDTO.);
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(employee);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//        return new EmployeeDTO(employee);
//    }


    //TODO Remove/Change this before use
    public long getEmployeeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long employeeCount = (long) em.createQuery("SELECT COUNT(r) FROM Employee r").getSingleResult();
            return employeeCount;
        } finally {
            em.close();
        }
    }

    public List<EmployeeDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        Employee employee;
        TypedQuery<Employee> query = em.createQuery("SELECT r FROM Employee r", Employee.class);
        List<Employee> rms = query.getResultList();
        return EmployeeDTO.getDtos(rms);
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade fe = getFacadeExample(emf);
//        fe.getAll().forEach(dto -> System.out.println(dto));
    }

}
