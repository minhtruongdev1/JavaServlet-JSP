package poly.edu.until;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	public static EntityManagerFactory factory;
	  public static EntityManager getEntityManager() {
	        if (factory == null || factory.isOpen()) {
	            factory = Persistence.createEntityManagerFactory("projectjava4");
	        }
	        return factory.createEntityManager();
	    }

	    public static void shutDown() {
	        if (factory != null && factory.isOpen()) {
	            factory.close();
	        }
	        factory = null;
	    }
		
}
