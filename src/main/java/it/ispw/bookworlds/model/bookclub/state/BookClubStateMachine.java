package it.ispw.bookworlds.model.bookclub.state;

public interface BookClubStateMachine {
    public void markAsFull();
    public void markAsNotFull();
    public boolean isFull();
    public boolean isNotFull();
}
