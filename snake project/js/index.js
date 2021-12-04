import { update as updateSnake, draw as drawSnake, score, gameOver } from './snake.js'
import { draw as drawApple } from './apple.js'
import { SNAKE_SPEED } from './difficulty.js'

//Game Constants
const initGameMusic = new Audio('sounds/music.mp3')

let lastDrawTime = 0
export const gameBoard = document.getElementById('GameBoard')
export const scoreBoard = document.getElementById('ScoreBoard')

function GameLoop(ctime) {
        initGameMusic.play()

    if (gameOver) {
        initGameMusic.pause()
        if (confirm('You lost. Press ok to restart')) {
            window.location = '/'       //aixo fara refresh de la pag automaticament
        }
        return
    }

    window.requestAnimationFrame(GameLoop)
    console.log("Score:" + score)
    if ((ctime - lastDrawTime) / 1000 < 1 / SNAKE_SPEED) return
    lastDrawTime = ctime

    update()
    draw()
}
window.requestAnimationFrame(GameLoop)


//Game Functions

function update() {
    //initGameMusic.play()
    updateSnake()
}
function draw() {
    gameBoard.innerHTML = ''    //anem borrant el camí per on passem, sense això la serp es infinita
    scoreBoard.innerHTML = ''
    drawScore(scoreBoard)
    drawSnake(gameBoard)
    drawApple(gameBoard)
}

function drawScore(ScoreBoard) {
    const scoreBoard = document.createElement('div')
    const scoreText = document.createTextNode("SCORE:  " + score)
    scoreBoard.appendChild(scoreText)
    ScoreBoard.appendChild(scoreBoard)
}