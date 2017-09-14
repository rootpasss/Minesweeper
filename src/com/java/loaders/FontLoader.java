package com.java.loaders;

import java.awt.Font;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: Sublime Text 3 [build 3126].
* Date: 23/08/17, Time: 18:51:06.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Personalized Font ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, Personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class FontLoader {
  public Font digitalFont() {
    Font myFont = null;

    try {
      myFont = Font.createFont( Font.TRUETYPE_FONT, getClass().getResourceAsStream( "/fonts/digital7.ttf" ) );
    } catch( Exception e ) {
      e.printStackTrace();
    }
    
    return myFont.deriveFont( 24F );
  }
}
