import { moviment, BorderCrash, BodyCrash } from './movements.js'
import { POS_APPLE, update as updateApple } from './apple.js'
import { gameMode, snakeVelocity } from './difficulty.js'

const gameOverSound = new Audio('sounds/GameOver.mp3')
const foodSound = new Audio('sounds/biteApple.mp3')

export const LAST_MOVE = { x: 0, y: 0 } //guardem ultim moviment realitzat
export const SNAKE_BODY = [{ x: 13, y: 13 }]  //posició (13,13) és el centre del taulell
export var score = 0 //puntuació (número de pomes menjades)
export var gameOver = false


//constants per comparar quin ha estat l'últim moviment a growUpSnake()
const LAST_MOVE_DOWN = { x: 0, y: 1 }
const LAST_MOVE_UP = { x: 0, y: -1 }
const LAST_MOVE_RIGHT = { x: 1, y: 0 }
const LAST_MOVE_LEFT = { x: -1, y: 0 }




export function update() {

    for (let i = SNAKE_BODY.length - 2; i >= 0; i--) {    //l'última posició de la serp ens és igual pq desaperaixerà. Com que el nostre vector (serp) comença a 0, si fem (lenght -2) estarem apuntant a la penúltima pos de la serp 
        SNAKE_BODY[i + 1] = { ...SNAKE_BODY[i] }    //{... } fer nou objecte sense que agafi referències del vell
    }

    //quan xoquem amb borde o amb cos
    if (BorderCrash() == true || BodyCrash() == true) {
        //game over
        gameOver = true
        gameOverSound.play()

    } else {    //serp avança
        SNAKE_BODY[0].x += moviment.x
        SNAKE_BODY[0].y += moviment.y
        //guardem última posició
        LAST_MOVE.x = moviment.x
        LAST_MOVE.y = moviment.y
    }

    //quan mengem poma
    if (SNAKE_BODY[0].x == POS_APPLE.x && SNAKE_BODY[0].y == POS_APPLE.y) {     //comprovem que el cap coincideixi amb pos poma
        updateApple()
        growUpSnake()
        foodSound.play()
        score++
        snakeVelocity(score, gameMode) //augment progressiu velocitat
    }
}

export function draw(GameBoard) {
    SNAKE_BODY.forEach((seg, index) => {
        const snakeElement = document.createElement('div')
        snakeElement.style.gridRowStart = seg.y
        snakeElement.style.gridColumnStart = seg.x
        if (index === 0) {
            snakeElement.classList.add('snakeHead')
        } else {
            snakeElement.classList.add('snakeBody')
        }
        GameBoard.appendChild(snakeElement)
    })
}


function growUpSnake() {
    //creixem en la direcció oposada en la que hem menjat la poma
    if (LAST_MOVE.x == LAST_MOVE_DOWN.x && LAST_MOVE.y == LAST_MOVE_DOWN.y) { //mengem poma direcció abaix
        SNAKE_BODY.push({ x: SNAKE_BODY[SNAKE_BODY.length - 1].x, y: SNAKE_BODY[SNAKE_BODY.length - 1].y - 1 }) //creixem amun (restem y)
    }
    if (LAST_MOVE.x == LAST_MOVE_UP.x && LAST_MOVE.y == LAST_MOVE_UP.y) { //mengem poma direcció amunt
        SNAKE_BODY.push({ x: SNAKE_BODY[SNAKE_BODY.length - 1].x, y: SNAKE_BODY[SNAKE_BODY.length - 1].y + 1 }) //creixem abaix (sumem y)
    }
    if (LAST_MOVE.x == LAST_MOVE_RIGHT.x && LAST_MOVE.y == LAST_MOVE_RIGHT.y) { //mengem poma direcció dreta
        SNAKE_BODY.push({ x: SNAKE_BODY[SNAKE_BODY.length - 1].x - 1, y: SNAKE_BODY[SNAKE_BODY.length - 1].y }) //creixem esquerra (restem x)
    }
    if (LAST_MOVE.x == LAST_MOVE_LEFT.x && LAST_MOVE.y == LAST_MOVE_LEFT.y) { //mengem poma direcció esquerra
        SNAKE_BODY.push({ x: SNAKE_BODY[SNAKE_BODY.length - 1].x + 1, y: SNAKE_BODY[SNAKE_BODY.length - 1].y }) //creixem dreta (sumem x)
    }
}



