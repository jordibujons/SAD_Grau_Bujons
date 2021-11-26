export const SNAKE_SPEED = 1
const SNAKE_BODY = [
    {x: 9, y: 10},
    {x: 10, y: 10},
    {x: 11, y: 10}
]     //posició (10,10) és el centre del taulell

export function update(){
    for (let i = SNAKE_BODY.length-2; i >= 0; i--) {    //l'última posició de la serp ens és igual pq desaperaixerà. Com que el nostre vector (serp) comença a 0, si fem (lenght -2) estarem apuntant a la penúltima pos de la serp 
        SNAKE_BODY[i+1] = {...SNAKE_BODY[i]}    //{... } fer nou objecte sense que agafi referències del vell
    }

    // SNAKE_BODY[0].x += 0
    // SNAKE_BODY[0].y += 1
}
export function draw(GameBoard){
    SNAKE_BODY.forEach(seg => {
        const snakeElement = document.createElement('div')
        snakeElement.style.gridRowStart = seg.y
        snakeElement.style.gridColumnStart = seg.x
        snakeElement.classList.add('snakeBody')
        GameBoard.appendChild(snakeElement)
        })
}