package com.niit.jukebox.service;
import com.niit.jukebox.doa.SongDAO;
import com.niit.jukebox.model.Songs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class SongService {

    private boolean checkAvailableSong(String song_name, ArrayList<Songs> songArrayList) {
        boolean result = false;
        if (songArrayList == null) {
            result = true;
        }
        return result;
    }
    public boolean addSongs(Songs songs , ArrayList<Songs> songArrayList) throws SQLException
    {   boolean result = false;
        if(!checkAvailableSong(songs.getSong_name(),songArrayList))
        {
            SongDAO.insertSongs(songs);
            result = true;
        }
        return result;
    }
    public ArrayList<Songs> getAllSongs() throws SQLException
    {
        return SongDAO.selectAllSongs();
    }
    public  void displaySongs(ArrayList<Songs> songArrayList)
    {   //to print the column name
        System.out.format("%10s\t%30s\t%30s\t%30s\t%30s\t%20s","song_id","song_name","album_name","artist_name","genre","duration");
        for(Songs songs : songArrayList){
            System.out.println(songs);
        }
    }
    public Songs getOneSong(ArrayList<Songs> songArrayList ,String song_name)
    {   //will check whether song exits or not
        Songs selected = null;
        if(songArrayList != null && song_name != null){
            for(Songs check : songArrayList)
            {
                if(check.getSong_name().equals(song_name)){
                    selected = check;
                }
            }
        }
        return selected;
    }
    public ArrayList<Songs> getSongsByAlbumName (ArrayList<Songs> songArrayList, String album_name)
    {
        ArrayList<Songs> albumChoice = null;
        if(!songArrayList.isEmpty() && album_name != null) {
            albumChoice = new ArrayList<>();
            for (Songs check : songArrayList) {
                if (check.getAlbum_name().equals(album_name)) {
                    albumChoice.add(check);
                }
            }
        }
        return albumChoice;
    }
    public  ArrayList<Songs> getSongsByArtist(ArrayList<Songs> songArrayList , String artist_Name) {
        ArrayList<Songs> playByArtistChoice = null;
        if (!artist_Name.isEmpty() && artist_Name != null) {
            playByArtistChoice = new ArrayList<>();
            for (Songs check : songArrayList) {
                if (check.getArtist_name().contains(artist_Name))
                {
                    playByArtistChoice.add(check);
                }
            }
        }
        return playByArtistChoice;
    }
    public ArrayList<Songs> getSongsByGenre(ArrayList<Songs> songArrayList,String genre){
        ArrayList<Songs> genreChoiceListByUser=null;
        if (!songArrayList.isEmpty() && genre!=null) {
            genreChoiceListByUser = new ArrayList<>();
            Iterator<Songs> iterator = songArrayList.iterator();
            while (iterator.hasNext()){
                Songs songs = iterator.next();
                String genreObj= songs.getGenre();
                if(genreObj.equals(genre))
                {
                    genreChoiceListByUser.add(songs);
                }
            }
        }
        return genreChoiceListByUser;
    }
}
