package facades;

import dtos.EmployeeDTO;
import entities.Employee;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class EmployeeFacadeTest {

    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;

    public EmployeeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = EmployeeFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();
            em.persist(new Employee("Kurt", "Wonnegut", 335567));
            em.persist(new Employee("Hanne", "Olsen", 435867));
            em.persist(new Employee("Jan", "Olsen", 411567));
            em.persist(new Employee("Irene", "Petersen", 33567));
            em.persist(new Employee("Tian", "Wonnegut", 56567));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() throws Exception {
        assertEquals(5, facade.getEmployeeCount(), "Expects two rows in the database");
    }
    @Test
    public void testAfgetEmployeeById() throws Exception {
        Long id= Long.valueOf(2);
        EmployeeDTO employeeDTO = facade.getEmployeeById(id);
        boolean hasReturned=(employeeDTO!=null);
        assertEquals(true, hasReturned, "we expect the method to return an object");
        Long idReturned= employeeDTO.getId();
        assertEquals(id, idReturned, "we expect the method to return the same id");

    }
    @Test
    public void testAfgetEmployeesByName() throws Exception {
        String name= "Irene";
        EmployeeDTO employeeDTO = facade.getEmployeesByName(name);
        String foundName = employeeDTO.getDummyStr1();
        assertEquals(name, foundName , "vi ved at dummystring 1 returnerer navn");
    }


    @Test
    public void testAfgetAllEmployees() throws Exception {
        String name= "Irene";
        List<EmployeeDTO> employees = facade.getAllEmployees();
        int size = employees.size();

        assertEquals(5,size , "vi tjekker at vi har fat i et objekt");
    }

    @Test
    public void testAfaddEmployee() throws Exception {
        facade.addEmployee("Kasper","Kardemommeby", 50000);
        String employeeName =facade.getEmployeesByName("Kasper").getDummyStr1();
        assertEquals("Kasper", employeeName, "vi tjekker at vi har fat i et objekt");
    }

    @Test
    public void testAfgetEmployeeWithHighestSalary() throws Exception {
        List<EmployeeDTO> highestPaidEmps = facade.getEmployeeWithHighestSalary();
        EmployeeDTO highestPaid = highestPaidEmps.get(0);
        String highestPaidName = highestPaid.getDummyStr1();
        assertEquals("Hanne", highestPaidName);
    }

}
