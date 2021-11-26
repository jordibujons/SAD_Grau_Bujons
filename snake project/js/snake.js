import {moviment} from'./movements.js'
import { POS_APPLE, pomaMenjada, update as updateApple, draw as drowApple } from './apple.js'

export const SNAKE_SPEED = 3
export const SNAKE_BODY = [
    {x: 13, y: 13}                      //posició (13,13) és el centre del taulell
]     
const foodSound = new Audio('sounds/biteApple.mp3')

export function update(){
    
    for (let i = SNAKE_BODY.length-2; i >= 0; i--) {    //l'última posició de la serp ens és igual pq desaperaixerà. Com que el nostre vector (serp) comença a 0, si fem (lenght -2) estarem apuntant a la penúltima pos de la serp 
        SNAKE_BODY[i+1] = {...SNAKE_BODY[i]}    //{... } fer nou objecte sense que agafi referències del vell
    }

    SNAKE_BODY[0].x += moviment.x
    SNAKE_BODY[0].y += moviment.y

    if (SNAKE_BODY[0].x == POS_APPLE.x && SNAKE_BODY[0].y == POS_APPLE.y) {     //comprovem que el cap coincideixi amb pos poma
        //pomaMenjada = true
        updateApple()        
        foodSound.play()  
    }
}
export function draw(GameBoard){
    SNAKE_BODY.forEach((seg, index)  => {
        
        const snakeElement = document.createElement('div')
        snakeElement.style.gridRowStart = seg.y
        snakeElement.style.gridColumnStart = seg.x
        if(index===0){
            snakeElement.classList.add('snakeHead')
        }else{
            snakeElement.classList.add('snakeBody')
        }
        GameBoard.appendChild(snakeElement)
        })
}


