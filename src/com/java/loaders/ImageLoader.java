package com.java.loaders;

import javax.swing.ImageIcon;

import java.awt.Image;

import java.util.ArrayList;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: Sublime Text 3 [build 3126].
* Date: 23/08/17, Time: 19:01:50.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Icons Pre-Loader ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, Personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class ImageLoader {
  ArrayList <ImageIcon> icons = new ArrayList<>();

  public ImageLoader() {
    icons.add( image( "happySmiley" ) );
    icons.add( image( "clickedSmiley" ) );
    icons.add( image( "winSmiley" ) );
    icons.add( image( "lostSmiley" ) );
    icons.add( image( "fail" ) );
    icons.add( image( "flag" ) );
    icons.add( image( "revealed" ) );
    icons.add( image( "incorrect" ) );
    icons.add( image( "question" ) );
    icons.add( largeImage( "header" ) );
  }

  private ImageIcon image( String name ) {
    int size = name.contains( "Smiley" ) ? 30 : 25;
    Image image = new ImageIcon( getClass().getResource( "/icons/" + name + ".png" ) ).getImage();
    image = image.getScaledInstance( size, size, Image.SCALE_SMOOTH );
    return new ImageIcon( image );
  }

  private ImageIcon largeImage( String name ) {
    Image image = new ImageIcon( getClass().getResource( "/references/" + name + ".png" ) ).getImage();
    image = image.getScaledInstance( 418, 120, Image.SCALE_SMOOTH );
    return new ImageIcon( image );
  }

  public ImageIcon loadIcon( int position ) {
    return icons.get( position );
  }
}
