import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;

class HbOpUpdate{
public static void main(String args[]){
Console c = System.console();

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();

Session session = sfact.openSession();

Transaction t = null;

try{
System.out.println("begin");
t=session.beginTransaction();
int eid = Integer.parseInt(c.readLine("enter ID "));

Employee e = (Employee)session.get(Employee.class,eid);
if(e!=null){
String ename = c.readLine("enter name ");
double esalary = Double.parseDouble(c.readLine("enter salary "));
e.setEname(ename);
e.setEsalary(esalary);
session.save(e);
t.commit();
System.out.println("record updated");
}
else{
System.out.println("record doesn't exists");
}
System.out.println("end");
}
catch(Exception e){
t.rollback();
System.out.println("some issue "+e);
}
finally{
session.close();
}
}//end of main
}//end of class