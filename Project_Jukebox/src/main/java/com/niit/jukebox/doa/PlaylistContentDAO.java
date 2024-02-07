package com.niit.jukebox.doa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlaylistContentDAO {
    //I'm going to get song_id,playList_id from main()
    public static boolean addSongsToPlaylist(int song_id, int playlist_id) throws Exception {
        //insert into playlist content
        System.out.println("inside add songs");//
        PreparedStatement preparedStatement = Connection.getConnection().prepareStatement("insert into playlistContent(playlist_id,song_id) values(?,?)");
        preparedStatement.setInt(1, playlist_id);
        preparedStatement.setInt(2, song_id);
        int result = preparedStatement.executeUpdate();

        return result > 0 ? true : false;
    }
    // Going to take any particular playList and in that playList what all songs are present, will take song ids and insert it into a arrayList of ids.
    public static ArrayList<Integer> viewSongInPlaylist(int playlist_id) throws Exception {
        ArrayList<Integer> song_idsFromPlaylist = new ArrayList<>();
        //retrieving the data and only selecting song ids
        PreparedStatement preparedStatement = Connection.getConnection().prepareStatement("select*from playlistContent where playlist_id=?");
        preparedStatement.setInt(1, playlist_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //if(resultSet.next()) {
        // song_idsFromPlaylist = new ArrayList<>();
        while (resultSet.next()) {
            song_idsFromPlaylist.add(resultSet.getInt(2));
        }return song_idsFromPlaylist;

    }
}



