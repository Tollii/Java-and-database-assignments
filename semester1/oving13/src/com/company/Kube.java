import java.awt.*;
import javax.swing.*;

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
public class Kube extends GLCanvas implements GLEventListener {
   // constants
   private static String TITLE = "Kube ting";
   private static final int CANVAS_WIDTH = 320*5;  // width of the drawable
   private static final int CANVAS_HEIGHT = 240*5; // height of the drawable
    private float rotAngleX;
    private float rotAngleY;
    private float rotAngleZ;


   // Setup OpenGL Graphics Renderer
   private GLU glu;  // for the GL Utility
    private GLUT glut = new GLUT();
   /** Constructor to setup the GUI for this Component */

   public Kube() {
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
                rotAngleX += 10;//
                Kube.this.repaint();//repaint our canvas
            } else if(e.getKeyChar() == 'd'){
                rotAngleX -= 10;//
                Kube.this.repaint();//repaint our canvas
            } else if(e.getKeyChar() == 'w'){
                rotAngleZ += 10;//
                Kube.this.repaint();//repaint our canvas
            } else if(e.getKeyChar() == 's'){
                rotAngleZ -= 10;//
                Kube.this.repaint();//repaint our canvas
            } else if(e.getKeyChar() == ','){
                rotAngleY += 10;//
                Kube.this.repaint();//repaint our canvas
            }
            else if(e.getKeyChar() == '.'){
                rotAngleY -= 10;//
                Kube.this.repaint();//repaint our canvas
            }


        }


    }

   public void Terning1(GL2 gl){
       gl.glColor3f(1.0f,0.0f, 0.0f);
       gl.glTranslatef(0.0f, 0.0f, -20.0f);
       gl.glRotated(45,1,1,1);
       gl.glScaled(2, 2, 2);
       gl.glTranslatef(2.0f, -3.0f, 0.0f);

       // bak
       gl.glBegin(GL2.GL_LINE_LOOP); // draw using triangles and DOUBLE datatype
       gl.glVertex3d(1.0, 1.0, 0.0);
       gl.glVertex3d(1.0, 0.0, 0.0);
       gl.glVertex3d(0.0,0.0,0.0);
       gl.glVertex3d(0.0, 1.0, 0.0);
       gl.glEnd();

       // høyre
       gl.glBegin(GL2.GL_LINE_LOOP); // draw using triangles and DOUBLE datatype
       gl.glVertex3d(1.0, 1.0, 0.0);
       gl.glVertex3d(1.0, 0.0, 0.0);
       gl.glVertex3d(1.0,0.0,1.0);
       gl.glVertex3d(1.0, 1.0, 1.0);
       gl.glEnd();

       // venstre
       gl.glBegin(GL2.GL_LINE_LOOP); // draw using triangles and DOUBLE datatype
       gl.glVertex3d(0.0, 1.0, 0.0);
       gl.glVertex3d(0.0, 0.0, 0.0);
       gl.glVertex3d(0.0,0.0,1.0);
       gl.glVertex3d(0.0, 1.0, 1.0);
       gl.glEnd();

       // foran
       gl.glBegin(GL2.GL_LINE_LOOP); // draw using triangles and DOUBLE datatype
       gl.glVertex3d(1.0, 1.0, 1.0);
       gl.glVertex3d(1.0, 0.0, 1.0);
       gl.glVertex3d(0.0,0.0,1.0);
       gl.glVertex3d(0.0, 1.0, 1.0);
       gl.glEnd();

   }

   public void Terning2(GL2 gl){
       gl.glColor3f(0.0f,0.0f, 1.0f);
       gl.glTranslatef(-2.0f, 3.0f, 0.0f);
       gl.glRotated(-65,1,1,1);
       gl.glScaled(3, 3, 3);
       gl.glTranslatef(-2.0f, 1.0f, -4.0f);

       glut.glutWireCube(3);


   }
   /**
    * Called by OpenGL for drawing
    */
   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
      gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
      gl.glLoadIdentity();  // reset the model-view matrix

      // ----- Oppgave -----
      // translated left and into the screen
       glu.gluLookAt(rotAngleX,rotAngleY,rotAngleZ,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f);
       Terning1(gl);
       Terning2(gl);


   } // display END

   /**
    * Called before the OpenGL context is destroyed. Release resource such as buffers.
    */
   public void dispose(GLAutoDrawable drawable) { }

   /** The entry main() method to setup the top-level JFrame with our OpenGL canvas inside */
   public static void main(String[] args) {
      GLCanvas canvas = new Kube();
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

      final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
      frame.getContentPane().add(canvas);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//stop program
      frame.setTitle(TITLE);
      frame.pack();
      frame.setVisible(true);
   }
}
