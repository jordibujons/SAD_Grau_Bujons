import { update as updateSnake, draw as drawSnake, SNAKE_SPEED } from './snake.js'
import { update as updateApple, draw as drawApple, pomaMenjada } from './apple.js'

//Game Constants
let lastDrawTime=0
export const gameBoard = document.getElementById('GameBoard')

function GameLoop(ctime){
        window.requestAnimationFrame(GameLoop)
        //console.log(ctime)
        if((ctime-lastDrawTime)/1000 < 1/SNAKE_SPEED) return
        lastDrawTime = ctime

        update()
        draw()       
}    
    window.requestAnimationFrame(GameLoop)


//Game Functions

function update(){
    updateSnake();
    // if(pomaMenjada==true){
    //     updateApple();
    // }

}
function draw(){
    gameBoard.innerHTML = ''
    drawSnake(gameBoard);
    drawApple(gameBoard);

}

//Main logic coemça aqui