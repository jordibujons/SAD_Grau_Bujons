import { update as updateSnake, draw as drawSnake, SNAKE_SPEED } from './snake.js'
import {keyUp} from'./movements.js'
//Game Constants

//let direction = {x: 0, y:0}
const foodSound = new Audio('sounds/BiteApple.mp3')
const gameOverSound = new Audio('sounds/GameOver.mp3')
const moveSound = new Audio('sounds/move.mp3')  

let lastDrawTime=0
const gameBoard = document.getElementById('GameBoard')

// var temps = 0;
// var loop = setInterval(GameLoop, 1000 / SNAKE_SPEED)
// function GameLoop(){
//     console.log("update")       //AQUI HAUREM DE FER QUE PARI DE REFRESCAR QUAN PERDEM LA PARTIDA
//     temps++;
//     if(temps==10){
//         alert("Fin")
//         clearInterval(loop)
//     }

//     update()
//     draw()
// }

function GameLoop(ctime){
        window.requestAnimationFrame(GameLoop)
        //console.log(ctime)
        if((ctime-lastDrawTime)/1000 < 1/SNAKE_SPEED) return
        lastDrawTime = ctime
        update()
        draw()
    

    if(keyUp){
        console.log("tecla amunt")
    }
}    
    window.requestAnimationFrame(GameLoop)


//Game Functions

function update(){
    updateSnake();

}
function draw(){
    gameBoard.innerHTML = ''
    drawSnake(gameBoard);

}

//Main logic coemça aqui