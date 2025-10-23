/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VistaTarea;

/**
 *
 * @author Joaquin
 */

import Tarea.Tarea;

import javax.swing.*;
import java.awt.*;

public class TareaVista extends JFrame {
    private final DefaultListModel<Tarea> modeloLista = new DefaultListModel<>();
    private final JList<Tarea> lista = new JList<>(modeloLista);
    private final JTextField txtNueva = new JTextField();
    private final JButton btnAgregar = new JButton("Agregar");
    private final JButton btnMarcar = new JButton("Marcar completada");

    public TareaVista() {
        super("Gestor de Tareas (MVC)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 360);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Superior: agregar tarea
        JPanel sup = new JPanel(new BorderLayout(6, 6));
        sup.add(new JLabel("Nueva tarea:"), BorderLayout.WEST);
        sup.add(txtNueva, BorderLayout.CENTER);
        sup.add(btnAgregar, BorderLayout.EAST);
        add(sup, BorderLayout.NORTH);

        // Centro: lista
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(lista), BorderLayout.CENTER);

        // Inferior: acciones
        JPanel inf = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        inf.add(btnMarcar);
        add(inf, BorderLayout.SOUTH);
    }

    // Getters para el controlador
    public DefaultListModel<Tarea> getModeloLista() { return modeloLista; }
    public JList<Tarea> getLista() { return lista; }
    public JTextField getTxtNueva() { return txtNueva; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnMarcar() { return btnMarcar; }
}
