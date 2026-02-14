let vidaMax = 150;
let vida = 150;
let forca = 25;
let escudo = 5;
let xp = 0;

let playerX = 1;
let playerY = 1;

let mapa = [
"##########",
"#........#",
"#..##....#",
"#........#",
"#....##...#",
"#........#",
"##########"
];

function escolherClasse(tipo) {

    if (tipo === "mago") {
        vidaMax = 120;
        forca = 40;
    }

    if (tipo === "cavaleiro") {
        vidaMax = 200;
        forca = 25;
        escudo = 10;
    }

    if (tipo === "ladrao") {
        vidaMax = 140;
        forca = 35;
    }

    vida = vidaMax;

    document.getElementById("classeEscolha").style.display = "none";
    document.getElementById("gameArea").style.display = "block";

    desenharMapa();
    atualizarStatus();
}

function desenharMapa() {

    let mapaHTML = "";

    for (let y = 0; y < mapa.length; y++) {
        for (let x = 0; x < mapa[y].length; x++) {

            if (x === playerX && y === playerY) {
                mapaHTML += "P ";
            } else {
                mapaHTML += mapa[y][x] + " ";
            }

        }
        mapaHTML += "<br>";
    }

    document.getElementById("miniMapa").innerHTML = mapaHTML;
}

function mover(direcao) {

    let novoX = playerX;
    let novoY = playerY;

    if (direcao === "w") novoY--;
    if (direcao === "s") novoY++;
    if (direcao === "a") novoX--;
    if (direcao === "d") novoX++;

    if (mapa[novoY][novoX] !== "#") {
        playerX = novoX;
        playerY = novoY;
    }

    if (Math.random() < 0.3) {
        batalha();
    }

    desenharMapa();
    atualizarStatus();
}

function batalha() {

    log("😈 Inimigo apareceu!");

    let vidaInimigo = 100;

    while (vida > 0 && vidaInimigo > 0) {

        let dano = forca + Math.floor(Math.random() * 15);
        vidaInimigo -= dano;

        log("Você causou " + dano + " de dano!");

        if (vidaInimigo > 0) {
            let danoInimigo = 20 - escudo;
            if (danoInimigo < 0) danoInimigo = 0;

            vida -= danoInimigo;
            log("Inimigo causou " + danoInimigo + " de dano!");
        }
    }

    if (vida > 0) {
        xp += 50;
        log("👾 Inimigo derrotado! +50 XP");
    }
}

function atualizarStatus() {

    document.getElementById("status").innerHTML =
        "Vida: " + vida + "/" + vidaMax +
        "<br>Força: " + forca +
        "<br>Escudo: " + escudo +
        "<br>XP: " + xp;
}

function log(msg) {
    document.getElementById("log").innerHTML += msg + "<br>";
}
