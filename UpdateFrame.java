import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import p1.*; 

class UpdateFrame extends JFrame{
Container c;
JButton btnSave,btnBack;
JLabel lblId,lblName,lblSalary,lblError;
JTextField txtId,txtName,txtSalary;

UpdateFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());
btnSave=new JButton("Save");
btnBack = new JButton("Back");
lblId = new JLabel("Id");
lblName = new JLabel("Name");
lblSalary = new JLabel("Salary");
lblError = new JLabel("");
txtId = new JTextField(20);
txtName = new JTextField(20);
txtSalary = new JTextField(20);
c.add(lblId);
c.add(txtId);
c.add(lblName);
c.add(txtName);
c.add(lblSalary);
c.add(txtSalary);
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
int eid;
String ename;
double esalary;
try{
System.out.println("begin");
t=session.beginTransaction();
eid = Integer.parseInt(txtId.getText());
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
Employee e = (Employee)session.get(Employee.class,eid);
if(e!=null){
ename = txtName.getText();
if(ename.length()<2){
lblError.setText("name length cannot be less than 2");
lblError.setForeground(Color.red);
AePlayWave a1 = new AePlayWave("negative.wav");
a1.start();
txtName.setText("");
txtName.requestFocus();
return;
}
else if(ename.matches(".*\\d.*")){
//JOptionPane.showMessageDialog(new JDialog(),"name cannot contain numeric values ");
lblError.setText("name cannot contain numeric values");
lblError.setForeground(Color.red);
AePlayWave a1 = new AePlayWave("negative.wav");
a1.start();
txtName.setText("");
txtName.requestFocus();
return;
}
esalary = Double.parseDouble(txtSalary.getText());
if(esalary <8000){
lblError.setText("salary cannot be less than 8000");
lblError.setForeground(Color.red);
AePlayWave a1 = new AePlayWave("negative.wav");
a1.start();
txtSalary.setText("");
txtSalary.requestFocus();
return;
}

/* if(eid<0){
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
else if(ename.length()<2){
lblError.setText("name length cannot be less than 2");
lblError.setForeground(Color.red);
AePlayWave a1 = new AePlayWave("negative.wav");
a1.start();
txtName.setText("");
txtName.requestFocus();
return;
}
else if(ename.matches(".*\\d.*")){
//JOptionPane.showMessageDialog(new JDialog(),"name cannot contain numeric values ");
lblError.setText("name cannot contain numeric values");
lblError.setForeground(Color.red);
AePlayWave a1 = new AePlayWave("negative.wav");
a1.start();
txtName.setText("");
txtName.requestFocus();
return;
}
else if(esalary <8000){
lblError.setText("salary cannot be less than 8000");
lblError.setForeground(Color.red);
AePlayWave a1 = new AePlayWave("negative.wav");
a1.start();
txtSalary.setText("");
txtSalary.requestFocus();
return;
} */
lblError.setText("");
e.setEname(ename);
e.setEsalary(esalary);
session.save(e);
t.commit();
AePlayWave a1 = new AePlayWave("positive.wav");
a1.start();
JOptionPane.showMessageDialog(new JDialog(),"record updated");
txtId.setText("");
txtName.setText("");
txtSalary.setText("");
txtId.requestFocus();
}
else{
JOptionPane.showMessageDialog(new JDialog(),"record doesn't exists");
txtId.setText("");
txtName.setText("");
txtSalary.setText("");
txtId.requestFocus();
}
System.out.println("end");
}
catch(Exception e){
try{
eid = Integer.parseInt(txtId.getText());
}
catch(NumberFormatException a)
{
JOptionPane.showMessageDialog(new JDialog(),"Invalid Id");
txtId.setText("");
txtId.requestFocus();

t.rollback();
return;}
try{
esalary = Double.parseDouble(txtSalary.getText());}
catch(NumberFormatException b)
{
JOptionPane.showMessageDialog(new JDialog(),"Invalid Salary");
txtSalary.setText("");
txtSalary.requestFocus();
t.rollback();
return;
}
t.rollback();
JOptionPane.showMessageDialog(new JDialog(),"some issue "+e);
txtId.setText("");
txtName.setText("");
txtSalary.setText("");
txtId.requestFocus();
}
finally{
session.close();
}
}
});

setTitle("Update Employee");
setLocationRelativeTo(null);
setSize(250,300);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}//end of constructor

}//end of class