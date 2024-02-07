package com.niit.jukebox.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;

public class PlayerService {
    Long currentFrame;
    Clip clip;
    String status;
    AudioInputStream audioInputStream;
    String filePath ;
    int idid;

    public void SimpleAudioPlayer(int id) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        idid = id;
        audioInputStream = AudioSystem.getAudioInputStream(new File("D:\\NIIT\\Project\\Project_Jukebox\\src\\main\\resources\\"+id+".wav"));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }
    public void gotoChoice(int c) throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        switch (c)
        {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio();
                break;
            case 3:
                restart();
                break;
            case 4:
                stop();
                break;
        }
    }
    public void gotoChoiceForPlaylist(int c) throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        switch (c)
        {
            case 1:
                stop();
                break;
            case 2:
                pause();
                break;
            case 3:
                resumeAudio();
                break;
            case 4:
                restart();
                break;
        }
    }

    public void play()
    {
        clip.start();
        status = "play";
    }
    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        if (status.equals("play"))
        {
            System.out.println("Audio is already being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(new File("D:\\NIIT\\Project\\Project_Jukebox\\src\\main\\resources\\"+idid+".wav"));
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}