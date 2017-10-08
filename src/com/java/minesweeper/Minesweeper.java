package com.java.minesweeper;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.GridLayout;

import java.util.ArrayList;

import com.java.loaders.FontLoader;
import com.java.loaders.ImageLoader;
import com.java.stats.Base;
import com.java.utils.Menu;
import com.java.utils.Level;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: VIM 7.3
* Date: 02/08/2017, Time: 17:53:36.
* 
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Minesweeper Classic, Beta version with bugs ]
*     TODO: 1. Put Application Icon
*     TODO: 2. Add Keyboard Gaming Mode
*     TODO: 3. Add How-To Play
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class Minesweeper extends JFrame {
  public static com.java.loaders.SoundLoader ticking = new com.java.loaders.SoundLoader( 3, true );
  public static com.java.timing.Timekeeper timing = new com.java.timing.Timekeeper();
  java.text.DecimalFormat DF = new java.text.DecimalFormat( "000" );
  Border lineBorder = new LineBorder( Color.GRAY, 1 );
  ArrayList <JButton> buttons = new ArrayList<>();
  ArrayList <String> position = new ArrayList<>();
  ArrayList <String> values = new ArrayList<>();
  ArrayList <String> coord = new ArrayList<>();
  JPanel contentPane, components, header, body;
  ImageLoader imgLoader = new ImageLoader();
  int mines, cellCounter = 0, X, Y;
  public static boolean launch;
  public static JLabel time, timeB;
  GridLayout layout;
  JLabel minesLeft;
  String[] range1;
  String[] range2;
  String[] range3;
  String[] range4;
  String[] range5;
  String[] range6;
  String[] range7;
  String[] range8;
  String[] range9;
  String[][] map;
  JButton start;

  public static void main( String[] args ) {
     new Minesweeper().setVisible( true );
  }

  public Minesweeper() {
    setTitle( "Minesweeper" );
    setResizable( false );
    setLocationRelativeTo( null );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    
    contentPane = new JPanel();
    contentPane.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
    contentPane.setLayout( null );
    setContentPane( contentPane );

    components = new JPanel();
    components.setBorder( BorderFactory.createRaisedBevelBorder() );
    components.setLayout( null );
    components.setBackground( Color.LIGHT_GRAY );
    getContentPane().add( components );

    header = new JPanel();
    header.setBounds( 8, 8, components.getWidth() - 16, 47 );
    header.setBorder( BorderFactory.createLoweredBevelBorder() );
    header.setLayout( null );
    header.setBackground( Color.LIGHT_GRAY );
    components.add( header );

    minesLeft = new JLabel();
    minesLeft.setBounds( 6, 6, 60, 34 );
    minesLeft.setForeground( Color.RED );
    minesLeft.setFont( new FontLoader().digitalFont() );

    JLabel minesLeftBack = new JLabel( "888" );
    minesLeftBack.setBounds( 6, 6, 60, 34 );
    minesLeftBack.setOpaque( true );
    minesLeftBack.setBackground( Color.BLACK );
    minesLeftBack.setForeground( new Color( 108, 0, 0 ) );
    minesLeftBack.setFont( minesLeft.getFont() );
    header.add( minesLeft );
    header.add( minesLeftBack );

    start = new JButton();
    start.setSize( 37, 37 );
    start.setLocation( header.getWidth() / 2 - start.getWidth() / 2, 5 );
    start.setBorder( BorderFactory.createRaisedBevelBorder() );
    start.setFocusPainted( false );
    start.setIcon( imgLoader.loadIcon( 0 ) );
    start.addActionListener( event -> restart() );
    start.addMouseListener( new java.awt.event.MouseAdapter() {
      public void mousePressed( java.awt.event.MouseEvent evt ) {
        start.setBorder( BorderFactory.createLoweredBevelBorder() );
      }

      public void mouseReleased( java.awt.event.MouseEvent evt ) {
        start.setBorder( BorderFactory.createRaisedBevelBorder() );
      }
    } );
    header.add( start );

    time = new JLabel( "000" );
    time.setSize( 60, 34 );
    time.setLocation( header.getWidth() - time.getWidth() - 6, 6 );
    time.setForeground( Color.RED );
    time.setFont( new FontLoader().digitalFont() );
    header.add( time );

    timeB = new JLabel( "888" );
    timeB.setBounds( time.getX() * -1 + 54, 6, 60, 34 );
    timeB.setOpaque( true );
    timeB.setBackground( Color.BLACK );
    timeB.setForeground( new Color( 108, 0, 0 ) );
    timeB.setFont( time.getFont() );
    header.add( timeB );
    
    body = new JPanel();
    body.setBounds( 8, 62, components.getWidth() - 16, components.getHeight() - 90 );
    body.setBackground( Color.LIGHT_GRAY );
    body.setBorder( BorderFactory.createLoweredBevelBorder() );
    components.add( body );

    setJMenuBar( new Menu( this ) );
    components.setBounds( 2, 2, getWidth() - 4, getHeight() - 49 );
    layout = new GridLayout( Level.getHeight(), Level.getWidth() );
    minesLeft.setText( DF.format( Level.getMines() ) );
    body.setLayout( layout );

    setButtons();// Place buttons on the board
    setup();     // Initialize 2D array randomly
    ticking.start();
    timing.start();
  }

  public void setButtons() {
    map = new String[ layout.getRows() ][ layout.getColumns() ];

    for( int i = 0; i < Level.getHeight() * Level.getWidth(); i++ ) {
      JButton B = new JButton();
      B.setOpaque( true );
      B.setBackground( Color.LIGHT_GRAY );
      B.setName( i + "" );
      B.setFont( new java.awt.Font( "Tahoma", java.awt.Font.BOLD, 18 ) );
      B.setBorder( BorderFactory.createRaisedBevelBorder() );
      B.addActionListener( event -> check( B, event ) );
      B.addMouseListener( new java.awt.event.MouseAdapter() {
        public void mousePressed( java.awt.event.MouseEvent evt ) {
          if( B.getActionListeners().length > 0 ) {
            start.setIcon( imgLoader.loadIcon( 1 ) );
          }
        }

        public void mouseReleased( java.awt.event.MouseEvent evt ) {
          if( B.getActionListeners().length > 0 ) {
            start.setIcon( B.getIcon() == imgLoader.loadIcon( 6 ) ? imgLoader.loadIcon( 3 ) : imgLoader.loadIcon( 0 ) );

            if( cellCounter == Level.getWidth() * Level.getHeight() - Level.getMines() ) {
              start.setIcon( imgLoader.loadIcon( 2 ) );
              disableBoard();
              revealFlags();
              minesLeft.setText( DF.format( countFlags() ) );

              // Compare level time with base file level time here...
              if( ! com.java.utils.Menu.getSelectedSkill().equals( "Custom" ) ) {
                int finalTime = Integer.parseInt( time.getText() );
                new Base().compareStats( finalTime, com.java.utils.Menu.getSelectedSkill() );
              }
            }

            if( launch ) {
              try {
                if( com.java.utils.Menu.sound.isSelected() ) {
                  ticking.mute( false );
                }

                timing.go( true );
                launch = false;
              } catch( Exception e ) {
                e.printStackTrace();
              }
            }
          }
        }
      } );
      body.add( B );
      buttons.add( B );
    }
  }

  public void check( JButton button, java.awt.event.ActionEvent e ) {
    if( (e.getModifiers() & java.awt.event.InputEvent.CTRL_MASK) != 0 ) {
      // Place flags here
      if( ( countFlags() > 0 ) || ( countFlags() == 0 && button.getIcon() != null ) ) {
        if( com.java.utils.Menu.marks.isSelected() ) {
          button.setIcon( button.getIcon() == null ? imgLoader.loadIcon( 5 ) : button.getIcon() == imgLoader.loadIcon( 5 ) ? imgLoader.loadIcon( 8 ) : null );
        } else {
          button.setIcon( button.getIcon() == null ? imgLoader.loadIcon( 5 ) : null );
        }

        button.setIcon( button.getBorder().toString().contains( "Line" ) ? null : button.getIcon() );
        minesLeft.setText( DF.format( countFlags() ) );
      }
    } else {
      if( button.getIcon() == null ) {
        updateCellForeground( button );
        
        switch( button.getText() ) {
          case "":   revealBlank( coord.get( Integer.parseInt( button.getName() ) ), Integer.parseInt( button.getName() ) );
                     if( com.java.utils.Menu.sound.isSelected() ) {
                      new com.java.loaders.SoundLoader( 2 ).start();
                     }
            break;
          case "*":  button.setIcon( imgLoader.loadIcon( 4 ) ); button.setText( "" ); revealMines(); disableBoard();
            break;
        }
      }
    }
  }

  public int countFlags() {
    javax.swing.ImageIcon flag = imgLoader.loadIcon( 5 );
    int counter = 0;

    for( int i = 0; i < buttons.size(); i++ ) {
      if( buttons.get( i ).getIcon() == flag ) {
        counter ++;
      }
    }

    return Level.getMines() - counter;
  }

  public void revealFlags() {
    javax.swing.ImageIcon icon = imgLoader.loadIcon( 5 );
    javax.swing.ImageIcon question = imgLoader.loadIcon( 8 );

    for( int i = 0; i < buttons.size(); i++ ) {
      JButton B = buttons.get( i );
      if( B.getIcon() == question || ( B.getBorder().toString().contains( "Bevel" ) && B.getIcon() == null ) ) {
        B.setIcon( icon );
      }
    }

    ticking.mute( true );
    timing.go( false );

    repaint();
  }

  public void revealMines() {
    for( int i = 0; i < values.size(); i++ ) {
      if( values.get( i ).equals( "*" ) && buttons.get( i ).getIcon() == null ) {
        buttons.get( i ).setIcon( imgLoader.loadIcon( 6 ) );
        buttons.get( i ).setBorder( lineBorder );
      }

      if( ! values.get( i ).equals( "*" ) && buttons.get( i ).getIcon() == imgLoader.loadIcon( 5 ) ) {
        buttons.get( i ).setIcon( imgLoader.loadIcon( 7 ) );
      }
    }

    if( com.java.utils.Menu.sound.isSelected() ) {
      new com.java.loaders.SoundLoader( 1 ).start();// Play explosion if 'Sound' option is enabled
    }        

    ticking.mute( true );
    timing.go( false );
    start.setIcon( imgLoader.loadIcon( 3 ) );
    repaint();
  }

  public void revealBlank( String location, int P ) {
    X = Integer.parseInt( location.split( "," )[0] );
    Y = Integer.parseInt( location.split( "," )[1] );
    position.add( X + "" + Y );
    int cols = Level.getWidth(), rows = Level.getHeight();

    while( position.size() > 0 ) {
      if( X == 0 && Y == 0 ) {
        clicks( P, range1 );
      } else if( X == 0 && Y == cols - 1 ) {
        clicks( P, range2 );
      } else if( X == rows - 1 && Y == 0 ) {
        clicks( P, range8 );
      } else if( X == rows - 1 && Y == cols - 1 ) {
        clicks( P, range9 );
      } else if( X == 0 ) {
        clicks( P, range3 );
      } else if( Y == 0 ) {
        clicks( P, range4 );
      } else if( X == rows - 1 ) {
        clicks( P, range5 );
      } else if( Y == cols - 1 ) {
        clicks( P, range6 );
      } else {
        clicks( P, range7 );
      }

      position.remove( 0 );
    }
  }

  public void clicks( int P, String[] R ) {
    for( int i = 0; i < R.length; i++ ) {
      JButton B = buttons.get( P + Integer.parseInt( R[ i ] ) );

      if( B.getBorder().toString().contains( "Bevel" ) ) {
        updateAppearance( buttons.get( P + Integer.parseInt( R[ i ] ) ) );

        if( values.get( P + Integer.parseInt( R[ i ] ) ).equals( "_" ) ) {
          position.add( coord.get( P + Integer.parseInt( R[ i ] ) ) );
        }  
      }
    }

    repaint();
  }

  public void updateAppearance( JButton B ) {
    updateCellForeground( B );
    
    if( B.getText().equals( "" ) && B.getIcon() == null ) {
      revealBlank( coord.get( Integer.parseInt( B.getName() ) ), Integer.parseInt( B.getName() ) );
    }
  }

  public void updateCellForeground( JButton B ) {
    String value = values.get( Integer.parseInt( B.getName() ) );
    B.setText( value.equals( "_" ) ? "" : value );

    if( B.getBorder().toString().contains( "Bevel" ) ) {
      cellCounter ++;
    }

    if( cellCounter == Level.getHeight() * Level.getWidth() - Level.getMines() ) {
      start.setIcon( imgLoader.loadIcon( 2 ) );
    }

    if( B.getText().length() > 0 && B.getIcon() != null ) {
      B.setText( "" );
    } else {
      B.setBorder( lineBorder );
      B.setOpaque( false );
    }

    switch( B.getText() ) {
      case "1":  B.setForeground( Color.BLUE.brighter() );
        break;
      case "2":  B.setForeground( Color.GREEN.darker() );
        break;
      case "3":  B.setForeground( Color.RED );
        break;
      case "4":  B.setForeground( Color.BLUE.darker() );
        break;
      case "5":  B.setForeground( Color.RED.darker() );
        break;
      case "6":  B.setForeground( Color.CYAN.darker() );
        break;
      case "7": B.setForeground( Color.BLACK );
        break;
      case "8":  B.setForeground( Color.GRAY );
        break;
    }
  }

  public void setup() {
    timing = new com.java.timing.Timekeeper();
    launch = true;
    
    for( int i = 0; i < Level.getHeight(); i++ ) {
      for( int j = 0; j < Level.getWidth(); j++ ) {
        map[ i ][ j ] = "_";
      }
    }

    setMines();
    seek();
    //view(); // Print board on console [for testings only]
  }

  public void setMines() {
    int x, y;
    for( int i = 0; i < Level.getMines(); i++ ) {  
      do {
        x = (int) Math.floor( Math.random() * Level.getHeight() );
        y = (int) Math.floor( Math.random() * Level.getWidth() );
      } while( map[ x ][ y ].equals( "*" ) );

      map[ x ][ y ] = "*";
    }

    values.removeAll( values );
    for( int i = 0; i < Level.getHeight(); i++ ) {
      for( int j = 0; j < Level.getWidth(); j++ ) {
        values.add( map[ i ][ j ] );
        coord.add( i + "," + j );
      }
    }
  }

  public void seek() {
    int cols = Level.getWidth();
    int rows = Level.getHeight();
    range1 = new String[] { "1", cols + "", cols + 1 + "" };// TOP LEFT CORNER ( i & j == 0 )
    range2 = new String[] { "-1", cols - 1 + "", cols + "" };// TOP RIGHT CORNER ( i == 0 & j == size - 1  )
    range3 = new String[] { "-1", "1", cols - 1 + "", cols + "", cols + 1 + "" };// FIRST ROW ( i == 0 )
    range4 = new String[] { cols * -1 + "", ( cols - 1 ) * - 1 + "", "1", cols + "", cols + 1 + "" };// FIRST COLUMN ( j == 0 )
    range5 = new String[] { ( cols + 1 ) * -1 + "", cols * -1 + "", ( cols - 1 ) * -1 + "", "-1", "1" };// LAST ROW ( i == size - 1 )
    range6 = new String[] { ( cols + 1 ) * -1 + "", cols * -1 + "", "-1", cols - 1 + "", cols + "" };// LAST COLUMN ( j == size - 1 )
    range7 = new String[] { ( cols + 1 ) * -1 + "", cols * -1 + "", ( cols - 1 ) * -1 + "", "-1", "1", cols - 1 + "", cols + "", cols + 1 + "" };// EVERY ( full surrounded )
    range8 = new String[] { cols * -1 + "", ( cols - 1 ) * -1 + "", "1" };// BOTTOM LEFT CORNER ( i == size - 1 & j == 0 )
    range9 = new String[] { ( cols + 1 ) * -1 + "", cols * -1 + "", "-1" };// BOTTOM RIGHT CORNER ( i == size - 1 & j == size - 1 )
    int pos = 0;

    for( int i = 0; i < Level.getHeight(); i++ ) {
      for( int j = 0; j < Level.getWidth(); j++ ) {
        if( map[ i ][ j ].equals( "_" ) ) {
          if( i == 0 && j == 0 ) {
            surr( pos, range1 );
          } else if( i == 0 && j == cols - 1 ) {
            surr( pos, range2 );
          } else if( i == rows - 1 && j == 0 ) {
            surr( pos, range8 );
          } else if( i == rows - 1 && j == cols - 1 ) {
            surr( pos, range9 );
          } else if( i == 0 ) {
            surr( pos, range3 );
          } else if( j == 0 ) {
            surr( pos, range4 );
          } else if( i == rows - 1 ) {
            surr( pos, range5 );
          } else if( j == cols - 1 ) {
            surr( pos, range6 );
          } else {
            surr( pos, range7 );
          }
        }

        pos ++;
      }
    }
  }

  public void surr( int P, String[] R ) {
    int surround = 0, pointer = 0;

    for( int j = 0; j < R.length; j++ ) {
      if( values.get( P + Integer.parseInt( R[ j ] ) ).equals( "*" ) ) {
        surround ++;
      }
    }

    if( surround > 0 ) {
      values.set( P, String.valueOf( surround ) );
    }

    for( int i = 0; i < Level.getHeight(); i++ ) {
      for( int j = 0; j < Level.getWidth(); j++ ) {
        map[ i ][ j ] = values.get( pointer ++ );
      }
    }
  }

  /*//Print board in console [for testings only]
  public void view() {
    for( int i = 0; i < Level.getHeight(); i++ ) {
      for( int j = 0; j < Level.getWidth(); j++ ) {
        System.out.print( map[ i ][ j ] + "  " );
      }
      System.out.println();
    }
  }*/

  public void restart() {
    setup();
    clearButtons();
    enableBoard();
    minesLeft.setText( DF.format( countFlags() ) );
    cellCounter = 0;
    launch = true;
    timing.reset( timing.step );
  }

  public void changeSkill() {
    layout = new GridLayout( Level.getHeight(), Level.getWidth() );
    body.setLayout( layout );
    redimension();

    body.removeAll();
    buttons.removeAll( buttons );
    position.removeAll( position );
    coord.removeAll( coord );
    values.removeAll( values );

    setButtons();
  }

  public void clearButtons() {
    for( int i = 0; i < buttons.size(); i++ ) {
      buttons.get( i ).setBorder( BorderFactory.createRaisedBevelBorder() );
      buttons.get( i ).setIcon( null );
      buttons.get( i ).setText( "" );
      buttons.get( i ).setOpaque( true );
    }

    start.setIcon( imgLoader.loadIcon( 0 ) );
    repaint();
  }

  public void disableBoard() {
    for( JButton B : buttons ) {
      B.removeActionListener( B.getActionListeners()[0] );
    }
  }

  public void enableBoard() {
    for( JButton B : buttons ) {
      if( B.getActionListeners().length > 0 ) {
        break;
      }

      B.addActionListener( event -> check( B, event ) );
    }
  }

  public void redimension() {
    components.setSize( getWidth() - 4, getHeight() - 49 );
    header.setSize( components.getWidth() - 16, header.getHeight() );
    minesLeft.setLocation( 6, 6 );
    start.setLocation( header.getWidth() / 2 - start.getWidth() / 2, 5 );
    time.setLocation( header.getWidth() - time.getWidth() - 6, 6 );
    timeB.setLocation( header.getWidth() - time.getWidth() - 6, 6 );
    body.setSize( components.getWidth() - 16, components.getHeight() - 67 );
  }
}
