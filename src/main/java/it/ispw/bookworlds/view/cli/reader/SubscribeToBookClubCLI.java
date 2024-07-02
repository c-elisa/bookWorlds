package it.ispw.bookworlds.view.cli.reader;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.bean.GenresListBean;
import it.ispw.bookworlds.controller.graphic.cli.reader.SubscribeToBookClubGraphicController;
import it.ispw.bookworlds.exceptions.InvalidSelectionException;
import it.ispw.bookworlds.exceptions.NoGenresSelectedException;
import it.ispw.bookworlds.model.Genre;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

import java.util.ArrayList;

public class SubscribeToBookClubCLI extends GeneralPageCLI implements PageCLI {
    private GenresListBean selectedGenres = new GenresListBean();
    private ArrayList<String> allGenres = new ArrayList<>();
    private SubscribeToBookClubGraphicController controller = new SubscribeToBookClubGraphicController();

    @Override
    public void display() {

        printTitle();
        Printer.println("\n--- ISCRIVITI A UN CLUB DEL LIBRO ---");

        allGenres = controller.getGenresList().getGenres();
        Printer.println("Generi disponibili:");
        printList(allGenres);
        selectGenres();

        BookClubBean selectedBookClub;
        ArrayList<BookClubBean> bookClubs = controller.findBookClubs(selectedGenres);
        showBookClubs(bookClubs);

        //seleziona un club del libro dalla lista
        int selection;

        while(true) {
            try {
                selection = requestInt("Selezionare un club del libro: ");
                if(selection > bookClubs.size()) throw new InvalidSelectionException();
                selectedBookClub = bookClubs.get(selection - 1);
            }catch(InvalidSelectionException e) {
                printErrorMessage(e.getLocalizedMessage());
            }
        }

    }

    private void selectGenres(){
        int selection;

        Printer.println("[0] Fine selezione");
        Printer.println("Selezionare fino a 4 generi.");
        while(selectedGenres.getGenres().size() < 4){
            try{
                selection = requestInt("Seleziona: ");
                if(selection > allGenres.size()) throw new InvalidSelectionException();
                if(selection == 0 && selectedGenres.getGenres().isEmpty()) throw new NoGenresSelectedException();
                if(selection == 0) break;
                selectedGenres.getGenres().add(allGenres.get(selection - 1));
            }catch(InvalidSelectionException | NoGenresSelectedException e){
                printErrorMessage(e.getLocalizedMessage());
            }
        }
    }

    private void showBookClubs(ArrayList<BookClubBean> bookClubBeans){
        Printer.println("Club del libro trovati\n");

        for(BookClubBean bookClub: bookClubBeans){
            Printer.print("[" + (bookClubBeans.indexOf(bookClub) + 1) + "] Nome: " + bookClub.getName() + " Generi: ");
            for(Genre genre: bookClub.getGenres()){
                Printer.print(genre.toString() + ", ");
            }
            Printer.println("\n----------------------------------------");
        }
    }

    private void showBookClubInfo(BookClubBean bookClub){
        Printer.println("Nome: " + bookClub.getName() + " - " + bookClub.getNumberOfSubscribers() + " iscritti - " + "Proprietario: " + bookClub.getOwner());
        for(Genre genre: bookClub.getGenres()){
            Printer.print(genre.toString() + ", ");
        }
        Printer.println("\n----------------------------------------");
    }
    }
}
