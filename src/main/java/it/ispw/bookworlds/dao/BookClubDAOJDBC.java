package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.exceptions.NoBookClubsFoundException;
import it.ispw.bookworlds.factory.ConnectionFactory;
import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;
import it.ispw.bookworlds.utils.Printer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookClubDAOJDBC implements BookClubDAO{
    private static BookClubDAOJDBC instance = null;
    private static final String GENRES_QUERY = "SELECT genre FROM book_club_genres WHERE bookclub=?";
    Connection connection;

    private BookClubDAOJDBC(){
        connection = ConnectionFactory.getInstance();
    }

    public static BookClubDAOJDBC getInstance(){
        if(instance == null) instance = new BookClubDAOJDBC();
        return instance;
    }

    @Override
    public void createBookClub(BookClubEntity bookClub) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO book_club VALUES (?,?,?,?)");
            statement.setString(1, bookClub.getName());
            statement.setString(2, bookClub.getOwner());
            statement.setInt(3, bookClub.getNumberOfSubscribers());
            statement.setInt(4, bookClub.getCapacity());
            statement.executeQuery();

            PreparedStatement genreStatement = connection.prepareStatement("INSERT INTO book_club_genres VALUES (?,?)");

            for(Genre g: bookClub.getGenres()) {
                genreStatement.setString(1, bookClub.getName());
                genreStatement.setString(2, String.valueOf(g));
                genreStatement.addBatch();
            }
            genreStatement.executeBatch();
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    @Override
    public List<BookClubEntity> getBookClubsByGenres(List<Genre> genres) throws NoBookClubsFoundException {
        // Lista di club del libro da restituire
        List<BookClubEntity> bookClubs = new ArrayList<BookClubEntity>();

        String query = "SELECT name, owner, numberOfSubscribers, capacity FROM book_club join book_club_genres ON name=bookclub WHERE ";
        for(int i=0; i<genres.size() - 1; i++){
            query = query.concat("genre=? OR ");
        }
        query = query.concat("genre=?");

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            for(int i=0; i< genres.size(); i++){
                statement.setString(i+1, String.valueOf(genres.get(i)));
            }
            ResultSet rs = statement.executeQuery();

            bookClubs = createListFromResultSet(rs);
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
        if(bookClubs.isEmpty()) throw new NoBookClubsFoundException();
        return bookClubs;
    }

    @Override
    public List<BookClubEntity> getBookClubsByOwner(String name) {
        // Lista di club del libro da restituire
        List<BookClubEntity> bookClubs = new ArrayList<BookClubEntity>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT name, owner, numberOfSubscribers, capacity FROM book_club WHERE owner=?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            bookClubs = createListFromResultSet(rs);
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return bookClubs;
    }

    @Override
    public BookClubEntity getBookClubByName(String name) {
        BookClubEntity bookClub = null;

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT name, owner, numberOfSubscribers, capacity FROM book_club WHERE name=?");
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();

            bookClub = createListFromResultSet(rs).getFirst();
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return bookClub;
    }

    @Override
    public void addSubscriber(String name) {

        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE book_club SET numberOfSubscribers=? WHERE name=?");
            statement.setInt(1, getNumberOfSubscribers(name) + 1);
            statement.setString(2, name);
            statement.execute();

        }catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    @Override
    public void removeSubscriber(String name) {

        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE book_club SET numberOfSubscribers=? WHERE name=?");
            statement.setInt(1, getNumberOfSubscribers(name) - 1);
            statement.setString(2, name);
            statement.execute();

        }catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    private int getNumberOfSubscribers(String name){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT numberOfSubscribers FROM book_club WHERE name=?");
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) return rs.getInt("numberOfSubscribers");

        }catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return -1;
    }

    private List<BookClubEntity> createListFromResultSet(ResultSet rs) throws SQLException {
        List<BookClubEntity> bookClubs = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GENRES_QUERY);

        while(rs.next()){
            bookClubs.add(new BookClubEntity(rs.getString(BookClubAttributesOrder.NAME.getincrementedIndex()), new ArrayList<Genre>(), rs.getInt(BookClubAttributesOrder.NUMBER_OF_SUBSCRIBERS.getincrementedIndex()),
                    rs.getInt(BookClubAttributesOrder.CAPACITY.getincrementedIndex()), rs.getString(BookClubAttributesOrder.OWNER.getincrementedIndex())));
        }

        for(BookClubEntity bookClub: bookClubs){
            statement.setString(1, bookClub.getName());
            rs = statement.executeQuery();

            while(rs.next()){
                bookClub.getGenres().add(Genre.valueOf(rs.getString(1)));
            }
        }

        return bookClubs;
    }
}
