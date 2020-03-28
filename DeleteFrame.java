import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import p1.*; 

class DeleteFrame extends JFrame{
Container c;
JLabel lblId,lblError;
JTextField txtId;
JButton btnSave, btnBack;

DeleteFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());
lblId = new JLabel("Id");
lblError = new JLabel("");
txtId = new JTextField(20);
btnSave = new JButton("Save");
btnBack = new JButton("Back");
c.add(lblId);
c.add(txtId);
c.add(btnSave);
c.add(btnBack);
c.add(lblError);

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame m = new MainFrame();
dispose();
}
});

btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();

Session session = sfact.openSession();

Transaction t = null;

try{
System.out.println("begin");
t=session.beginTransaction();
int eid = Integer.parseInt(txtId.getText());
if(eid<0){
		//lblError.setOpaque(true);
		//lblError = new JLabel("Invalid ID");
		//lblError.setBackground(Color.RED);
		lblError.setText("Invalid ID");
		lblError.setForeground(Color.red);
		AePlayWave a1 = new AePlayWave("5ec95a75e586daa1279a1db93fb5-orig.wav");
		a1.start();
		//c.add(lblError);
		txtId.setText("");
		txtId.requestFocus();
		return;
}
lblError.setText("");
Employee e = (Employee)session.get(Employee.class,eid);
if(e!=null){
session.delete(e);
t.commit();
JOptionPane.showMessageDialog(new JDialog(),"record deleted ");
txtId.setText("");
txtId.requestFocus();
}
else{
JOptionPane.showMessageDialog(new JDialog(),"record doesn't exists ");
txtId.setText("");
txtId.requestFocus();
}
System.out.println("end");
}//end of try
catch(NumberFormatException nfe){
JOptionPane.showMessageDialog(new JDialog(),"Invalid ID");
t.rollback();
txtId.setText("");
txtId.requestFocus();
}
catch(Exception e){
t.rollback();
JOptionPane.showMessageDialog(new JDialog(),"some issue "+e);
txtId.setText("");
txtId.requestFocus();
}
finally{
session.close();
}
}
});

setTitle("Delete Employee");
setSize(270,300);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}//end of constructor
}//end of class