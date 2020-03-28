import javax.swing.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;

class ViewFrame extends JFrame{
Container c;
JTextArea txtEmp;
JButton btnBack;

ViewFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());
txtEmp = new JTextArea(10,25);
txtEmp.setEditable(false);
btnBack = new JButton("Back");
c.add(txtEmp);
c.add(btnBack);

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame m = new MainFrame();
dispose();
}
});

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();

Session session = sfact.openSession();

try{
System.out.println("begin");
List<Employee> emp = new ArrayList<>();
emp = session.createQuery("from Employee").list();
String data ="";
for (Employee e:emp)
	data =data+ e.getEid()+" "+e.getEname()+" "+e.getEsalary()+"\n";
if(data=="")
	txtEmp.setText("no records");
else
	txtEmp.setText(data);
System.out.println("end");
}
catch(Exception e){
JOptionPane.showMessageDialog(new JDialog(),"some issue "+e);
}
finally{
session.close();
}

setTitle("View Employee");
setLocationRelativeTo(null);
setSize(300,300);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}//end of constructor
}//end of class