

package PaqPuerto;

import PaqC01.Contenedor;
import PaqC01.Hub;
import PaqC01.Puerto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DiseñoContenedores extends JFrame {
    private JLabel NumId;
    private JTextField NumIdtext;
    private JLabel Peso;
    private JTextField Pesotext;
    private JLabel Desc;
    private JTextArea Desctext;
    private JLabel Emp_rem;
    private JLabel Emp_rec;
    private JTextField Emp_rectext;
    private JLabel Pais_proc;
    private JComboBox Pais_procbox;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JCheckBox Insp_Aduanas;
    private JPanel mainPanel;
    private JTextField Emp_remtext;
    private JLabel Ops;
    private JButton Ap_button;
    private JButton Desap_button;
    private JTextField numCol_text;
    private JButton ContData_button;
    private JTextField ID_text;
    private JButton Cuantos_button;
    private JComboBox cuantosPais_box;
    private JTextField Cant_text;
    private JPanel auxPanel;
    private JLabel Estado;
    private JTextArea Estad_text;
    private JLabel Logo;
    private JLabel Mensajes;
    private JLabel hub_Texto;
    private JRadioButton a1Hub;
    private JRadioButton a2Hub;
    private JRadioButton a3Hub;
    private JButton stringButton;
    private JTextField elegirHubTextField;

    private int hubMostrar = 0;
    Puerto p1;


    public DiseñoContenedores() {
        setContentPane(mainPanel);
        setTitle("Welcome");
        a1RadioButton.setSelected(true);
        a1Hub.setSelected(true);
        setSize(1500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Hub h1 = new Hub();
        Hub h2 = new Hub();
        Hub h3 = new Hub();

        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        //Lectura de objetos
        try {
            fis = new FileInputStream("puerto.dat");
            entrada = new ObjectInputStream(fis);
            p1 = (Puerto) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            p1 = new Puerto();
            p1.setPuerto(new Hub[]{h1, h2, h3});
        }

        Estad_text.setText(p1.toStringHUB(hubMostrar));


        Ap_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int prioridad = 0;
                if(a1RadioButton.isSelected()) {
                    prioridad = 1;
                } else if(a2RadioButton.isSelected()) {
                    prioridad = 2;
                } else if(a3RadioButton.isSelected()) {
                    prioridad = 3;
                }
                if(p1.apilaContenedor(hubMostrar, new Contenedor(Integer.parseInt(NumIdtext.getText()), Integer.parseInt(Pesotext.getText()), (String) Pais_procbox.getSelectedItem(), Insp_Aduanas.isSelected(), prioridad, Desctext.getText(), Emp_remtext.getText(), Emp_rectext.getText()))) {
                    JOptionPane.showMessageDialog(null, "El contenedor se ha apilado.");
                } else JOptionPane.showMessageDialog(null, "No hay espacio para ese contenedor en este hub.");
                Estad_text.setText(p1.toStringHUB(hubMostrar));

                FileOutputStream fos = null;
                ObjectOutputStream salida = null;
                //Escritura de objetos
                try {
                    fos = new FileOutputStream("puerto.dat");
                    salida = new ObjectOutputStream(fos);
                    salida.writeObject(p1);
                } catch (IOException w) {
                    w.printStackTrace();
                } finally {
                    try {
                        if(fos != null) fos.close();
                        if(salida != null) salida.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        Desap_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                p1.desapilaContenedor(hubMostrar, Integer.parseInt(numCol_text.getText())); 
                Estad_text.setText(p1.toStringHUB(hubMostrar));

                FileOutputStream fos = null;
                ObjectOutputStream salida = null;
                //Escritura de objetos
                try {
                    fos = new FileOutputStream("puerto.dat");
                    salida = new ObjectOutputStream(fos);
                    salida.writeObject(p1);
                } catch (IOException w) {
                    w.printStackTrace();
                } finally {
                    try {
                        if(fos != null) fos.close();
                        if(salida != null) salida.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        ContData_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DiseñoContenedoresP2 newframe = new DiseñoContenedoresP2(p1.mostrarDatos(hubMostrar, Integer.parseInt(ID_text.getText())));
                newframe.setVisible(true);
            }
        });

        Cuantos_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Cant_text.setText(String.valueOf(p1.contenedoresPorPais((String) cuantosPais_box.getSelectedItem())));
            }
        });


        a1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                a2RadioButton.setSelected(false);
                a3RadioButton.setSelected(false);
            }
        });
        a2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                a1RadioButton.setSelected(false);
                a3RadioButton.setSelected(false);
            }
        });
        a3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                a1RadioButton.setSelected(false);
                a2RadioButton.setSelected(false);
            }
        });


        a1Hub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                a2Hub.setSelected(false);
                a3Hub.setSelected(false);
                hubMostrar = 0;
                Estad_text.setText(p1.toStringHUB(hubMostrar));
            }
        });
        a2Hub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                a1Hub.setSelected(false);
                a3Hub.setSelected(false);
                hubMostrar = 1;
                Estad_text.setText(p1.toStringHUB(hubMostrar));
            }
        });
        a3Hub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                a1Hub.setSelected(false);
                a2Hub.setSelected(false);
                hubMostrar = 2;
                Estad_text.setText(p1.toStringHUB(hubMostrar));
            }
        });

        JTextField pesoLimiteTextField = new JTextField();
        pesoLimiteTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double pesoLimite = Double.parseDouble(pesoLimiteTextField.getText());
            }
        });




    }

    public DiseñoContenedores(JLabel logo) throws HeadlessException {
        Logo = logo;
        Logo.setSize(150, 128);
    }
}







