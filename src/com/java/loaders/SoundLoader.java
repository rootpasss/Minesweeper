package com.java.loaders;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioInputStream;

import java.io.File;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: Sublime Text 3 [build 3126].
* Date: 29/08/17, Time: 00:32:48.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Sound Resources Player, Media Provided By freesound.org ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, Personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class SoundLoader extends Thread {
  private int ID;

  public SoundLoader(int ID) {
    this.ID = ID;
  }

  private void play(String media) {
    try {
      Clip tune = AudioSystem.getClip();
      tune.open( AudioSystem.getAudioInputStream( getClass().getResource( media ) ) );
      tune.start();

      do {
        sleep( 2 );
      } while( tune.isRunning() );
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }

  public void run() {
    switch( ID ) {
      case 1:  play( "/sounds/bomb.wav" );
        break;
      case 2:  play( "/sounds/sweep.wav" );
        break;
      case 3:  play( "/sounds/clock.wav" );
        break;
    }
  }
}
