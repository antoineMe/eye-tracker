import static org.bytedeco.javacpp.opencv_core.*;

import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import javax.imageio.ImageIO;


public class FaceDetection{


	public static void main(String[] args){
		IplImage img = cvLoadImage("ressources/test.jpg");
		detect(img);		
	}	

	public static IplImage detect(IplImage src){
		CvHaarClassifierCascade cascade = new 
				CvHaarClassifierCascade(cvLoad("haarcascade_frontalface_default.xml"));
		CvMemStorage storage = CvMemStorage.create();
		CvSeq sign = cvHaarDetectObjects(
				src,
				cascade,
				storage,
				1.5,
				3,
				CV_HAAR_DO_CANNY_PRUNING);

		cvClearMemStorage(storage);

		int total_Faces = sign.total();		

		for(int i = 0; i < total_Faces; i++){
			CvRect r = new CvRect(cvGetSeqElem(sign, i));
			cvRectangle (
					src,
					cvPoint(r.x(), r.y()),
					cvPoint(r.width() + r.x(), r.height() + r.y()),
					CvScalar.RED,
					2,
					LINE_AA,
					0);

		}

		return src;

	}			
}