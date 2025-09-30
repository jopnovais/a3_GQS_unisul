package main;

import javax.swing.SwingUtilities;

import view.TelaCadastro;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCadastro tela = new TelaCadastro();
            tela.setVisible(true);
        });
    }
}
