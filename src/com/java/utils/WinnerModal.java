package com.java.utils;

import javax.swing.JDialog;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Color;
import java.awt.Font;

import com.java.stats.Base;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: Sublime Text 3 [build 3126].
* Date: 31/08/17, Time: 23:17:51.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Player Name Capture Dialog ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, Personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class WinnerModal extends JDialog {
  JPanel contentPane;
  Font serifFont = new Font( "Tahoma", Font.PLAIN, 14 );
  JLabel message;
  JTextField playerName;
  JButton save;
  String skill;
  int time;

  public WinnerModal( String skill, int time ) {
    this.skill = skill;
    this.time = time;
    setSize( 207, 169 );
    setUndecorated( true );
    setModal( true );
    setLocationRelativeTo( null );
    setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

    contentPane = new JPanel();
    contentPane.setBorder( new javax.swing.border.LineBorder( new Color( 0, 0, 128 ), 5 ) );
    contentPane.setBackground( Color.WHITE );
    contentPane.setLayout( null );
    setContentPane( contentPane );

    message = new JLabel( messageBody( skill ) );
    message.setBounds( 14, 0, getWidth() - 27, 75 );
    message.setFont( serifFont );
    getContentPane().add( message );

    playerName = new JTextField();
    playerName.setBorder( new javax.swing.border.LineBorder( Color.BLACK, 1 ) );
    playerName.setSize( 160, 25 );
    playerName.setLocation( getWidth() / 2 - playerName.getWidth() / 2, 80 );
    playerName.setFont( serifFont );
    playerName.addKeyListener( new KeyAdapter() {
      public void keyReleased( KeyEvent evt ) {
        save.setEnabled( playerName.getText().length() > 0 );
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
          storePlayer();
        }
      }
    } );
    getContentPane().add( playerName );

    save = new JButton( "OK" );
    save.setBorder( javax.swing.BorderFactory.createRaisedBevelBorder() );
    save.setSize( 70, 30 );
    save.setLocation( getWidth() / 2 - save.getWidth() / 2, 120 );
    save.setBackground( Color.LIGHT_GRAY );
    save.setOpaque( true );
    save.setEnabled( false );
    save.setFocusable( false );
    save.addActionListener( event -> storePlayer() );
    getContentPane().add( save );
  }

  public void storePlayer() {
    new Base().updateBaseFile( skill, time, playerName.getText() );
    dispose();
  }

  public String messageBody( String level ) {
    return "<html><p style=\"text-align:center;\">You have the fastest time for " + level.toLowerCase() + " level.<br>Please type your name.</p></html>";
  }
}
