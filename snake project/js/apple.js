import { SNAKE_BODY } from './snake.js'


const GRID_SIZE = 25
export var POS_APPLE = { x: 5, y: 13 } //getRandomApplePos()

export function update() {
    POS_APPLE = getRandomApplePos()
}

export function draw(GameBoard) {

    const appleElement = document.createElement('div')
    appleElement.style.gridRowStart = POS_APPLE.y
    appleElement.style.gridColumnStart = POS_APPLE.x
    appleElement.classList.add('apple')
    GameBoard.appendChild(appleElement)
}

function randomPositon() {
    return Math.floor(Math.random() * GRID_SIZE) + 1
}

function getRandomApplePos() {
    let posOcupada = false
    let newApplePos = { x: null, y: null }

    do { //generem nova posició de la poma de manera random, però sempre mentre la posició random no sigui on hi ha un SNAKE_BODY
        posOcupada = false
        let randomX = randomPositon()     //numero random entre (0 i 24) +1, per tant, num entre 1 i 25
        let randomY = randomPositon()
        newApplePos.x = randomX
        newApplePos.y = randomY

        for (let i = 0; i < SNAKE_BODY.length; i++) {
            if ((newApplePos.x == SNAKE_BODY[i].x) && (newApplePos.y == SNAKE_BODY[i].y)) {   //comprovem que no aparegui la poma sobre la serp
                posOcupada = true
                break
            }
        }

    } while (posOcupada == true)
    return newApplePos
}