package it.ispw.bookworlds.model.bookclub.state;

public class BookClubNotFullState extends BookClubAbstractState{
    @Override
    public void markAsFull(BookClubStateMachineImpl stateMachine) {
        stateMachine.setState(new BookClubFullState());
    }

    @Override
    public void markAsNotFull(BookClubStateMachineImpl stateMachine) {
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isNotFull() {
        return true;
    }
}
