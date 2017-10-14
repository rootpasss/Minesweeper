package com.java.utils;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: Sublime Text 3 [build 3126].
* Date: 24/08/17, Time: 18:42:53.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Level Selection Values ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, Personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class Level {
  public static int width, height, mines;

  public static void setSize( int newWidth, int newHeight ) {
    width = newWidth;
    height = newHeight;
  }

  public static void setMines( int newMines ) {
    mines = newMines;
  }

  public static int getWidth() {
    return width;
  }

  public static int getHeight() {
    return height;
  }

  public static int getMines() {
    return mines;
  }
}
