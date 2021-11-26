export const SNAKE_SPEED = 1
const SNAKE_BODY = [
    {x: 9, y: 10},
    {x: 10, y: 10},
    {x: 11, y: 10}
]     //posició (10,10) és el centre del taulell

export function update(){
    for (let i = SNAKE_BODY.length; i <= 0; i--) {
        SNAKE_BODY[i+1] = SNAKE_BODY[i]
    }

    SNAKE_BODY[0].x += 1
    SNAKE_BODY[0].y += 0
}
export function draw(GameBoard){
    SNAKE_BODY.forEach(segment => {
        const snakeElement = document.createElement('div')
        snakeElement.style.gridRowStart = segment.y
        snakeElement.style.gridColumnStart = segment.x
        snakeElement.classList.add('snakeBody')
        GameBoard.appendChild(snakeElement)
        })
}