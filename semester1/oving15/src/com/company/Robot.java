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
public class Robot extends GLCanvas implements GLEventListener {
    // constants
    private static String TITLE = "Robot ting";
    private static final int CANVAS_WIDTH = 320*5;  // width of the drawable
    private static final int CANVAS_HEIGHT = 240*5; // height of the drawable
    private double rotAngleX, rotAngleY, rotAngleZ;
    private float xPos, yPos, zPos;
    // Setup OpenGL Graphics Renderer
    private GLU glu;  // for the GL Utility
    private GLUT glut = new GLUT();
    /** Constructor to setup the GUI for this Component */

    public Robot() {
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

    private boolean bin = false;

    private class RotateKeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent e) {

            switch(e.getKeyChar()){
                case 'a' :
                    rotAngleX += 5;//
                    Robot.this.repaint();//repaint our canvas
                    break;

                case 'd' :
                    rotAngleX -= 5;//
                    Robot.this.repaint();//repaint our canvas
                    break;

                case 'w' :
                    rotAngleZ += 5;//
                    Robot.this.repaint();//repaint our canvas
                    break;

                case 's' :
                    rotAngleZ -= 5;//
                    Robot.this.repaint();//repaint our canvas
                    break;

                case ',' :
                    rotAngleY += 5;//
                    Robot.this.repaint();//repaint our canvas
                    break;

                case '.' :
                    rotAngleY -= 5;//
                    Robot.this.repaint();//repaint our canvas
                    break;

                case 'i' :
                    yPos++;
                    if(leftLegAngle > 45){
                        bin = true;
                    }
                    if(leftLegAngle < -45){
                        bin = false;
                    }
                    if(!bin){
                        rightLegAngle -= 10;
                        leftLegAngle += 10;
                    } else {
                        rightLegAngle += 10;
                        leftLegAngle -= 10;
                    }
                    Robot.this.repaint();
                    break;

                case 'k' :
                    yPos--;
                    if(leftLegAngle > 45){
                        bin = false;
                    }
                    if(leftLegAngle < -45){
                        bin = true;
                    }
                    if(!bin){
                        rightLegAngle += 10;
                        leftLegAngle -= 10;
                    } else {
                        rightLegAngle -= 10;
                        leftLegAngle += 10;

                    }
                    Robot.this.repaint();
                    break;

                case 'j' :
                    xPos--;
                    Robot.this.repaint();
                    break;

                case 'l' :
                    xPos++;
                    Robot.this.repaint();
                    break;

                case 'n' :
                    zPos++;
                    Robot.this.repaint();
                    break;

                case 'm' :
                    zPos--;
                    Robot.this.repaint();
                    break;

                case 'u' :
                    rot -= 5;
                    Robot.this.repaint();
                    break;

                case 'o' :
                    rot += 5;
                    Robot.this.repaint();
                    break;
            }
        }
    }

    private static final int height = 8;
    private double rightLegAngle = 45, leftLegAngle = -45;
    private double rot;

    public void lineHelp(GL2 gl){
        // X RØD
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(1.0f,0.0f,0.0f);
        gl.glVertex3d(999.0,0.0,0.0);
        gl.glVertex3d(-999.0,0.0,0.0);
        gl.glEnd();

        // Y GUL
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(1.0f,1.0f,0.0f);
        gl.glVertex3d(0.0,999.0,0.0);
        gl.glVertex3d(0.0,-999.0,0.0);
        gl.glEnd();

        // Z BLÅ
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(0.0f,0.0f,1.0f);
        gl.glVertex3d(0.0,0.0,999.0);
        gl.glVertex3d(0.0,0.0,-999.0);
        gl.glEnd();
    }

    public void drawGround(GL2 gl,double size){
        if (size < 200){
            size = 300;
        }
        gl.glColor3f(0.0f,1.0f, 0.0f);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3d(size,0,size);
        gl.glVertex3d(-size, 0, size);
        gl.glVertex3d(-size, 0, -size);
        gl.glVertex3d(size, 0, -size);
        gl.glEnd();
    }

    public void drawRLeg(GL2 gl){
        gl.glPushMatrix();
        gl.glColor3f(1.0f,0.0f,0.0f);
        gl.glRotated(90,1,0,0);
        gl.glTranslated(height/4,0, -height);
        gl.glTranslated(xPos, -yPos, -zPos);
        gl.glRotated(rightLegAngle,1,0,0);
        glut.glutSolidCylinder(height/8, height, 15, 15);
        gl.glPopMatrix();
    }

    public void drawLLeg(GL2 gl){
        gl.glPushMatrix();
        gl.glColor3f(1.0f,0.0f,0.0f);
        gl.glRotated(90,1,0,0);
        gl.glTranslated(-height/4,0, -height);
        gl.glTranslated(xPos, -yPos, -zPos);
        gl.glRotated(leftLegAngle,1,0,0);
        glut.glutSolidCylinder(height/8, height, 15, 15);
        gl.glPopMatrix();
    }

    public void drawTorso(GL2 gl){
        gl.glPushMatrix();
        gl.glRotated(-90,1,0,0);
        gl.glTranslated(0,0, height);
        gl.glTranslated(xPos, yPos, zPos);
        glut.glutSolidCylinder(height/2, height/1.14285714286, 15, 15);
        gl.glPopMatrix();
    }

    public void drawLArm(GL2 gl){
        gl.glPushMatrix();
        gl.glRotated(90,1,0,0);
        gl.glTranslated(-height/1.6,0,-height * 1.83);
        gl.glTranslated(xPos, -yPos, -zPos);
        gl.glRotated(rightLegAngle,1,0,0);
        glut.glutSolidCylinder(height/8, height, 15, 15);
        gl.glPopMatrix();
    }

    public void drawRArm(GL2 gl){
        gl.glPushMatrix();
        gl.glRotated(90,1,0,0);
        gl.glTranslated(height/1.6,0,-height * 1.83);
        gl.glTranslated(xPos, -yPos, -zPos);
        gl.glRotated(leftLegAngle,1,0,0);
        glut.glutSolidCylinder(height/8, height, 15, 15);
        gl.glPopMatrix();
    }

    public void drawHead(GL2 gl){
        gl.glPopMatrix();
        gl.glColor3f(1.0f,1.0f,0.0f);
        gl.glRotated(90,0,1,0);
        gl.glTranslated(0,height * 2.1, 0);
        gl.glTranslated(yPos, zPos, xPos);
        glut.glutSolidTeapot(height/3.2);
        gl.glPopMatrix();
    }

    public void rotate(GL2 gl){
        gl.glRotated(rot,0,1,0);
    }

    /**
     * Called by OpenGL for drawing
     */

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix

        // ----- Oppgave -----
            glu.gluLookAt(rotAngleX + 50, rotAngleY + 50, rotAngleZ + 50, xPos, zPos, -yPos, 0.0f, 1.0f, 0.0f);
            lineHelp(gl);
            drawGround(gl, 400);
            rotate(gl);
            drawLLeg(gl);
            drawRLeg(gl);
            drawTorso(gl);
            drawLArm(gl);
            drawRArm(gl);
            drawHead(gl);
        rotate(gl);
    } // display END




    /**
     * Called before the OpenGL context is destroyed. Release resource such as buffers.
     */
    public void dispose(GLAutoDrawable drawable) { }

    /** The entry main() method to setup the top-level JFrame with our OpenGL canvas inside */
    public static void main(String[] args) {
        GLCanvas canvas = new Robot();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//stop program
        frame.setTitle(TITLE);
        frame.pack();
        frame.setVisible(true);
    }
}
