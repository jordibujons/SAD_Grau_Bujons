export const SNAKE_SPEED = 1
const SNAKE_BODY = [{x: 10, y: 10}]     //posició (10,10) és el centre del taulell

export function update(){
    console.log('update snake')

}
export function draw(GameBoard){
    SNAKE_BODY.forEach(segment => {
        const snakeElement = document.createElement('div')
        snakeElement.style.gridRowStart = segment.x
        snakeElement.style.gridColStart = segment.y
        snakeElement.classList.add('snakeBody')
        GameBoard.appendChild(snakeElement)
        })
}