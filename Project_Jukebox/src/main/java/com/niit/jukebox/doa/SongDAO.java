package com.niit.jukebox.doa;

import com.niit.jukebox.model.Songs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SongDAO {
    public static ArrayList<Songs> selectAllSongs() throws SQLException
    {
        ArrayList<Songs> songArrayList=null;
        Statement selectStatement= Connection.getConnection().createStatement();
        ResultSet resultSet= selectStatement.executeQuery("select * from songs");
        if(resultSet.isBeforeFirst())
        {   songArrayList=new ArrayList<>();
            while (resultSet.next()) {
                songArrayList.add(new Songs(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));}
        }
        return songArrayList;
    }
    public static boolean insertSongs(Songs songs) throws SQLException
    {
        PreparedStatement insertStatement= Connection.getConnection().prepareStatement("insert into songs(song_name,album_name,artist_name,genre,duration) values(?,?,?,?,?);");
        insertStatement.setString(1,songs.getSong_name().trim().toLowerCase());
        insertStatement.setString(2,songs.getAlbum_name().trim().toLowerCase());
        insertStatement.setString(3,songs.getArtist_name().trim().toLowerCase());
        insertStatement.setString(4,songs.getGenre().trim().toLowerCase());
        insertStatement.setString(5,songs.getDuration().trim().toLowerCase());
        int result=insertStatement.executeUpdate();
        return result>0?true:false;
    }
}
