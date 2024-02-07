package com.niit.jukebox.service;
import com.niit.jukebox.doa.PlayListDAO;
import java.sql.SQLException;
import java.util.Hashtable;

public class PlayListService {
    public boolean addPlaylist(String playlist_name, Hashtable<String,Integer> playlist) throws SQLException {
        boolean result = false;
        boolean checkIfPlaylistPresent=playlist.containsKey(playlist_name.trim().toLowerCase());
        if(checkIfPlaylistPresent== false){
            PlayListDAO.createPlaylist(playlist_name);
            result=true;
        }
        return result;
    }
    public Hashtable<String,Integer> getAllPlaylist() throws SQLException {
        return PlayListDAO.viewAllPlaylist();
    }
}


