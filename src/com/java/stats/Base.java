package com.java.stats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;

import com.java.utils.WinnerModal;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: Sublime Text 3 [build 3126].
* Date: 31/08/17, Time: 21:18:45.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Game Persistence Manager ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, Personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class Base {
  ArrayList <Skill> dataInfo = new ArrayList<>();
  File F = new File( System.getProperty( "user.home" ) + "/.sweepersBase/fastest.sw" );
  FileInputStream FIS;
  FileOutputStream FOS;
  ObjectInputStream OIS;
  ObjectOutputStream OOS;

  public void updateBaseFile( String level, int time, String playerName ) {
    try {
      readBaseFile();
      int pos = level.equals( "Beginner" ) ? 0 : level.equals( "Intermediate" ) ? 1 : 2;
      FOS = new FileOutputStream( F );
      OOS = new ObjectOutputStream( FOS );

      dataInfo.set( pos, new Skill( level + ":", time, playerName ) );

      OOS.writeObject( dataInfo );

      OOS.close();
      FOS.close();
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings( "unchecked" )
  public void readBaseFile() {
    try {
      FIS = new FileInputStream( F );
      OIS = new ObjectInputStream( FIS );

      dataInfo = (ArrayList <Skill>) OIS.readObject();

      OIS.close();
      FIS.close();
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }

  public void resetBaseFile() {
    try {
      comprobation();// Comprobates if file base exists

      FOS = new FileOutputStream( F );
      OOS = new ObjectOutputStream( FOS );

      dataInfo.add( new Skill( "Beginner:", 999, "Anonymous" ) );
      dataInfo.add( new Skill( "Intermediate:", 999, "Anonymous" ) );
      dataInfo.add( new Skill( "Expert:", 999, "Anonymous" ) );

      OOS.writeObject( dataInfo );
      OOS.close();
      FOS.close();
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }

  public String getStatistics() {
    String output = "\n";
    readBaseFile();

    for( Skill S : dataInfo ) {
      output += S.getSkill() + "\t" + S.getTotalTime() + " seconds\t" + S.getPlayerAlias() + "\n\n";
    }

    return output;
  }

  public void compareStats( int time, String level ) {
    int pos = level.equals( "Beginner" ) ? 0 : level.equals( "Intermediate" ) ? 1 : 2;
    readBaseFile();

    if( time < dataInfo.get( pos ).getTotalTime() ) {
      new WinnerModal( level, time ).setVisible( true );
    }
  }

  public void comprobation() {
    try {
      if( ! F.exists() ) {
        new File( F.getParent() ).mkdirs();
      }
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }
}

class Skill implements Serializable {
  String skill, playerAlias;
  int totalTime;

  public Skill( String skill, int totalTime, String playerAlias ) {
    this.skill = skill;
    this.totalTime = totalTime;
    this.playerAlias = playerAlias;
  }

  public String getSkill() {
    return skill;
  }

  public int getTotalTime() {
    return totalTime;
  }

  public String getPlayerAlias() {
    return playerAlias;
  }
}
