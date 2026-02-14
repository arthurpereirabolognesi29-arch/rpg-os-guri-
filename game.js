let vidaMax = 150;
let vida = 150;
let forca = 25;
let escudo = 5;
let xp = 0;

let playerX = 1;
let playerY = 1;

let mapa = [
[1,1,1,1,1,1,1,1,1,1],
[1,0,0,0,0,0,0,0,0,1],
[1,0,1,1,0,0,0,0,0,1],
[1,0,0,0,0,0,0,0,0,1],
[1,0,0,0,1,1,0,0,0,1],
[1,0,0,0,0,0,0,0,0,1],
[1,1,1,1,1,1,1,1,1,1]
];

function escolherClasse(tipo) {

    if (tipo === "mago") {
        vidaMax = 120;
        forca = 40;
        escudo = 3;
    }

    if (tipo === "cavaleiro") {
        vidaMax = 200;
        forca = 25;
        escudo = 10;
    }

    if (tipo === "ladrao") {
        vidaMax = 140;
        forca = 35;
        escudo = 5;
    }

    vida = vidaMax;

    document.getElementById("classeEscolha").style.display = "none";
    document.getElementById("gameArea").style.display = "block";

    desenharMapa();
    atualizarStatus();
}

function desenharMapa() {

    let miniMapa = document.getElementById("miniMapa");
    miniMapa.innerHTML = "";

    for (let y = 0; y < mapa.length; y++) {
        for (let x = 0; x < mapa[y].length; x++) {

            let tile = document.createElement("div");
            tile.classList.add("tile");

            if (mapa[y][x] === 1) {
                tile.classList.add("parede");
            } else {
                tile.classList.add("chao");
            }

            if (x === playerX && y === playerY) {
                tile.classList.add("player");
            }

            miniMapa.appendChild(tile);
        }
    }
}

function mover(direcao) {

    let novoX = playerX;
    let novoY = playerY;

    if (direcao === "w") novoY--;
    if (direcao === "s") novoY++;
    if (direcao === "a") novoX--;
    if (direcao === "d") novoX++;

    if (
        mapa[novoY] !== undefined &&
        mapa[novoY][novoX] !== undefined &&
        mapa[novoY][novoX] === 0
    ) {
        playerX = novoX;
        playerY = novoY;
    }

    desenharMapa();
}

function atualizarStatus() {
    document.getElementById("status").innerHTML =
        "Vida: " + vida + "/" + vidaMax +
        "<br>Força: " + forca +
        "<br>Escudo: " + escudo +
        "<br>XP: " + xp;
}
