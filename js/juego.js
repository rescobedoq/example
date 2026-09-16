const palabras = [
    "JAVASCRIPT",
    "PROGRAMACION",
    "COMPUTADORA",
    "INTERNET",
    "ALGORITMO",
    "SOFTWARE",
    "HTML",
    "SERVIDOR",
    "VARIABLE",
    "FUNCION"
];

let palabraSecreta;
let palabraOculta;
let letrasUsadas;
let intentos;

const dibujos = [
`
  +---+
  |   |
      |
      |
      |
      |
=========`,
`
  +---+
  |   |
  O   |
      |
      |
      |
=========`,
`
  +---+
  |   |
  O   |
  |   |
      |
      |
=========`,
`
  +---+
  |   |
  O   |
 /|   |
      |
      |
=========`,
`
  +---+
  |   |
  O   |
 /|\\  |
      |
      |
=========`,
`
  +---+
  |   |
  O   |
 /|\\  |
 /    |
      |
=========`,
`
  +---+
  |   |
  O   |
 /|\\  |
 / \\  |
      |
=========`
];

function nuevoJuego() {

    // Seleccionar una palabra aleatoria
    palabraSecreta =
        palabras[Math.floor(Math.random() * palabras.length)];

    // Crear palabra oculta
    palabraOculta = [];

    for (let letra of palabraSecreta) {
        palabraOculta.push("_");
    }

    letrasUsadas = [];
    intentos = 6;

    document.getElementById("mensaje").textContent = "";

    mostrarPalabra();
    mostrarTeclado();
    actualizarJuego();
}

function mostrarPalabra() {

    document.getElementById("palabra").textContent =
        palabraOculta.join(" ");
}

function mostrarTeclado() {

    const teclado = document.getElementById("teclado");

    teclado.innerHTML = "";

    const letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    for (let letra of letras) {

        const boton = document.createElement("button");

        boton.textContent = letra;

        boton.onclick = function () {
            comprobarLetra(letra);
        };

        teclado.appendChild(boton);
    }
}

function comprobarLetra(letra) {

    // Evitar repetir letras
    if (letrasUsadas.includes(letra)) {
        return;
    }

    letrasUsadas.push(letra);

    if (palabraSecreta.includes(letra)) {

        // Mostrar la letra
        for (let i = 0; i < palabraSecreta.length; i++) {

            if (palabraSecreta[i] === letra) {
                palabraOculta[i] = letra;
            }
        }

    } else {

        // Restar intento
        intentos--;
    }

    actualizarJuego();

    comprobarResultado();
}

function actualizarJuego() {

    document.getElementById("palabra").textContent =
        palabraOculta.join(" ");

    document.getElementById("intentos").textContent =
        intentos;

    document.getElementById("letrasUsadas").textContent =
        letrasUsadas.join(" - ");

    document.getElementById("dibujo").textContent =
        dibujos[6 - intentos];

    // Desactivar las letras utilizadas
    const botones =
        document.querySelectorAll("#teclado button");

    botones.forEach(boton => {

        if (letrasUsadas.includes(boton.textContent)) {
            boton.disabled = true;
        }

    });
}

function comprobarResultado() {

    // Victoria
    if (!palabraOculta.includes("_")) {

        document.getElementById("mensaje").textContent =
            "🎉 ¡Ganaste! La palabra era " + palabraSecreta;

        desactivarTeclado();

        return;
    }

    // Derrota
    if (intentos === 0) {

        document.getElementById("mensaje").textContent =
            "😢 ¡Perdiste! La palabra era " + palabraSecreta;

        desactivarTeclado();
    }
}

function desactivarTeclado() {

    const botones =
        document.querySelectorAll("#teclado button");

    botones.forEach(boton => {
        boton.disabled = true;
    });
}

// Iniciar el juego
nuevoJuego();
