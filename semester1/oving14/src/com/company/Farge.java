package jog12;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * NeHe Lesson #2 (JOGL 2 Port): Basic 2D Shapes
 * @author Tomas Holt, based on code from Hock-Chuan Chua (May 2012)
 * @version October 2016
 */

/* Main class which extends GLCanvas. This means that this is a OpenGL canvas.
   We will use OpenGL commands to draw on this canvas.
   This implementation has no animation or user input.
*/
public class Farge extends GLCanvas implements GLEventListener {
    // constants
    private static String TITLE = "Farge ting";
    private static final int CANVAS_WIDTH = 320*5;  // width of the drawable
    private static final int CANVAS_HEIGHT = 240*5; // height of the drawable
    private float rotAngleX;
    private float rotAngleY;
    private float rotAngleZ;

    // Setup OpenGL Graphics Renderer
    private GLU glu;  // for the GL Utility
    private GLUT glut = new GLUT();
    /** Constructor to setup the GUI for this Component */

    public Farge() {
      this.addGLEventListener(this);
      this.addKeyListener(new RotateKeyListener());
    }

// ------ Implement methods declared in GLEventListener (init,reshape,display,dispose)

   /**
    * Called immediately after the OpenGL context is initialized. Can be used
    * to perform one-time initialization. Run only once.
    */
     public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
      glu = new GLU();                         // get GL Utilities
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
      gl.glEnable(GL2.GL_DEPTH_TEST);           // enables depth testing
      gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
   }

   /**
    * Handler for window re-size event. Also called when the drawable is
    * first set to visible
    */

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context

      if (height == 0) height = 1;   // prevent divide by zero
      float aspect = (float)width / height;

      //Set the view port (display area) to cover the entire window
      //gl.glViewport(0, 0, width, height);

      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
      gl.glLoadIdentity();             // reset projection matrix
      glu.gluPerspective(45.0, aspect, 0.1, 1000.0); // fovy, aspect, zNear, zFar

      // Enable the model-view transform
      gl.glMatrixMode(GL2.GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
   }

    private class RotateKeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            if(e.getKeyChar() == 'a') {
                rotAngleX += 5;//
                Farge.this.repaint();//repaint our canvas
            } else if(e.getKeyChar() == 'd'){
                rotAngleX -= 5;//
                Farge.this.repaint();//repaint our canvas
            } else if(e.getKeyChar() == 'w'){
                rotAngleZ += 5;//
                Farge.this.repaint();//repaint our canvas
            } else if(e.getKeyChar() == 's'){
                rotAngleZ -= 5;//
                Farge.this.repaint();//repaint our canvas
            } else if(e.getKeyChar() == ','){
                rotAngleY += 5;//
                Farge.this.repaint();//repaint our canvas
            }
            else if(e.getKeyChar() == '.'){
                rotAngleY -= 5;//
                Farge.this.repaint();//repaint our canvas
            }
        }
    }

    private static final double[][] cornerPositions = new double[][]{
            {-2.0,-2.0,-2.0},{-2.0,2.0,2.0},{-2.0,2.0,-2.0},{-2.0,-2.0,2.0},{2.0,2.0,-2.0},{2.0,-2.0,2.0},{2.0,-2.0,-2.0},{2.0,2.0,2.0}
    };

    private static final float[][] colors = new float[][]{
            {1.0f, 0.0f, 0.0f}, {0.0f, 1.0f, 0.0f}, {0.0f, 0.0f, 1.0f}, {1.0f, 1.0f, 0.0f}, {1.0f, 0.0f, 1.0f}, {0.0f, 1.0f, 1.0f}, {0.0f, 0.0f, 0.0f}, {1.0f, 1.0f, 1.0f}
    };

    public void drawSide(GL2 gl, int a, int b, int c, int d){
    gl.glColor3f(colors[a][0], colors[a][1], colors[a][2]);
    gl.glBegin(GL2.GL_POLYGON);
    gl.glVertex3d(cornerPositions[a][0], cornerPositions[a][1], cornerPositions[a][2]);
    gl.glVertex3d(cornerPositions[b][0], cornerPositions[b][1], cornerPositions[b][2]);
    gl.glVertex3d(cornerPositions[c][0], cornerPositions[c][1], cornerPositions[c][2]);
    gl.glVertex3d(cornerPositions[d][0], cornerPositions[d][1], cornerPositions[d][2]);
    gl.glEnd();
    }

    public void lineHelp(GL2 gl){
       // X
       gl.glBegin(GL2.GL_LINES);
       gl.glColor3f(1.0f,0.0f,0.0f);
       gl.glVertex3d(99.0,0.0,0.0);
       gl.glVertex3d(-99.0,0.0,0.0);
       gl.glEnd();

       // Y
       gl.glBegin(GL2.GL_LINES);
       gl.glColor3f(0.0f,1.0f,0.0f);
       gl.glVertex3d(0.0,99.0,0.0);
       gl.glVertex3d(0.0,-99.0,0.0);
       gl.glEnd();

       // Z
       gl.glBegin(GL2.GL_LINES);
       gl.glColor3f(0.0f,0.0f,1.0f);
       gl.glVertex3d(0.0,0.0,99.0);
       gl.glVertex3d(0.0,0.0,-99.0);
       gl.glEnd();

   }

    public void flateVenstre(GL2 gl){
       gl.glColor3f(0.0f, 1.0f, 0.0f);
       int a = 0, b = 2, c = 1, d = 3;
       gl.glBegin(GL2.GL_POLYGON);

       gl.glVertex3d(cornerPositions[a][0], cornerPositions[a][1], cornerPositions[a][2]);
       gl.glVertex3d(cornerPositions[b][0], cornerPositions[b][1], cornerPositions[b][2]);
       gl.glVertex3d(cornerPositions[c][0], cornerPositions[c][1], cornerPositions[c][2]);
       gl.glVertex3d(cornerPositions[d][0], cornerPositions[d][1], cornerPositions[d][2]);
       gl.glEnd();
   }

    public void flateHøyre(GL2 gl){
       int a = 7, b = 5, c = 6, d = 4;

       gl.glColor3f(1.0f, 0.0f, 0.0f);
       gl.glBegin(GL2.GL_POLYGON);
       gl.glVertex3d(cornerPositions[a][0], cornerPositions[a][1], cornerPositions[a][2]);
       gl.glVertex3d(cornerPositions[b][0], cornerPositions[b][1], cornerPositions[b][2]);
       gl.glVertex3d(cornerPositions[c][0], cornerPositions[c][1], cornerPositions[c][2]);
       gl.glVertex3d(cornerPositions[d][0], cornerPositions[d][1], cornerPositions[d][2]);
       gl.glEnd();
   }

    public void flateBakre(GL2 gl){
       int a = 0, b = 2, c = 4, d = 6;

       gl.glColor3f(1.0f, 1.0f, 0.0f);
       gl.glBegin(GL2.GL_POLYGON);
       gl.glVertex3d(cornerPositions[a][0], cornerPositions[a][1], cornerPositions[a][2]);
       gl.glVertex3d(cornerPositions[b][0], cornerPositions[b][1], cornerPositions[b][2]);
       gl.glVertex3d(cornerPositions[c][0], cornerPositions[c][1], cornerPositions[c][2]);
       gl.glVertex3d(cornerPositions[d][0], cornerPositions[d][1], cornerPositions[d][2]);
       gl.glEnd();
   }

    public void flateForan(GL2 gl){

       int a = 3, b = 1, c = 7, d = 5;

       gl.glColor3f(0.0f, 0.0f, 1.0f);
       gl.glBegin(GL2.GL_POLYGON);
       gl.glVertex3d(cornerPositions[a][0], cornerPositions[a][1], cornerPositions[a][2]);
       gl.glVertex3d(cornerPositions[b][0], cornerPositions[b][1], cornerPositions[b][2]);
       gl.glVertex3d(cornerPositions[c][0], cornerPositions[c][1], cornerPositions[c][2]);
       gl.glVertex3d(cornerPositions[d][0], cornerPositions[d][1], cornerPositions[d][2]);
       gl.glEnd();

   }

    public void flateOpp(GL2 gl){

       int a = 2, b = 1, c = 7, d = 4;

       gl.glColor3f(1.0f, 0.0f, 1.0f);
       gl.glBegin(GL2.GL_POLYGON);
       gl.glVertex3d(cornerPositions[a][0], cornerPositions[a][1], cornerPositions[a][2]);
       gl.glVertex3d(cornerPositions[b][0], cornerPositions[b][1], cornerPositions[b][2]);
       gl.glVertex3d(cornerPositions[c][0], cornerPositions[c][1], cornerPositions[c][2]);
       gl.glVertex3d(cornerPositions[d][0], cornerPositions[d][1], cornerPositions[d][2]);
       gl.glEnd();
   }

    public void flateNed(GL2 gl){

       int a = 0, b = 3, c = 5, d = 6;

       gl.glColor3f(1.0f, 1.0f, 1.0f);
       gl.glBegin(GL2.GL_POLYGON);
       gl.glVertex3d(cornerPositions[a][0], cornerPositions[a][1], cornerPositions[a][2]);
       gl.glVertex3d(cornerPositions[b][0], cornerPositions[b][1], cornerPositions[b][2]);
       gl.glVertex3d(cornerPositions[c][0], cornerPositions[c][1], cornerPositions[c][2]);
       gl.glVertex3d(cornerPositions[d][0], cornerPositions[d][1], cornerPositions[d][2]);
       gl.glEnd();
   }

    public void drawCube(GL2 gl){
        drawSide(gl, 0, 2, 1, 3); // Venstre
        drawSide(gl, 7, 5, 6 ,4); // Hoyre
        drawSide(gl, 0, 2 ,4 ,6); // Bak
        drawSide(gl, 3, 1, 7, 5); // Foran
        drawSide(gl, 2, 1, 7, 4); // Opp
        drawSide(gl, 0, 3, 5, 6); // Ned
    } // drawCube END



   /**
    * Called by OpenGL for drawing
    */
   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
      gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
      gl.glLoadIdentity();  // reset the model-view matrix

      // ----- Oppgave -----
      // translated left and into the screen
       glu.gluLookAt(rotAngleX + 50, rotAngleY + 50, rotAngleZ + 50,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f);

       // Nede venstre
       gl.glViewport(0,0 ,getWidth()/2, getHeight()/2);
       lineHelp(gl);
       drawCube(gl);

       // Nede hoyre
       gl.glPushMatrix();
       gl.glViewport(getWidth()/2, 0 ,getWidth()/2, getHeight()/2);
       lineHelp(gl);
       gl.glRotated(-65,1,1,1);
       drawCube(gl);
       gl.glPopMatrix();

       // Ovre hoyre
       gl.glPushMatrix();
       gl.glViewport(getWidth()/2, getHeight()/2 ,getWidth()/2, getHeight()/2);
       lineHelp(gl);
       gl.glRotated(20,1,1,1);
       gl.glTranslatef(2.0f,2.0f,2.0f);
       drawCube(gl);
       gl.glPopMatrix();

       // Ovre venstre
       gl.glPushMatrix();
       gl.glViewport(0, getHeight()/2 ,getWidth()/2, getHeight()/2);
       lineHelp(gl);
       gl.glRotated(45,1,1,1);
       gl.glTranslatef(-2.0f,-2.0f,2.0f);
       drawCube(gl);
       gl.glPopMatrix();
   } // display END

   /**
    * Called before the OpenGL context is destroyed. Release resource such as buffers.
    */
   public void dispose(GLAutoDrawable drawable) { }

   /** The entry main() method to setup the top-level JFrame with our OpenGL canvas inside */
   public static void main(String[] args) {
      GLCanvas canvas = new Farge();
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

      final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
      frame.getContentPane().add(canvas);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//stop program
      frame.setTitle(TITLE);
      frame.pack();
      frame.setVisible(true);
   }
}
