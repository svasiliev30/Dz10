package spb.city.demo.ProductRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import spb.city.demo.service.VkladInfo;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    /**
     * Пустой конструктор
     */
    public ProductRepository()
    {
        super();
    }

    /**
     * Получаю ссылку на имя таблицы
     */
    @Value("${name.table}")
    private String nameTable;

    /**
     *Получение ссылки на базу данных
     */
    @Value("${provider.create.table}")
    private String url;

    /**
     * Takes an information of table.Create new table.
     * @param informationTable
     * @return
     */
    public boolean createTable(String informationTable) {

        String sql = informationTable;

        try(Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();) {
            int affectedRows = statement.executeUpdate(informationTable);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Takes an information of vklad and name table. Added this information to table.
     * @param vkladInfo
     * @return
     */
    public boolean addVklad(VkladInfo vkladInfo){

       try (Connection connection = DriverManager.getConnection(url);
               Statement statement = connection.createStatement();){

           String sql = String.format("INSERT INTO " + nameTable + " ('№', 'deposit') VALUES (%s, %s)",
                   vkladInfo.getNumberVklad(), vkladInfo.getSumma());

           boolean hasResultSet = statement.execute(sql);
           int affectedRows = statement.getUpdateCount();
            System.out.println( "affectedRows = " + affectedRows);

       }catch (SQLException e) {
           e.printStackTrace();
           return false;
       }
        return true;
    }

    /**
     * Takes a name table and delete his.
     * @return
     */
    public boolean deleteTable(){

        String deletTable = "DROP TABLE " + nameTable;

       try (Connection connection = DriverManager.getConnection(url);
               Statement statement = connection.createStatement();){
           statement.executeUpdate(deletTable);
       } catch (SQLException e) {
           e.printStackTrace();
           return false;
       }
       return true;
    }

    /**
     * Read information from database.
     * @return
     */
    public List<VkladInfo> readAllVklad() {
        final String readAllPersonsQuery = "select * from " + nameTable ;

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(readAllPersonsQuery)) {
            List<VkladInfo> vkladRead = new ArrayList<>();

            while (resultSet.next()) {
                VkladInfo localPerson = new VkladInfo(
                        resultSet.getInt("№"),
                        resultSet.getInt("deposit"));
                vkladRead.add(localPerson);
            }
            return vkladRead;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Takes an age of person and id.Change Age on new age.
     * @param numberVklad
     * @return
     */
        public boolean closeVklad (int numberVklad) {

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();){

            int affectedRows = statement.executeUpdate("DELETE FROM " + nameTable + " WHERE  №=" + numberVklad);
            System.out.println( "affectedRows = " + affectedRows);

            if (affectedRows == 0 ){
                return false;
            }

        }catch (SQLException e){
            return false;
        }
        return true;
    }
}

