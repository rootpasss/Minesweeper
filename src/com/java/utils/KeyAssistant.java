package com.java.utils;

import javax.swing.JButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

import java.util.ArrayList;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: VIM 7.3
* Date: 11/10/2017, Time: 22:40:54.
* 
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Keyboard Gaming Assistance For: Q,W,E,A,S,D,Z,X Keys And Mouse Motion ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class KeyAssistant {
  private Color normal = Color.LIGHT_GRAY;
  private Color darker = new Color( 214, 214, 214 );
  private ArrayList <String> coord = new ArrayList<>();
  private JButton[][] buttons;
  private int X, Y, rows, cols;

  public KeyAssistant( ArrayList <JButton> buttonsList, int rows, int cols ) {
    for( JButton B : buttonsList ) {
      B.addKeyListener( new KeyAdapter() {
        public void keyPressed( KeyEvent evt ) {
          move( B, evt );
        }
      } );
      B.addMouseListener( new MouseAdapter() {
        public void mouseEntered( MouseEvent evt ) {
          clear( B );
        }
        public void mouseExited( MouseEvent evt ) {
          B.setBackground( normal );
        }
        public void mouseClicked( MouseEvent evt ) {
          clear( B );
        }
      } );
      B.addMouseMotionListener( new MouseAdapter() {
        public void mouseMoved( MouseEvent evt ) {
          clear( B );
        }
      } );
    }

    buttons = new JButton[ rows ][ cols ];
    int pos = 0;
    for( int i = 0; i < rows; i++ ) {
      for( int j = 0; j < cols; j++ ) {
        buttons[ i ][ j ] = (JButton) buttonsList.get( pos ++ );
        coord.add( i + "," + j );
      }
    }

    this.rows = rows;
    this.cols = cols;
  }

  private void move( JButton B, KeyEvent E ) {
    int code = E.getKeyCode();

    if( code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A ) {
      direction( 1 );
    } else if( code == KeyEvent.VK_UP || code == KeyEvent.VK_W ) {
      direction( 2 );
    } else if( code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D ) {
      direction( 3 );
    } else if( code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S ) {
      direction( 4 );
    } else if( code == KeyEvent.VK_Q ) {
      direction( 5 );
    } else if( code == KeyEvent.VK_E ) {
      direction( 6 );
    } else if( code == KeyEvent.VK_Z ) {
      direction( 7 );
    } else if( code == KeyEvent.VK_X ) {
      direction( 8 );
    } else if( code == KeyEvent.VK_ENTER ) {
      B.doClick();
    }
  }

  private void direction( int orientation ) {
    buttons[ X ][ Y ].setBackground( normal );
    int initPos, initX, initY;
    
    if( orientation == 1 && Y > 0 ) {
      initPos = Y;
      Y --;

      while( buttons[ X ][ Y ].getText().length() > 0 ) {
        Y = Y == 0 ? initPos : Y - 1;
      }
    } else if( orientation == 2 && X > 0 ) {
      initPos = X;
      X --;

      while( buttons[ X ][ Y ].getText().length() > 0 ) {
        X = X == 0 ? initPos : X - 1;
      }
    } else if( orientation == 3 && Y < cols - 1 ) {
      initPos = Y;
      Y ++;

      while( buttons[ X ][ Y ].getText().length() > 0 ) {
        Y = Y == cols - 1 ? initPos : Y + 1;
      }
    } else if( orientation == 4 && X < rows - 1 ) {
      initPos = X;
      X ++;

      while( buttons[ X ][ Y ].getText().length() > 0 ) {
        X = X == rows - 1 ? initPos : X + 1;
      }
    } else if( orientation == 5 && X > 0 && Y > 0 ) {
      initX = X; initY = Y;
      X --;
      Y --;

      while( buttons[ X ][ Y ].getText().length() > 0 ) {
        if( X == 0 || Y == 0 ) {
          X = initX; Y = initY;
        } else {
          X --; Y --;
        }
      }
    } else if( orientation == 6 && X > 0 && Y < cols - 1 ) {
      initX = X; initY = Y;
      X --;
      Y ++;

      while( buttons[ X ][ Y ].getText().length() > 0 ) {
        if( X == 0 || Y == cols - 1 ) {
          X = initX; Y = initY;
        } else {
          X --; Y ++;
        }
      }
    } else if( orientation == 7 && X < rows - 1 && Y > 0 ) {
      initX = X; initY = Y;
      X ++;
      Y --;

      while( buttons[ X ][ Y ].getText().length() > 0 ) {
        if( X == rows - 1 || Y == 0 ) {
          X = initX; Y = initY;
        } else {
          X ++; Y --;
        }
      }
    } else if( orientation == 8 && X < rows - 1 && Y < cols - 1 ) {
      initX = X; initY = Y;
      X ++;
      Y ++;

      while( buttons[ X ][ Y ].getText().length() > 0 ) {
        if( X == rows - 1 || Y == cols - 1 ) {
          X = initX; Y = initY;
        } else {
          X ++; Y ++;
        }
      }
    }

    buttons[ X ][ Y ].requestFocus();
    if( buttons[ X ][ Y ].getBorder().toString().contains( "Bevel" ) ) {
      buttons[ X ][ Y ].setBackground( darker );
    }
  }

  private void clear( JButton button ) {
    for( int i = 0; i < rows; i++ ) {
      for( int j = 0; j < cols; j++ ) {
        buttons[ i ][ j ].setBackground( normal );
      }
    }

    int val = Integer.parseInt( button.getName() );
    X = Integer.parseInt( coord.get( val ).split( "," )[0] );
    Y = Integer.parseInt( coord.get( val ).split( "," )[1] );
    
    if( button.getBorder().toString().contains( "Bevel" ) ) {
      button.setBackground( darker );
    }
  }
}
