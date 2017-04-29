package project;

import java.util.*;

/** Basically it manages different menus in the game
 * checks for one's return type, which is the users input, winning or losing
 * then acts upon these scenarios, loads the next state(menu)
 * @author Kunkli Richárd
 */
public class Manager {

    private Stack<State> states;
    private View view;

    public Manager() {
        states = new Stack<>();
        view = new View();
        Menu menu = new Menu(view);
        states.add(menu);
    }

    public void run() {
        while(true) {
            switch(states.peek().start()) {
                case START_GAME: 
                    states.push(new Game(view));
                    break;
                case EXIT_GAME:
                    System.exit(0);
                case CRASHED:
                    states.push(new End(Status.CRASHED, view));
                    break;
                case GAME_WON:
                    states.push(new End(Status.GAME_WON, view));
                case CONTINUE:
                    states.pop();
                    view.setStatus(Status.GAME);
                    view.repaint();
                    break;
                case PAUSE:
                    states.push(new Pause(view));
                    break;         
            }  
        }
    }

}