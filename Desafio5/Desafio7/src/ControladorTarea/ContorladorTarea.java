/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorTarea;

/**
 *
 * @author Joaquin
 */
import VistaTarea.TareaVista;
import Tarea.Tarea;

import javax.swing.*;

public class ContorladorTarea {
    private final TareaVista view;

    public ContorladorTarea(TareaVista view) {
        this.view = view;

        view.getBtnAgregar().addActionListener(e -> agregar());
        view.getTxtNueva().addActionListener(e -> agregar());
        view.getBtnMarcar().addActionListener(e -> marcar());
        view.setVisible(true);
    }

    private void agregar() {
        String nombre = view.getTxtNueva().getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Escribí un nombre de tarea",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        view.getModeloLista().addElement(new Tarea(nombre));
        view.getTxtNueva().setText("");
        view.getTxtNueva().requestFocus();
    }

    private void marcar() {
        int idx = view.getLista().getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(view, "Seleccioná una tarea",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Tarea t = view.getModeloLista().getElementAt(idx);
        t.setCompletada(!t.isCompletada());   
        view.getModeloLista().set(idx, t);    
    }
}
