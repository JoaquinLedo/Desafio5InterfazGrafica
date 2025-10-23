/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package desafio7;

/**
 *
 * @author Joaquin
 */


import Vista.ConversorVista;
import Conversor.Conversor;
import ConversorControlador.ConversorControlador;

import VistaTarea.TareaVista;
import ControladorTarea.ContorladorTarea;

import javax.swing.*;
import java.awt.*;

public class Desafio7 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Desafio7::lanzar);
    }

    private static void lanzar() {
        JFrame f = new JFrame("Desafío 7 — Lanzador");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(360, 180);
        f.setLocationRelativeTo(null);
        f.setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnConversor = new JButton("Ejercicio 1 — Conversor de Temperatura (MVC)");
        JButton btnTareas    = new JButton("Ejercicio 2 — Gestor de Tareas (MVC)");

        btnConversor.addActionListener(e -> {
            ConversorVista view = new ConversorVista();
            Conversor model = new Conversor();
            new ConversorControlador(model, view);
        });

        btnTareas.addActionListener(e -> {
            TareaVista view = new TareaVista();
            new ContorladorTarea(view);
        });

        f.add(btnConversor);
        f.add(btnTareas);
        f.setVisible(true);
    }
}
