package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import utils.EMF_Creator;
import facades.EmployeeFacade;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("employee")
public class EmployeeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final EmployeeFacade FACADE = EmployeeFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {

        long count = FACADE.getEmployeeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        List<EmployeeDTO> employeeDTOS = FACADE.getAllEmployees();
        return new Gson().toJson(employeeDTOS);

    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("id")Long id) {
        EmployeeDTO employeeDTO = FACADE.getEmployeeById(id);
        System.out.println(new Gson().toJson(employeeDTO));
        return new Gson().toJson(employeeDTO);
    }

    @Path("highestpaid")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeByHighestPay() {
        List<EmployeeDTO> employeeDTOS = FACADE.getEmployeeWithHighestSalary();
        return new Gson().toJson(employeeDTOS);

    }


    @Path("emp/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("id") String id) {
        EmployeeDTO employeeDTO = FACADE.getEmployeesByName(id);
        System.out.println(new Gson().toJson(employeeDTO));
        return new Gson().toJson(employeeDTO);
    }
}
