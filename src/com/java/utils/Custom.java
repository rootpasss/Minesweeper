package com.java.utils;

import javax.swing.JDialog;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import com.java.minesweeper.Minesweeper;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: Sublime Text 3 [build 3126].
* Date: 28/08/17, Time: 13:48:04.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Custom Option Dialog ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, Personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class Custom extends JDialog {
  JPanel contentPane, inputs, buttons;
  JLabel labelW, labelH, labelM;
  static JTextField width, height, mines;
  JButton ok, cancel;
  Minesweeper M;

  public Custom( Minesweeper M ) {
    this.M = M;
    setTitle( "Custom Field" );
    setSize( 260, 160 );
    setResizable( false );
    setModal( true );
    setLocationRelativeTo( null );
    setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

    contentPane = new JPanel();
    contentPane.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
    contentPane.setLayout( null );
    setContentPane( contentPane );
    
    setInputs();
    setButtons();
  }

  public void setInputs() {
    inputs = new JPanel();
    inputs.setSize( 130, 100 );
    inputs.setLocation( 12, (getHeight() / 2 - inputs.getHeight() / 2) - 12 );
    inputs.setLayout( new GridLayout( 3, 2 ) );
    getContentPane().add( inputs );

    labelH = new JLabel( "Height:" );
    height = new JTextField( Level.getHeight() == 0 ? "9" : Level.getHeight() + "" );
    height.addFocusListener( new java.awt.event.FocusAdapter() {
      public void focusLost( java.awt.event.FocusEvent evt ) {
        cellsValidation( height, 2 );
      }
    } );
    inputs.add( labelH );
    inputs.add( height );

    labelW = new JLabel( "Width:" );
    width = new JTextField( Level.getWidth() == 0 ? "9" : Level.getWidth() + "" );
    width.addFocusListener( new java.awt.event.FocusAdapter() {
      public void focusLost( java.awt.event.FocusEvent evt ) {
        cellsValidation( width, 8 );
      }
    } );
    inputs.add( labelW );
    inputs.add( width );

    labelM = new JLabel( "Mines:" );
    mines = new JTextField( Level.getMines() == 0 ? "10" : Level.getMines() + "" );
    mines.addFocusListener( new java.awt.event.FocusAdapter() {
      public void focusLost( java.awt.event.FocusEvent evt ) {
        String txt = mines.getText();
        int totalCells = Integer.parseInt( width.getText() ) * Integer.parseInt( height.getText() );

        mines.setText( txt.length() == 0 ? "10" : Integer.parseInt( txt ) < 0 ? Integer.parseInt( txt ) * -1 + "" : txt );
        txt = mines.getText();//Update txt contents
        mines.setText( txt.length() == 0 || Integer.parseInt( txt ) == 0 || Integer.parseInt( txt ) >= totalCells ? "10" : txt );
      }
    } );
    inputs.add( labelM );
    inputs.add( mines );
  }

  public void setButtons() {
    buttons = new JPanel();
    buttons.setSize( 90, 65 );
    buttons.setLocation( getWidth() - buttons.getWidth() - 7, ( getHeight() / 2 - buttons.getHeight() / 2 ) - 12 );
    buttons.setLayout( null );
    getContentPane().add( buttons );

    ok = new JButton( "OK" );
    ok.setBorder( javax.swing.BorderFactory.createRaisedBevelBorder() );
    ok.setBounds( 5, 0, 70, 25 );
    ok.setBackground( java.awt.Color.LIGHT_GRAY );
    ok.setOpaque( true );
    ok.addActionListener( event -> applyDimension() );
    buttons.add( ok );
    getRootPane().setDefaultButton( ok );

    cancel = new JButton( "Cancel" );
    cancel.setBorder( javax.swing.BorderFactory.createRaisedBevelBorder() );
    cancel.setBounds( 5, 35, 70, 25 );
    cancel.setBackground( java.awt.Color.LIGHT_GRAY );
    cancel.setOpaque( true );
    cancel.addActionListener( event -> dispose() );
    buttons.add( cancel );
  }

  public void applyDimension() {
    //Biggest Level: 30W 24H 200M
    //Smallest frame: 217 167 aprox.
    // Level.setSize( Integer.parseInt( width.getText() ), Integer.parseInt( height.getText() ) );
    // M.setSize( 685, 472 );// This is the expert size! [change]
    int cols = Integer.parseInt( width.getText() ), rows = Integer.parseInt( height.getText() );

    if( cols == Level.getWidth() && rows == Level.getHeight() ) {
      M.restart();
    } else {
      Level.setSize( cols, rows );
      Level.setMines( Integer.parseInt( mines.getText() ) );
      M.setSize( cols * 22 + 24, rows * 22 + 123 );
      M.setLocationRelativeTo( null );
      M.changeSkill();
      M.restart();
      M.repaint();
    }

    dispose();
  }

  public void cellsValidation( JTextField F, int MIN ) {
    F.setText( F.getText().length() == 0 ? "9" : Integer.parseInt( F.getText() ) < 0 ? Integer.parseInt( F.getText() ) * -1 + "" : F.getText() );
    F.setText( F.getText().length() == 0 || Integer.parseInt( F.getText() ) < MIN ? "9" : F.getText() );
  }

  public static int getCellsWidth() {
    return Integer.parseInt( width.getText() );
  }

  public static int getCellsHeight() {
    return Integer.parseInt( height.getText() );
  }

  public static int getMines() {
    return Integer.parseInt( mines.getText() );
  }
}
