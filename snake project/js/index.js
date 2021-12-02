import { update as updateSnake, draw as drawSnake, score, drawScore } from './snake.js'
import { draw as drawApple} from './apple.js'

//Game Constants
let lastDrawTime=0
export const gameBoard = document.getElementById('GameBoard')
export const scoreBoard = document.getElementById('ScoreBoard')
export var SNAKE_SPEED = 5 //velocitat de joc

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
    scoreBoard.innerHTML = ''
    drawScore(scoreBoard)
    drawSnake(gameBoard)
    drawApple(gameBoard)

}