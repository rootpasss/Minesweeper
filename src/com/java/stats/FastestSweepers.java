package com.java.stats;

import javax.swing.JDialog;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: Sublime Text 3 [build 3126].
* Date: 30/08/17, Time: 23:21:18.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Fastest Times View ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, Personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class FastestSweepers extends JDialog {
  JPanel contentPane;
  JButton reset, ok;
  JTextArea data;

  public FastestSweepers() {
    setTitle( "Fastest Mine Sweepers" );
    setSize( 349, 188 );
    setResizable( false );
    setModal( true );
    setLocationRelativeTo( null );
    setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

    contentPane = new JPanel();
    contentPane.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
    contentPane.setLayout( null );
    setContentPane( contentPane );

    setData();
    setButtons();
  }

  public void setButtons() {
    reset = new JButton( "Reset Scores" );
    reset.setSize( 110, 25 );
    reset.setFocusable( false );
    reset.setBorder( javax.swing.BorderFactory.createRaisedBevelBorder() );
    reset.setBackground( java.awt.Color.LIGHT_GRAY );
    reset.setOpaque( true );
    reset.setLocation( getWidth() / 2 - reset.getWidth(), getHeight() - 60 );
    reset.addActionListener( event -> resetStats() );
    getContentPane().add( reset );

    ok = new JButton( "OK" );
    ok.setSize( 72, 25 );
    ok.setLocation( getWidth() / 2 + ok.getWidth() / 2, getHeight() - 60 );
    ok.setBorder( javax.swing.BorderFactory.createRaisedBevelBorder() );
    ok.setBackground( java.awt.Color.LIGHT_GRAY );
    ok.setOpaque( true );
    ok.addActionListener( event -> dispose() );
    getContentPane().add( ok );
  }

  public void setData() {
    data = new JTextArea( new Base().getStatistics() );
    data.setOpaque( false );
    data.setEditable( false );
    data.setFocusable( false );
    data.setFont( new Font( "Tahoma", Font.PLAIN, 14 ) );
    data.setBounds( 24, 5, getWidth() - 34, getHeight() - 80 );
    getContentPane().add( data );
  }

  public void resetStats() {
    new Base().resetBaseFile();
    dispose();
  }
}
