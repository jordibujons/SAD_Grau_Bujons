import {SNAKE_BODY} from'./snake.js'

export var POS_APPLE = {x:5,y:13}
export var pomaMenjada = false

export function update(){
    let posOcupada = false
    pomaMenjada = false

    do{
        posOcupada = false
        let randomX = Math.floor(Math.random() * 25)
        let randomY = Math.floor(Math.random() * 25)
        POS_APPLE.x = randomX
        POS_APPLE.y = randomY

        for(let i= 0; i<SNAKE_BODY.length; i++){
            if((POS_APPLE.x == SNAKE_BODY[i].x) && (POS_APPLE.y == SNAKE_BODY[i].y)){
                posOcupada = true
                break        
            }   
        }

    }while(posOcupada==true)
}
    

export function draw(GameBoard){
        
        const appleElement = document.createElement('div')
        appleElement.style.gridRowStart = POS_APPLE.y
        appleElement.style.gridColumnStart = POS_APPLE.x            
        appleElement.classList.add('apple')
        GameBoard.appendChild(appleElement)
}