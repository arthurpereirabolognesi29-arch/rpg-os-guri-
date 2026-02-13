import java.util.Random;
import java.util.Scanner;

public class RPGOsGuri {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static int vidaJogador;
    static int ataqueJogador;
    static int defesaJogador;
    static int vidaNPC = 200;
    static int ataqueNPC = 35;

    public static void main(String[] args) {

        System.out.println("=== ⚔️ RPG OS GURI ⚔️ ===");
        escolherPersonagem();

        while (vidaJogador > 0 && vidaNPC > 0) {

            System.out.println("\nSua vida: " + vidaJogador + " | Vida NPC: " + vidaNPC);
            System.out.println("1 - Atacar");
            System.out.println("2 - Defesa");
            System.out.println("3 - Ataque Especial");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    atacar();
                    break;
                case 2:
                    defender();
                    break;
                case 3:
                    especial();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (vidaNPC > 0) turnoNPC();
        }

        if (vidaJogador <= 0) {
            System.out.println("\n💀 Você foi destruído pelo NPC!");
        } else {
            System.out.println("\n🏆 VOCÊ DERROTOU O NPC HOSTIL!");
        }
    }

    static void escolherPersonagem() {

        System.out.println("Escolha seu personagem:");
        System.out.println("1 - Guerreiro (vida alta)");
        System.out.println("2 - Mago (ataque alto)");
        System.out.println("3 - Assassino (crítico alto)");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                vidaJogador = 250;
                ataqueJogador = 25;
                defesaJogador = 10;
                break;
            case 2:
                vidaJogador = 180;
                ataqueJogador = 40;
                defesaJogador = 5;
                break;
            case 3:
                vidaJogador = 150;
                ataqueJogador = 35;
                defesaJogador = 8;
                break;
            default:
                System.out.println("Escolha inválida. Guerreiro selecionado.");
                vidaJogador = 250;
                ataqueJogador = 25;
                defesaJogador = 10;
        }
    }

    static void atacar() {
        int dano = ataqueJogador + random.nextInt(20);

        // Chance de crítico
        if (random.nextInt(100) < 25) {
            dano *= 2;
            System.out.println("💥 CRÍTICO!");
        }

        vidaNPC -= dano;
        System.out.println("Você causou " + dano + " de dano!");
    }

    static void especial() {
        int dano = ataqueJogador + random.nextInt(50);
        vidaNPC -= dano;
        vidaJogador -= 10; // custo de energia

        System.out.println("🔥 Ataque Especial causou " + dano + " de dano!");
        System.out.println("Você perdeu 10 de vida pelo esforço!");
    }

    static void defender() {
        int cura = defesaJogador + random.nextInt(15);
        vidaJogador += cura;
        System.out.println("🛡️ Você recuperou " + cura + " de vida!");
    }

    static void turnoNPC() {
        int dano = ataqueNPC + random.nextInt(25);

        // NPC também pode critar
        if (random.nextInt(100) < 30) {
            dano *= 2;
            System.out.println("😈 NPC deu CRÍTICO!");
        }

        vidaJogador -= dano;
        System.out.println("NPC atacou e causou " + dano + " de dano!");
    }
}
