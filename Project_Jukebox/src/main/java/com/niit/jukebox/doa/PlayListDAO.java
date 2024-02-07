package com.niit.jukebox.doa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
public class PlayListDAO{
    public static boolean createPlaylist(String playlist_name) throws SQLException {
        boolean result = false;
        PreparedStatement preparedStatement = Connection.getConnection().prepareStatement("insert into playlist(playlist_name) values(?);");
        preparedStatement.setString(1, playlist_name.trim().toLowerCase());
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet>0) {
            result = true;
        }
        return result;
    }
    public static Hashtable<String,Integer> viewAllPlaylist() throws SQLException {
        Hashtable<String,Integer> playlistTable=new Hashtable<>();
        Statement statement = Connection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from playlist;");
        while(rs.next())
        {
            playlistTable.put(rs.getString(2),rs.getInt(1)); //if I give my playList_name, give me it's id
        }
        return playlistTable;
    }
}
