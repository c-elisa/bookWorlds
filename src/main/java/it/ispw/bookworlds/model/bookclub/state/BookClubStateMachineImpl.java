package it.ispw.bookworlds.model.bookclub.state;

public class BookClubStateMachineImpl implements BookClubStateMachine{
    private BookClubAbstractState currentState;

    public BookClubStateMachineImpl(){
        currentState = BookClubAbstractState.getInitialState();
    }

    public void setState(BookClubAbstractState state){this.currentState = state;}
    @Override
    public void markAsFull() {
        currentState.markAsFull(this);
    }

    @Override
    public void markAsNotFull() {
        currentState.markAsNotFull(this);
    }

    @Override
    public boolean isFull() {
        return currentState.isFull();
    }

    @Override
    public boolean isNotFull() {
        return currentState.isNotFull();
    }
}
