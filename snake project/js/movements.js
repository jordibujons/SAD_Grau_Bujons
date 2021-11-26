const gameOverSound = new Audio('sounds/GameOver.mp3')
const upDownSound = new Audio('sounds/upDown.mp3')  
const rightLeftSound = new Audio('sounds/rightLeft.mp3')  



const ARROW_UP = 38
const ARROW_DOWN = 40
const ARROW_LEFT = 37
const ARROW_RIGHT = 39
const SPACE_BAR = 32
export let moviment = {x:0,y:0}

window.addEventListener('keydown', function(event){

    switch (event.keyCode) {
        case ARROW_UP:
            upDownSound.play()
            moviment.x = 0;
            moviment.y = -1;
            console.log('AMUNT')
            break;

        case ARROW_DOWN:
            upDownSound.play()
            moviment.x = 0;
            moviment.y = 1;
            console.log('AVALL')
            break;
    
        case ARROW_LEFT:
            rightLeftSound.play()
            moviment.x = -1;
            moviment.y = 0;
            console.log('ESQUERRA')
            break;
        
        case ARROW_RIGHT:
            rightLeftSound.play()
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

