package com.niit.jukebox;

import com.niit.jukebox.doa.JukeboxException;
import com.niit.jukebox.model.Songs;
import com.niit.jukebox.service.PlayListService;
import com.niit.jukebox.service.PlayerService;
import com.niit.jukebox.service.PlaylistContentService;
import com.niit.jukebox.service.SongService;

import java.util.*;

public class JukeboxMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SongService songsService = new SongService();
        PlayListService playlistService = new PlayListService();
        PlaylistContentService playlistContentService = new PlaylistContentService();
        PlayerService playerService = new PlayerService();
        try {
            ArrayList<Songs> allSongsList = songsService.getAllSongs();
            Hashtable<String, Integer> allPlaylist = playlistService.getAllPlaylist();

            songsService.displaySongs(allSongsList);

            System.out.println("\nChoose an option for");
            System.out.println("\t1-Songs\n\t2-Playlist\n\t3-Player\n\t4-Exit");
            int choice = scanner.nextInt();
            boolean repeat=false;
             do{
                 while (repeat)
                 {
                     System.out.println("\nChoose an option");
                     System.out.println("\t1-Songs\n\t2-Playlist\n\t3-Player\n\t4-Exit");
                     choice = scanner.nextInt();
                     break;
                 }
                 switch (choice) {
                     case 1: {
                         System.out.println("\nEnter a choice in songs");
                         System.out.println("\t1-To add a song\n\t2-Search song by name\n\t3-Search song by album\n\t4-Search song by artist\n\t5-Search song by genre\n\t6-Return to Main Menu\n\t7-Exit");
                         int choice2 = scanner.nextInt();
                         switch (choice2) {
                             case 1: {
                                 scanner.nextLine();
                                 System.out.println("\nEnter the song name you want to add");
                                 String song_name = scanner.nextLine().trim().toLowerCase();
                                 System.out.println("Enter the song album name");
                                 String album_name = scanner.nextLine().trim().toLowerCase();
                                 System.out.println("Enter the song Artist name");
                                 String artist_name = scanner.nextLine().trim().toLowerCase();
                                 System.out.println("Enter the song genre");
                                 String genre = scanner.nextLine().trim().toLowerCase();
                                 System.out.println("Enter the song duration");
                                 String duration = scanner.nextLine().trim().toLowerCase();
                                 Songs songs = new Songs(song_name, album_name, artist_name, genre, duration);
                                 String result = songsService.addSongs(songs, allSongsList) ? "Song Added" : "Song Not Added";
                                 System.out.println(result);
                                 allSongsList = songsService.getAllSongs();  //refreshing
                                 songsService.displaySongs(allSongsList);
                                 break;
                             }
                             case 2: {
                                 System.out.println("\nEnter the song name you want to search:");
                                 scanner.nextLine();
                                 String song_name = scanner.nextLine().trim().toLowerCase();
                                 Songs result = songsService.getOneSong(allSongsList, song_name);
                                 if (result != null)
                                     System.out.println(result);
                                 else
                                     System.out.println("\nSong Not Found!");
                                 break;
                             }
                             case 3: {
                                 System.out.println("\nEnter the album name by which you want to search Songs:");
                                 scanner.nextLine();
                                 String albumName = scanner.nextLine().trim().toLowerCase();
                                 ArrayList<Songs> result = songsService.getSongsByAlbumName(allSongsList, albumName);
                                 if (!result.isEmpty())
                                     System.out.println(result);
                                 else
                                     System.out.println("\nAlbum Not Found!");
                                 break;
                             }
                             case 4: {
                                 System.out.println("\nEnter the artist name by which you want to search Songs:");
                                 scanner.nextLine();
                                 String artistName = scanner.nextLine().trim().toLowerCase();
                                 ArrayList<Songs> result = songsService.getSongsByArtist(allSongsList, artistName);
                                 if (!result.isEmpty())
                                     System.out.println(result);
                                 else
                                     System.out.println("\nArtist Not Found!");
                                 break;
                             }
                             case 5: {
                                 System.out.println("\nEnter the genre name by which you want to search Songs");
                                 scanner.nextLine();
                                 String genre_name = scanner.nextLine().trim().toLowerCase();
                                 ArrayList<Songs> result = songsService.getSongsByGenre(allSongsList, genre_name);
                                 if (!result.isEmpty())
                                     System.out.println(result);
                                 else
                                     System.out.println("\nGenre Not Found!");
                                 break;
                             }
                             case 6:
                                 System.out.println("Returning to main menu...");
                                 repeat=true;
                                 break;
                             case 7:
                                 System.exit(0);
                             default:
                                 System.out.println("Wrong Choice!");
                         }
                         break;
                     }
                     case 2:
                         System.out.println("\nEnter your choice in playlist");
                         System.out.println("\t1- Show all playlist\n\t2- Create a new playlist\n\t3- Add song to a playlist\n\t4- Add an album songs to a playlist\n\t5- Display a playlist\n\t6- Return to Main menu\n\t7- Exit");
                         int choice3 = scanner.nextInt();
                         switch (choice3) {
                             case 1:
                                 Set<String> keysFromHashTable = allPlaylist.keySet();
                                 Iterator<String> iterator = keysFromHashTable.iterator();
                                 while (iterator.hasNext()) {
                                     System.out.println(iterator.next());
                                 }
                                 break;

                             case 2:
                                 scanner.nextLine();
                                 System.out.println("\nEnter your new playlist name");
                                 String playlist_name = scanner.nextLine().trim().toLowerCase();
                                 if (playlistService.addPlaylist(playlist_name, allPlaylist)) {
                                     System.out.println(playlist_name + " added to playlist");
                                     playlistService.getAllPlaylist();
                                 }
                                 break;

                             case 3:
                                 scanner.nextLine();
                                 System.out.println("\nEnter the playlist name in which you want to add Song");
                                 String playList_name = scanner.nextLine().trim().toLowerCase();
                                 System.out.println("Enter the song name you want to add");
                                 String song_name = scanner.nextLine().trim().toLowerCase();
                                 if (playlistContentService.addSongToPlaylist(allSongsList, allPlaylist, song_name, playList_name))
                                     System.out.println("Song " + song_name + " added to playlist " + playList_name + " successfully");
                                 break;

                             case 4:
                                 scanner.nextLine();
                                 System.out.println("\nEnter the playlist name in which you want to add Album");
                                 String playList3 = scanner.nextLine().trim().toLowerCase();
                                 System.out.println("Enter the album name you want to add");
                                 String albumName3 = scanner.nextLine().trim().toLowerCase();
                                 if (playlistContentService.addAlbumToPlaylist(allSongsList, allPlaylist, albumName3, playList3))
                                     System.out.println("Album " + albumName3 + " added to playlist " + playList3 + " successfully");
                                 break;
                             case 5:
                                 scanner.nextLine();
                                 System.out.println("\nGive the playlist name to display its content");
                                 String playListName4 = scanner.nextLine().trim().toLowerCase();
                                 ArrayList<Songs> playListContent1 = playlistContentService.playlistContent(playListName4, allPlaylist, allSongsList);
                                 System.out.println(playListContent1);
                                 break;

                             case 6:
                                 System.out.println("Returning to main...");
                                 repeat=true;
                                 break;
                             case 7:
                                 System.exit(0);
                                 break;
                             default:
                                 System.out.println("Wrong Choice!");
                         }
                         break;

                     case 3:
                         System.out.println("\nEnter Your Choice \n\t1- Play Songs\n\t2- Play a Playlist\n\t3- Return to Main Menu\n\t4- Exit");
                         scanner.nextLine();
                         int choiceP = scanner.nextInt();
                         switch (choiceP)
                         {
                             case 1:
                                 scanner.nextLine();
                                 System.out.println("\nEnter the Song name you want to play");
                                 String songTobePlayed = scanner.nextLine().trim().toLowerCase();
                                 Songs infoOfSong = songsService.getOneSong(allSongsList,songTobePlayed);
                                 playerService.SimpleAudioPlayer(infoOfSong.getSong_id());

                                 while (true)
                                 {
                                     System.out.println("Enter \n\t1- pause\n\t2- resume\n\t3- restart\n\t4- stop");
                                     int playerChoice = scanner.nextInt();
                                     playerService.gotoChoice(playerChoice);
                                     if(playerChoice==4)
                                         break;
                                 }
                                 break;
                             case 2:
                                 scanner.nextLine();
                                 System.out.println("Enter the name of playlist you want to play ");
                                 String playlistTobePlayed = scanner.nextLine().trim().toLowerCase();
                                 ArrayList<Integer> songsIdToPlay = new ArrayList<>();
                                 ArrayList<Songs> songsOfPlaylistToPlay = playlistContentService.playlistContent(playlistTobePlayed,allPlaylist,allSongsList);
                                 Iterator<Songs> iterator = songsOfPlaylistToPlay.iterator();
                                 while (iterator.hasNext()) {
                                     songsIdToPlay.add(iterator.next().getSong_id());
                                 }
                                 System.out.println("Songs present in "+playlistTobePlayed+" playlist");
                                 for (int id:songsIdToPlay)
                                 {
                                     System.out.println("Song "+id);
                                 }
                                 playerService.SimpleAudioPlayer(songsIdToPlay.get(0));
                                 while (true)
                                 {
                                     System.out.println("Enter \n\t1- stop\n\t2- pause\n\t3- resume\n\t4- restart");
                                     int playerChoice = scanner.nextInt();
                                     playerService.gotoChoiceForPlaylist(playerChoice);
                                     if(playerChoice==1)
                                         break;
                                 }

                                 break;
                             case 3:
                                 System.out.println("Returning to Main Menu...");
                                 repeat=true;
                                 break;
                             case 4:
                                 System.out.println("Existing...");
                                 System.exit(0);
                                 break;
                             default:
                                 System.out.println("Wrong choice!");

                         }
                 }
             }while (choice!=4);
        }
        catch (JukeboxException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            }
    }
}

