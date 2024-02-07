package com.niit.jukebox.service;
import com.niit.jukebox.doa.JukeboxException;
import com.niit.jukebox.doa.PlaylistContentDAO;
import com.niit.jukebox.model.Songs;
import java.util.ArrayList;
import java.util.Hashtable;

public class PlaylistContentService {
    public boolean addSongToPlaylist(ArrayList<Songs> songlist, Hashtable<String, Integer> playlist, String song_name, String playlist_name) throws Exception {

        boolean result = false;
        if (songlist.isEmpty() || playlist.isEmpty() || song_name == null || playlist_name == null) {
            throw new JukeboxException("Please provide all values");
        } else {
            int playlist_id = playlist.get(playlist_name);
            int song_id = 0;
            for (Songs songs : songlist) {
                if (songs.getSong_name().equals(song_name)) {
                    song_id = songs.getSong_id();
                    result = true;
                    break;
                }
            }
            if (playlist_id == 0) {
                throw new JukeboxException("Playlist not present");
            } else if (song_id == 0) {
                throw new JukeboxException("Song not present");
            } else {
                PlaylistContentDAO playlistContentDAO = new PlaylistContentDAO();
                playlistContentDAO.addSongsToPlaylist(song_id, playlist_id);
            }
        }return result;
    }
    public boolean addAlbumToPlaylist(ArrayList<Songs> songlist, Hashtable<String, Integer> playlist, String albumName, String playlistname) throws Exception {
        boolean result = false;
        if (songlist.isEmpty() || playlist.isEmpty() || albumName == null || playlistname == null) {
            throw new JukeboxException("Please provide some values");
        } else {
            int playlist_id = playlist.get(playlistname);
            ArrayList<Integer> songIdList = new ArrayList<>();
            for (Songs songs : songlist) {
                if (songs.getAlbum_name().equals(albumName)) {
                    songIdList.add(songs.getSong_id());
                    result = true;
                }
            }
            if (playlist_id == 0) {
                throw new Exception("Playlist not present");
            } else if (songIdList.isEmpty()) {
                throw new Exception("Album not present");
            } else {
                PlaylistContentDAO playlistContentDAO = new PlaylistContentDAO();
                for (int id : songIdList) {
                    playlistContentDAO.addSongsToPlaylist(id, playlist_id);
                }
            }
        }
        return result;
    }
    public ArrayList<Songs> playlistContent(String playlist_name, Hashtable<String, Integer> playlist, ArrayList<Songs> songlist) throws Exception {
        ArrayList<Songs> forReturnList=null;
        ArrayList<Integer> intArraylist;
        if (playlist_name == null || playlist.isEmpty() || songlist.isEmpty()) {
            throw new JukeboxException("Please provide some values");
        } else {
            int playlist_id=0;
            //if playlist id is not present
            if (playlist.containsKey(playlist_name)==false) {
                throw new JukeboxException("Playlist not found");
            } else {
                playlist_id=playlist.get(playlist_name);
                PlaylistContentDAO playlistContentDAO = new PlaylistContentDAO();
                intArraylist = playlistContentDAO.viewSongInPlaylist(playlist_id);
            }
            if (intArraylist.isEmpty() == false) {
                forReturnList = new ArrayList<>();
                for (int id : intArraylist) {
                    for (Songs songs : songlist) {
                        if (songs.getSong_id() == id) {
                            forReturnList.add(songs);
                        }
                    }
                }
            } else {
                throw new Exception("PlayList is Empty");
            }
        }return forReturnList;
    }
}
