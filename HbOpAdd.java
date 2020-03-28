import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;

class HbOpAdd{
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
Employee e = new Employee();
int eid = Integer.parseInt(c.readLine("enter Id "));
String ename = c.readLine("enter name ");
double esalary = Double.parseDouble(c.readLine("enter salary "));
e.setEid(eid);
e.setEname(ename);
e.setEsalary(esalary);
session.save(e);
t.commit();
System.out.println("record inserted ");
System.out.println("end");
}
catch(Exception e){
t.rollback();
System.out.println(e);
}
finally{
session.close();
}
}//end of main
}//end of class