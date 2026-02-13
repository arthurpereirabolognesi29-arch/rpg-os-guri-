import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RPGGame extends JFrame {

    private int vidaJogador;
    private int ataqueJogador;
    private int vidaNPC = 120;
    private int ataqueNPC = 25;

    private JTextArea areaTexto;
    private JButton btnAtacar;
    private JButton btnEspecial;

    private Random random = new Random();

    public RPGGame() {

        setTitle("RPG Batalha Hostil");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        escolherPersonagem();
        criarInterface();

        setVisible(true);
    }

    private void escolherPersonagem() {

        String[] personagens = {"Guerreiro", "Mago", "Assassino"};

        String escolha = (String) JOptionPane.showInputDialog(
                null,
                "Escolha seu personagem:",
                "Seleção",
                JOptionPane.QUESTION_MESSAGE,
                null,
                personagens,
                personagens[0]
        );

        if (escolha == null) System.exit(0);

        switch (escolha) {
            case "Guerreiro":
                vidaJogador = 150;
                ataqueJogador = 20;
                break;
            case "Mago":
                vidaJogador = 100;
                ataqueJogador = 30;
                break;
            case "Assassino":
                vidaJogador = 90;
                ataqueJogador = 35;
                break;
        }
    }

    private void criarInterface() {

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Arial", Font.BOLD, 14));
        areaTexto.setBackground(Color.BLACK);
        areaTexto.setForeground(Color.GREEN);

        btnAtacar = new JButton("Atacar");
        btnEspecial = new JButton("Ataque Especial");

        btnAtacar.addActionListener(e -> atacar());
        btnEspecial.addActionListener(e -> ataqueEspecial());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAtacar);
        painelBotoes.add(btnEspecial);

        add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        atualizarTexto("⚔️ Um NPC extremamente hostil apareceu!");
    }

    private void atacar() {
        int dano = ataqueJogador + random.nextInt(10);
        vidaNPC -= dano;

        atualizarTexto("Você causou " + dano + " de dano!");

        turnoNPC();
    }

    private void ataqueEspecial() {
        int dano = ataqueJogador + random.nextInt(25);
        vidaNPC -= dano;

        atualizarTexto("💥 ATAQUE ESPECIAL causou " + dano + " de dano!");

        turnoNPC();
    }

    private void turnoNPC() {

        if (vidaNPC <= 0) {
            atualizarTexto("🏆 Você venceu o NPC!");
            desativarBotoes();
            return;
        }

        int danoNPC = ataqueNPC + random.nextInt(15);
        vidaJogador -= danoNPC;

        atualizarTexto("😈 NPC atacou violentamente e causou " + danoNPC + " de dano!");

        if (vidaJogador <= 0) {
            atualizarTexto("💀 Você foi derrotado!");
            desativarBotoes();
        }
    }

    private void atualizarTexto(String mensagem) {
        areaTexto.append("\n" + mensagem);
        areaTexto.append("\nSua vida: " + vidaJogador + " | Vida NPC: " + vidaNPC + "\n");
    }

    private void desativarBotoes() {
        btnAtacar.setEnabled(false);
        btnEspecial.setEnabled(false);
    }

    public static void main(String[] args) {
        new RPGGame();
    }
}
