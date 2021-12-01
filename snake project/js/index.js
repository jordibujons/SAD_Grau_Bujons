import { update as updateSnake, draw as drawSnake, SNAKE_SPEED, score } from './snake.js'
import { update as updateApple, draw as drawApple, pomaMenjada } from './apple.js'

//Game Constants
let lastDrawTime=0
export const gameBoard = document.getElementById('GameBoard')

function GameLoop(ctime){
        window.requestAnimationFrame(GameLoop)
        console.log("Score:" + score)
        if((ctime-lastDrawTime)/1000 < 1/SNAKE_SPEED) return
        lastDrawTime = ctime

        update()
        draw()       
}    
    window.requestAnimationFrame(GameLoop)


//Game Functions

function update(){
    updateSnake()
}
function draw(){
    gameBoard.innerHTML = ''    //anem borrant el camí per on passem, sense això la serp es infinita
    drawSnake(gameBoard)
    drawApple(gameBoard)

}