package it.ispw.bookworlds.model.bookclub.state;

public abstract class BookClubAbstractState {
    public static BookClubAbstractState getInitialState(){return new BookClubNotFullState();}

    public abstract void markAsFull(BookClubStateMachineImpl stateMachine);
    public abstract void markAsNotFull(BookClubStateMachineImpl stateMachine);
    public abstract boolean isFull();
    public abstract boolean isNotFull();
}
