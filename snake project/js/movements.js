
const ARROW_UP = 38
const ARROW_DOWN = 40
const ARROW_LEFT = 37
const ARROW_RIGHT = 39
const SPACE_BAR = 32
let moviment = {x:0,y:0}

window.addEventListener('keydown', function(event){
    switch (event.keyCode) {
        case ARROW_UP:
            moviment.x = 0;
            moviment.y = -1;
            console.log('AMUNT')
            break;

        case ARROW_DOWN:
            moviment.x = 0;
            moviment.y = 1;
            console.log('AVALL')
            break;
    
        case ARROW_LEFT:
            moviment.x = -1;
            moviment.y = 0;
            console.log('ESQUERRA')
            break;
        
        case ARROW_RIGHT:
            moviment.x = 1;
            moviment.y = 0;
            console.log('DRETA')
            break;
        
        case SPACE_BAR:
            //CONDICIÓ PER FER PAUSE
            console.log('ESPAI')
            break;
        default:
            break;
    }
},true)

