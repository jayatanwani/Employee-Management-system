import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.regex.*;

import java.io.File; 
import java.io.IOException; 
import javax.sound.sampled.AudioFormat; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.FloatControl; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.SourceDataLine; 
import javax.sound.sampled.UnsupportedAudioFileException; 
import p1.*; 

class AddFrame extends JFrame{

Container c;
JButton btnSave,btnBack;
JTextField txtId,txtName,txtSalary;
JLabel lblId, lblName, lblSalary, lblError;

AddFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());
lblId = new JLabel("Id");
lblName = new JLabel("Name");
lblSalary = new JLabel("Salary");
lblError = new JLabel("");
txtId = new JTextField(20);
txtName = new JTextField(20);
txtSalary = new JTextField(20);
btnSave = new JButton("Save");
btnBack = new JButton("Back");
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
MainFrame a = new MainFrame();
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
double esalary;
String ename;
try{
System.out.println("begin");
t=session.beginTransaction();
Employee e = new Employee();
eid = Integer.parseInt(txtId.getText());
//String eid =txtId.getText();
 /*if(ename.equals("")){
JOptionPane.showMessageDialog(new JDialog(),"Please enter the name ");
txtName.requestFocus();
return;
}*/
 
//String esalary = txtSalary.getText();

//if (txtId.length()==0 || txtName.hashCode()==0 || txtSalary.getText() == "")
	//{JOptionPane.showMessageDialog(new JDialog(),"Please enter the required fields ");}
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
ename = txtName.getText();

 if(ename.equals("")){
JOptionPane.showMessageDialog(new JDialog(),"please enter name ");
	return;

}
 if(ename.length()<2){
lblError.setText("name length cannot be less than 2");
lblError.setForeground(Color.red);
AePlayWave a1 = new AePlayWave("negative.wav");
a1.start();
txtName.setText("");
txtName.requestFocus();
	return;
}
if(ename.matches(".*\\d.*")){
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
lblError.setText("");
e.setEid(eid);
e.setEsalary(esalary);
e.setEname(ename);
session.save(e);
t.commit();
AePlayWave a1 = new AePlayWave("positive.wav");
a1.start();
JOptionPane.showMessageDialog(new JDialog(),"record inserted ");

txtId.setText("");
txtName.setText("");
txtSalary.setText("");
txtId.requestFocus();
System.out.println("end");
//end of else
}//end of try
catch(Exception nfe){
try{
eid = Integer.parseInt(txtId.getText());
}
catch(NumberFormatException a)
{
lblError.setText("");
JOptionPane.showMessageDialog(new JDialog(),"Invalid Id");
txtId.setText("");
txtId.requestFocus();
t.rollback();
return;
}


try{
esalary = Double.parseDouble(txtSalary.getText());}
catch(NumberFormatException b)
{
lblError.setText("");
JOptionPane.showMessageDialog(new JDialog(),"Invalid Salary");
txtSalary.setText("");
txtSalary.requestFocus();

t.rollback();
return;

}
}

finally{
session.close();
}
}
});

setTitle("Add Employee");
setLocationRelativeTo(null);
setSize(250,300);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}//end of constructor


}//end of class