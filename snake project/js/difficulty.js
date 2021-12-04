export var gameMode
export const EASY_MODE = 1
export const NORMAL_MODE = 2
export const HARD_MODE = 3
export var SNAKE_SPEED//velocitat de joc


var menu = document.getElementById('menu');
var game = document.getElementById('game');

document.getElementById('easy').addEventListener('click', function (e) {
    e.preventDefault();
    gameMode = 1;
    SNAKE_SPEED = 5
    menu.style.display = 'none';
    game.style.display = 'flex';
    console.log('SS=easy')
});
document.getElementById('normal').addEventListener('click', function (e) {
    e.preventDefault();
    gameMode = 2;
    SNAKE_SPEED = 7
    menu.style.display = 'none';
    game.style.display = 'flex';
    console.log('SS=normal')

});
document.getElementById('hard').addEventListener('click', function (e) {
    e.preventDefault();
    gameMode = 3;
    SNAKE_SPEED = 10
    menu.style.display = 'none';
    game.style.display = 'flex';
    console.log('SS=hard')

});


//funcio que incrementa en 1 la velocitat de la serp cada 'x' pomes menjades
export function snakeVelocity(score, gameMode) {

    switch (gameMode) {
        case EASY_MODE:
            if ((score > 0) && (score % 7) == 0) SNAKE_SPEED++
            break;
        case NORMAL_MODE:
            if ((score > 0) && (score % 5) == 0) SNAKE_SPEED++
            break;
        case HARD_MODE:
            if ((score > 0) && (score % 3) == 0) SNAKE_SPEED++
            break;
        default:
            break;
    }
    console.log('SNAKE_SPEED: ' + SNAKE_SPEED)
}