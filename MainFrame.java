import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame{
Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete,btnClose;

MainFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());
btnAdd = new JButton("Add");
btnView = new JButton("View");
btnUpdate = new JButton("Update");
btnDelete = new JButton("Delete");
btnClose = new JButton("Close");
c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);
c.add(btnClose);

btnClose.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
System.exit(0);
}
});

btnAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
AddFrame a = new AddFrame();
dispose();
}
});

btnView.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
ViewFrame a = new ViewFrame();
dispose();
}
});

btnDelete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
DeleteFrame a = new DeleteFrame();
dispose();
}
});

btnUpdate.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
UpdateFrame a = new UpdateFrame();
dispose();
}
});

setTitle("Employee Management system");
setSize(200,200);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

public static void main(String args[]){
MainFrame m = new MainFrame();
}//end of main
}//end of class