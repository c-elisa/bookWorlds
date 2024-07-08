package it.ispw.bookworlds.dao;

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

    private BookClubDAOJDBC(){}

    public static BookClubDAOJDBC getInstance(){
        if(instance == null) instance = new BookClubDAOJDBC();
        return instance;
    }

    @Override
    public void createBookClub(BookClubEntity bookClub) {
        Connection connection = ConnectionFactory.getInstance();

        try{
            Statement statement = connection.createStatement();

            statement.addBatch("INSERT INTO book_club VALUES (" + bookClub.getName() +"," + bookClub.getOwner() + "," + bookClub.getNumberOfSubscribers() + "," + bookClub.getCapacity() + ")");

            for(Genre g: bookClub.getGenres()) {
                statement.addBatch("INSERT INTO book_club_genres VALUES (" + bookClub.getName() + "," + g.toString() +")");
            }
            statement.executeBatch();
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    @Override
    public List<BookClubEntity> getBookClubsByGenres(List<Genre> genres) {
        // Lista di club del libro da restituire
        ArrayList<BookClubEntity> bookClubs = new ArrayList<BookClubEntity>();

        Connection connection = ConnectionFactory.getInstance();

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

            while(rs.next()){
                bookClubs.add(new BookClubEntity(rs.getString(BookClubAttributesOrder.NAME.getincrementedIndex()), new ArrayList<Genre>(), rs.getInt(BookClubAttributesOrder.NUMBER_OF_SUBSCRIBERS.getincrementedIndex()),
                        rs.getInt(BookClubAttributesOrder.CAPACITY.getincrementedIndex()), rs.getString(BookClubAttributesOrder.OWNER.getincrementedIndex())));
            }

            for(BookClubEntity bookClub: bookClubs){
                statement = connection.prepareStatement(GENRES_QUERY);
                statement.setString(1, bookClub.getName());
                rs = statement.executeQuery();

                while(rs.next()){
                    bookClub.getGenres().add(Genre.valueOf(rs.getString(1)));
                }
            }
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return bookClubs;
    }

    @Override
    public List<BookClubEntity> getBookClubsByOwner(String name) {
        // Lista di club del libro da restituire
        ArrayList<BookClubEntity> bookClubs = new ArrayList<BookClubEntity>();

        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT name, owner, numberOfSubscribers, capacity FROM book_club WHERE owner=?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                bookClubs.add(new BookClubEntity(rs.getString(BookClubAttributesOrder.NAME.getincrementedIndex()), new ArrayList<Genre>(), rs.getInt(BookClubAttributesOrder.NUMBER_OF_SUBSCRIBERS.getincrementedIndex()),
                        rs.getInt(BookClubAttributesOrder.CAPACITY.getincrementedIndex()), rs.getString(BookClubAttributesOrder.OWNER.getincrementedIndex())));
            }

            for(BookClubEntity bookClub: bookClubs){
                statement = connection.prepareStatement(GENRES_QUERY);
                statement.setString(1, bookClub.getName());
                rs = statement.executeQuery();

                while(rs.next()){
                    bookClub.getGenres().add(Genre.valueOf(rs.getString(1)));
                }
            }
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return bookClubs;
    }

    @Override
    public BookClubEntity getBookClubByName(String name) {
        BookClubEntity bookClub = null;
        Connection connection = ConnectionFactory.getInstance();

        try{
            List<Genre> bookClubGenres = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement(GENRES_QUERY);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                bookClubGenres.add(Genre.valueOf(rs.getString("genre")));
            }

            statement = connection.prepareStatement("SELECT name, owner, numberOfSubscribers, capacity FROM book_club WHERE name=?");
            statement.setString(1,name);
            rs = statement.executeQuery();

            if(rs.next()){
                bookClub = new BookClubEntity(rs.getString(BookClubAttributesOrder.NAME.getincrementedIndex()), bookClubGenres, rs.getInt(BookClubAttributesOrder.NUMBER_OF_SUBSCRIBERS.getincrementedIndex()),
                        rs.getInt(BookClubAttributesOrder.CAPACITY.getincrementedIndex()), rs.getString(BookClubAttributesOrder.OWNER.getincrementedIndex()));
            }
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return bookClub;
    }

    @Override
    public void addSubscriber(String name) {
        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT numberOfSubscribers FROM book_club WHERE name=?");
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();

            int oldNumber;
            if(rs.next()) oldNumber = rs.getInt("numberOfSubscribers");
            else oldNumber = 0;

            statement = connection.prepareStatement("UPDATE book_club SET numberOfSubscribers=? WHERE name=?");
            statement.setInt(1, oldNumber + 1);
            statement.setString(2, name);
            statement.executeQuery();

        }catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }
}
