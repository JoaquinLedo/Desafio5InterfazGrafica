/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author Joaquin
 */
import javax.swing.*;
import java.awt.*;

public class ConversorVista extends JFrame{
    private final JTextField txtEntrada = new JTextField();
    private final JTextField txtSalida  = new JTextField();
    private final JComboBox<String> combo = new JComboBox<>(
    new String[]{"Celsius → Fahrenheit", "Fahrenheit → Celsius"}
    );
    private final JButton btnConvertir = new JButton("Convertir");

    public ConversorVista() {
        super("Conversor de Temperatura (MVC)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 220);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JPanel centro = new JPanel(new GridLayout(3, 2, 8, 8));
        txtSalida.setEditable(false);

        centro.add(new JLabel("Conversión:"));
        centro.add(combo);
        centro.add(new JLabel("Valor de entrada:"));
        centro.add(txtEntrada);
        centro.add(new JLabel("Resultado:"));
        centro.add(txtSalida);

        add(centro, BorderLayout.CENTER);

        JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sur.add(btnConvertir);
        add(sur, BorderLayout.SOUTH);
    }

    public JTextField getTxtEntrada() { return txtEntrada; }
    public JTextField getTxtSalida()  { return txtSalida; }
    public JComboBox<String> getCombo() { return combo; }
    public JButton getBtnConvertir()  { return btnConvertir; }
}
