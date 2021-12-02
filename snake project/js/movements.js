import { SNAKE_BODY, LAST_MOVE } from "./snake.js"

const upDownSound = new Audio('sounds/upDown.mp3')
const rightLeftSound = new Audio('sounds/rightLeft.mp3')

const ARROW_UP = 38
const ARROW_DOWN = 40
const ARROW_LEFT = 37
const ARROW_RIGHT = 39
const SPACE_BAR = 32

export let moviment = { x: 0, y: 0 }

window.addEventListener('keydown', function (event) {   //utilitzem window no element

    switch (event.keyCode) {
        case ARROW_UP:
            if ((SNAKE_BODY.length > 1) && (LAST_MOVE.x == 0 && LAST_MOVE.y == 1)) {
                //moviment no valid (anar en direcció oposada a la actual)
                break;
            }
            moviment.x = 0;
            moviment.y = -1;
            console.log('AMUNT')
            if (LAST_MOVE.x != 0 || LAST_MOVE.y != -1) { //només activem el so quan canviem de direcció (si pitxem més vegades la mateixa fletxa només sonarà el primer cop)
                upDownSound.play()
            }
            break;

        case ARROW_DOWN:
            if ((SNAKE_BODY.length > 1) && (LAST_MOVE.x == 0 && LAST_MOVE.y == -1)) {
                //moviment no valid (anar en direcció oposada a la actual)
                break;
            }
            moviment.x = 0;
            moviment.y = 1; 
            console.log('AVALL')
            if (LAST_MOVE.x != 0 || LAST_MOVE.y != 1) { //només activem el so quan canviem de direcció (si pitxem més vegades la mateixa fletxa només sonarà el primer cop)
                upDownSound.play()
            }
            break;

        case ARROW_LEFT:
            if ((SNAKE_BODY.length > 1) && (LAST_MOVE.x == 1 && LAST_MOVE.y == 0)) {
                //moviment no valid (anar en direcció oposada a la actual)
                break;
            }
            moviment.x = -1;
            moviment.y = 0;
            console.log('ESQUERRA')
            if (LAST_MOVE.x != -1 || LAST_MOVE.y != 0) { //només activem el so quan canviem de direcció (si pitxem més vegades la mateixa fletxa només sonarà el primer cop)
                rightLeftSound.play()
            }
            break;

        case ARROW_RIGHT:
            if ((SNAKE_BODY.length > 1) && (LAST_MOVE.x == -1 && LAST_MOVE.y == 0)) {
                //moviment no valid (anar en direcció oposada a la actual)
                break;
            }
            moviment.x = 1;
            moviment.y = 0;
            console.log('DRETA')
            if (LAST_MOVE.x != 1 || LAST_MOVE.y != 0) { //només activem el so quan canviem de direcció (si pitxem més vegades la mateixa fletxa només sonarà el primer cop)
                rightLeftSound.play()
            }
            break;

        case SPACE_BAR:
            //CONDICIÓ PER FER PAUSE
            console.log('ESPAI')
            break;
        default:
            break;
    }
}, true)

export function BorderCrash() {
    let crashed = false
    //comprobem on tenim el cap i quin és el pròxim moviment que volem fer
    if ((SNAKE_BODY[0].x == 1) && (moviment.x == -1) || (SNAKE_BODY[0].x == 25) && (moviment.x == 1) || (SNAKE_BODY[0].y == 1) && (moviment.y == -1) || (SNAKE_BODY[0].y == 25) && (moviment.y == 1)) {
        //has xocat amb el borde
        console.log('BORDERCRASH')
        crashed = true
    }
    return crashed

}

export function BodyCrash() {       //JA VA LOKOOOO HHAHAH

    let crashed = false
    //per mirar si el cap està apunt de xocar amb el seu cos, recorrem SNAKE_BODY a partir de la posició 1 (no 0, el cap no pot xocar amb ell mateix)
    for (let i = 1; i < SNAKE_BODY.length; i++) {
        //comprobem si en direcció DRETA tenim un SNAKE_BODY (següent posició x, mateixa y) i el pròxim moviment és DRETA
        if ((((SNAKE_BODY[0].x + 1) == SNAKE_BODY[i].x) && (SNAKE_BODY[0].y == SNAKE_BODY[i].y)) && moviment.x == 1) {
            crashed = true
            console.log('BODYCRASH!!!!!!!!!!!')
            break;
        }
        //comprobem si en direcció ESQUERRA tenim un SNAKE_BODY (següent posició x, mateixa y) i el pròxim moviment és ESQUERRA
        if ((((SNAKE_BODY[0].x - 1) == SNAKE_BODY[i].x) && (SNAKE_BODY[0].y == SNAKE_BODY[i].y)) && moviment.x == -1) {
            crashed = true
            console.log('BODYCRASH!!!!!!!!!!!')
            break;
        }
        //comprobem si en direcció ABAIX tenim un SNAKE_BODY (mateixa x, següent posició y) i el pròxim moviment és ABAIX
        if (((SNAKE_BODY[0].x == SNAKE_BODY[i].x) && (SNAKE_BODY[0].y + 1) == SNAKE_BODY[i].y) && moviment.y == 1) {
            crashed = true
            console.log('BODYCRASH!!!!!!!!!!!')
            break;
        }
        //comprobem si en direcció AMUNT tenim un SNAKE_BODY (mateixa x, següent posició y) i el pròxim moviment és AMUNT
        if (((SNAKE_BODY[0].x == SNAKE_BODY[i].x) && (SNAKE_BODY[0].y - 1) == SNAKE_BODY[i].y) && moviment.y == -1) {
            crashed = true
            console.log('BODYCRASH!!!!!!!!!!!')
            break;
        }
    }

    return crashed
}

    

        // let crashed = false
        // for(let i=1; i<SNAKE_BODY.length; i++ ){
        //     if((SNAKE_BODY[0].x == SNAKE_BODY[i].x) && (SNAKE_BODY[0].y == SNAKE_BODY[i].y)){
        //         console.log('bodyCrash')
        //         crashed = true
        //     }
        // }
        // return crashed
    
    

