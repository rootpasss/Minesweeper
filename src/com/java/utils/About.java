package com.java.utils;

import javax.swing.JDialog;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import com.java.loaders.ImageLoader;

/**
* Engineered and developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 1.8.0_31, Standard Edition.
* Development Environment: Sublime Text 3 [build 3126].
* Date: 02/09/17, Time: 14:49:59.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [ 'About' Box ]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, Personal for non-commercial purposes.
* Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com || jtrejosb@icloud.com
* GitHub.com/jtrejosb
*/

public class About extends JDialog {
  JPanel contentPane;
  JLabel image;
  JTextArea data;
  JButton ok;

  public About() {
    setTitle( "About Minesweeper" );
    setSize( 418, 328 );
    setResizable( false );
    setModal( true );
    setLocationRelativeTo( null );
    setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

    contentPane = new JPanel();
    contentPane.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
    contentPane.setLayout( null );
    contentPane.setBackground( java.awt.Color.WHITE );
    setContentPane( contentPane );

    image = new JLabel();
    image.setBounds( 0, 0, getWidth(), 120 );
    image.setIcon( new ImageLoader().loadIcon( 9 ) );
    getContentPane().add( image );

    data = new JTextArea( getContents() );
    data.setBounds( 50, 120, getWidth() - 50, 145 );
    data.setFont( new java.awt.Font( "Tahoma", java.awt.Font.PLAIN, 14 ) );
    data.setEditable( false );
    getContentPane().add( data );

    ok = new JButton( "OK" );
    ok.setBounds( 330, getHeight() - 60, 70, 25 );
    ok.setBorder( javax.swing.BorderFactory.createRaisedBevelBorder() );
    ok.setBackground( java.awt.Color.LIGHT_GRAY );
    ok.setOpaque( true );
    ok.addActionListener( event -> dispose() );
    getContentPane().add( ok );
  }

  public String getContents() {
    char c = '\u00a9', h = '\u2665';
    return "\nMinesweeper Classic Edition\nVersion 2.5 [Written in Java 1.8.0 update 31]\nCopyright " + c + " 2017\nby Jhonny Trejos Barrios with " + h + "\n\nProduct licensed to:\n" + System.getProperty( "user.name" );
  }
}
