/*
 * Copyright 2015 Sivarat Malapet all rights reserved.
 */
package testOpenCV;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

//
// Detects faces in an image, draws boxes around them, and writes the results
// to "faceDetection.png".
//
class DetectFaceDemo {
  public void run(String picName) {
	  
    System.out.println("\nRunning DetectFaceDemo");

    // Create a face detector from the cascade file in the resources
    // directory.
    CascadeClassifier faceDetector = new CascadeClassifier("cascades/haarcascade_frontalface_default.xml");
    Mat image = Highgui.imread(picName);

    // Detect faces in the image.
    // MatOfRect is a special container class for Rect.
    MatOfRect faceDetections = new MatOfRect();
    faceDetector.detectMultiScale(image, faceDetections);

    System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

    // Draw a bounding box around each face.
    for (Rect rect : faceDetections.toArray()) {
        Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
    }

    // Save the visualized detection.
    String filename = "faces/faceDetection.jpg";
    System.out.println(String.format("Writing %s", filename));
    Highgui.imwrite(filename, image);
  }
}

public class FaceDetectorTest {
  public static void main(String[] args) {
    System.out.println("FaceDetectorTest started..");

    // Load the native library.
    //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    System.load("/home/sivarat/opencv-2.4.11/build/lib/libopencv_java2411.so");
    new DetectFaceDemo().run("faces/lena.jpg");
  }
}
