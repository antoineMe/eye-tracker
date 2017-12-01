import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import static org.bytedeco.javacpp.opencv_core.cvFlip;
 
public class Main {
 public static void main(String[] args) {
	 FaceDetection faceDetection = new FaceDetection();
  //Create canvas frame for displaying webcam.
     CanvasFrame canvas = new CanvasFrame("Webcam"); 
     OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
     
  //Set Canvas frame to close on exit
     canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);   
      
     //Declare FrameGrabber to import output from webcam
     FrameGrabber grabber = new OpenCVFrameGrabber("");  
      
      
     try {      
       
      //Start grabber to capture video
      grabber.start();      
       
      //Declare img as IplImage
      IplImage img;
       
      while (true) {
        
       //inser grabed video fram to IplImage img
       img = converter.convert(grabber.grab());
        
       //Set canvas size as per dimentions of video frame.
       canvas.setCanvasSize(grabber.getImageWidth(), grabber.getImageHeight()); 
        
       if (img != null) {      
         //Flip image horizontally
         cvFlip(img, img, 1);
        //Show video frame in canvas
         faceDetection.detect(img);
         canvas.showImage(converter.convert(img));               
        }
       }
      }
     catch (Exception e) {      
     }
	 
    }
}