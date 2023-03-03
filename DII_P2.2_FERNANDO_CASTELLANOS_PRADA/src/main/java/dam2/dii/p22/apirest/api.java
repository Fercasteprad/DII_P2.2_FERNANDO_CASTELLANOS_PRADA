package dam2.dii.p22.apirest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dam2.dii.p22.contacto.Contacto;
import dam2.dii.p22.service.ContactoService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("contact")
public class api {
	
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Contacto> getListadoContactos() {
    
    	ArrayList<Contacto> listado = ContactoService.getContactos();
    	
  
    return listado;
    
    }
	 
	@GET
	@Path("size")
	@Produces(MediaType.TEXT_PLAIN)
	public int numeroContactos() {
		
		ArrayList<Contacto> listado = ContactoService.getContactos();
		int numeroTotal = listado.size();
		return numeroTotal;
		
	}
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //No hace falta que tenga ruta
    @Produces(MediaType.TEXT_PLAIN)
    public String anadirPersona(Contacto contacto) {
   
    	String mensaje = "";
    	
    	if (!(ContactoService.mailEncontrado(contacto.getEmail()))){
    		
    	   	if(ContactoService.anadirContacto(contacto)) {
    	   		mensaje = "El contacto ha sido añadido correctamente";
    	   	}
    	   	else {
    	   		mensaje = "El contacto no ha sido añadido";
    	   	}
    		
    	}
    	
    	else {
    		
    		mensaje = "El correo ya está en uso";
    		
    	}
    		   	
	   	return mensaje;
   
    }
    
   @PUT
   @Produces(MediaType.TEXT_PLAIN)
   public String actualizarPersona(@QueryParam("n") String nombre, @QueryParam("a") String apellido, @QueryParam("e") String email, 
		   @DefaultValue("0") @QueryParam("t") String telefono, @DefaultValue("") @QueryParam("c") String comentario) {
   
    	String mensaje = "";
    	
    	Contacto actualizar = new Contacto(nombre, apellido, email, telefono, comentario);
    	
    	if (ContactoService.actualizarContacto(actualizar)) {
    		mensaje = "El contacto ha sido actualizado correctamente";
    	}
    	
    	else {
    		mensaje = "El contacto no ha podido ser actualizado";
    	}
    	
    	
    	return mensaje;
   
   }
   
   @DELETE
   @Path("{email}")
   @Produces(MediaType.TEXT_PLAIN)
   public String eliminarPersona(@PathParam("email") String email) {
   
    	String mensaje = "";
    	
    	
    	    	
    	if (ContactoService.eliminarContacto(email)) {
    		mensaje = "El contacto ha sido eliminado correctamente";
    	}
    	
    	else {
    		mensaje = "El contacto no ha podido ser eliminado";
    	}
    	
    	
    	return mensaje;
   
   }
	
         
    	
}
