import {SNAKE_BODY} from'./snake.js'

export var POS_APPLE = {x:5,y:13}

export function update(){
    let posOcupada = false

    do{ //generem nova posició de la poma de manera random, però sempre mentre la posició random no sigui on hi ha un SNAKE_BODY
        posOcupada = false
        let randomX = Math.floor(Math.random() * 25) +1     //numero random entre (0 i 24) +1, per tant, num entre 1 i 25
        let randomY = Math.floor(Math.random() * 25) +1
        POS_APPLE.x = randomX
        POS_APPLE.y = randomY

        for(let i= 0; i<SNAKE_BODY.length; i++){
            if((POS_APPLE.x == SNAKE_BODY[i].x) && (POS_APPLE.y == SNAKE_BODY[i].y)){   //comprovem que no aparegui la poma sobre la serp
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

