package com.java.utils;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import com.java.minesweeper.Minesweeper;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: Sublime Text 3 [build 3126].
* Date: 24/08/17, Time: 17:45:50.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ Menu Bar Utilities ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, Personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class Menu extends JMenuBar {
  public static JCheckBoxMenuItem beginner, intermediate, expert, custom;
  public static JCheckBoxMenuItem marks, sound;
  static JMenuItem newGame, best, about, exit;
  static Minesweeper M;
  static Custom C;
  static JMenu menuGame, menuHelp;

  public Menu( Minesweeper M ) {
    this.M = M;
    setBackground( Color.GRAY );
    setBorder( new javax.swing.border.LineBorder( Color.LIGHT_GRAY, 1 ) );
    menuGame = new JMenu( "Game" );
    menuGame.setBackground( java.awt.Color.GRAY );
    newGame = new JMenuItem( "New Game" );
    beginner = new JCheckBoxMenuItem( "Beginner" );
    beginner.setSelected( true );
    intermediate = new JCheckBoxMenuItem( "Intermediate" );
    expert = new JCheckBoxMenuItem( "Expert" );
    custom = new JCheckBoxMenuItem( "Custom..." );
    marks = new JCheckBoxMenuItem( "Marks (?)" );
    sound = new JCheckBoxMenuItem( "Sound" );
    best = new JMenuItem( "Best Times..." );
    exit = new JMenuItem( "Exit" );

    menuHelp = new JMenu( "Help" );
    menuHelp.setBackground( Color.GRAY );
    about = new JMenuItem( "About Minesweeper" );
    menuHelp.add( about );

    menuGame.add( newGame );
    menuGame.addSeparator();
    menuGame.add( beginner );
    menuGame.add( intermediate );
    menuGame.add( expert );
    menuGame.add( custom );
    menuGame.addSeparator();
    menuGame.add( marks );
    menuGame.add( sound );
    menuGame.addSeparator();
    menuGame.add( best );
    menuGame.addSeparator();
    menuGame.add( exit );
    add( menuGame );
    add( menuHelp );

    setListeners();

    if( beginner.isSelected() ) {
      M.setSize( 223, 319 );
      Level.setSize( 9, 9 );
      Level.setMines( 10 );
    } else if( intermediate.isSelected() ) {
      M.setSize( 377, 472 );
      Level.setSize( 16, 16 );
      Level.setMines( 40 );
    } else if( expert.isSelected() ) {
      M.setSize( 685, 472 );
      Level.setSize( 30, 16 );
      Level.setMines( 99 );
    } else {
      M.setSize( 685, 472 );
      Level.setSize( Custom.getCellsWidth(), Custom.getCellsHeight() );
      Level.setMines( Custom.getMines() );
    }

    M.setLocationRelativeTo( null );
    M.redimension();
  }

  public static void setListeners() {
    newGame.addActionListener( event -> M.restart() );

    beginner.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        if( beginner.isSelected() ) {
          M.setSize( 223, 319 );
          Level.setSize( 9, 9 );
          Level.setMines( 10 );
          M.setLocationRelativeTo( null );
          M.changeSkill();
          M.restart();
          intermediate.setSelected( false );
          expert.setSelected( false );
          custom.setSelected( false );
        }
        
        beginner.setSelected( true );
      }
    } );
    
    intermediate.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        if( intermediate.isSelected() ) {
          M.setSize( 377, 472 );
          Level.setSize( 16, 16 );
          Level.setMines( 40 );
          M.setLocationRelativeTo( null );
          M.changeSkill();
          M.restart();
          beginner.setSelected( false );
          expert.setSelected( false );
          custom.setSelected( false );
        }
        
        intermediate.setSelected( true );
      }
    } );

    expert.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        if( expert.isSelected() ) {
          M.setSize( 685, 472 );
          Level.setSize( 30, 16 );
          Level.setMines( 99 );
          M.setLocationRelativeTo( null );
          M.changeSkill();
          M.restart();
          beginner.setSelected( false );
          intermediate.setSelected( false );
          custom.setSelected( false );
        }

        expert.setSelected( true );
      }
    } );
    
    custom.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        new Custom( M ).setVisible( true );
        beginner.setSelected( false );
        intermediate.setSelected( false );
        expert.setSelected( false );
        custom.setSelected( true );
      }
    } );

    sound.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        com.java.timing.Timekeeper.comp = com.java.timing.Timekeeper.secs + 1;
      }
    } );

    best.addActionListener( event -> new com.java.stats.FastestSweepers().setVisible( true ) );
    about.addActionListener( event -> new About().setVisible( true ) );
    exit.addActionListener( event -> System.exit( 0 ) );
  }

  public static String getSelectedSkill() {
    return beginner.isSelected() ? "Beginner" : intermediate.isSelected() ? "Intermediate" : expert.isSelected() ? "Expert" : "Custom";
  }
}

