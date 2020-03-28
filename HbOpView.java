import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;

class HbOpView
{
public static void main(String args[]){
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();

Session session = sfact.openSession();

try{
System.out.println("begin");
List<Employee> emp = new ArrayList<>();
emp = session.createQuery("from Employee").list();
for (Employee e:emp)
	System.out.println(e.getEid()+" "+e.getEname()+" "+e.getEsalary());
System.out.println("end");
}
catch(Exception e){
System.out.println(e);
}
finally{
session.close();
}

}
}