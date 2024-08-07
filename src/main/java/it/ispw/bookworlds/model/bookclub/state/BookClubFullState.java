package it.ispw.bookworlds.model.bookclub.state;

public class BookClubFullState extends BookClubAbstractState {
    @Override
    public void markAsFull(BookClubStateMachineImpl stateMachine) {
        //quando il club del libro è già pieno, se lo si vuole marcare come pieno, non è necessario eseguire alcuna azione
    }

    @Override
    public void markAsNotFull(BookClubStateMachineImpl stateMachine) {
        stateMachine.setState(new BookClubNotFullState());
    }

    @Override
    public boolean isFull() {
        return true;
    }

    @Override
    public boolean isNotFull() {
        return false;
    }
}
