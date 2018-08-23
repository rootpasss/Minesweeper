package com.java.timing;

import java.text.DecimalFormat;

import com.java.minesweeper.Minesweeper;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: Sublime Text 3 [build 3126].
* Date: 29/08/17, Time: 13:26:56.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Time Keeper in Seconds (classic version) ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, Personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class Timekeeper extends Thread {
  private DecimalFormat DF = new DecimalFormat( "000" );
  public static boolean step = false;
  private boolean toggle = true;
  private static int secs = 0;
  
  public void run() {
    long ST = System.currentTimeMillis();

    try {
      while( true ) {
        if( step ) {
          if(toggle) {
            ST = System.currentTimeMillis();
            Thread.sleep(1000);
            toggle = false;
          }
          
          secs = (int) ((System.currentTimeMillis() - ST) / 1000F);
          Minesweeper.time.setText( secs < 1000 ? DF.format( secs ) : Minesweeper.time.getText() );
        } else {
          ST = System.currentTimeMillis();
        }

        sleep( 2 );
      }
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }

  public static int getTime() {
    return secs;
  }

  public static void go( boolean status ) {
    step = status;
  }

  public static void reset( boolean onCourse ) {
    Minesweeper.time.setText( "000" );
    step = false;
  }
}
