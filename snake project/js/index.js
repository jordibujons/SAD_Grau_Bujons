import { update as updateSnake, draw as drawSnake, SNAKE_SPEED } from './snake.js'

//Game Constants
let direction = {x: 0, y:0}
const foodSound = new Audio('sounds/BiteApple.mp3')
const gameOverSound = new Audio('sounds/GameOver.mp3')
const moveSound = new Audio('sounds/move.mp3')



var temps = 0;
var loop = setInterval(GameLoop, 1000 / SNAKE_SPEED)
function GameLoop(){
    console.log("update")       //AQUI HAUREM DE FER QUE PARI DE REFRESCAR QUAN PERDEM LA PARTIDA
    temps++;
    if(temps==10){
        alert("Fin")
        clearInterval(loop)
    }

    update()
    draw()
}

//Game Functions

function update(){
    updateSnake();

}
function draw(){
    drawSnake();

}
//Main logic coemça aqui