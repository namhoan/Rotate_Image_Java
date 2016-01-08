import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

/**
* PAmakeup - a class to manipulate images
* This class is a modification of a programming assignment created by Antonella DiLillo
* @author Antonella DiLillo
* @author Tim Hickey
*/

public class PAmakeup {
   
   /**
   * This flips the pixels horizontally by reversing each row of pixels
   * It uses a temporary variable to flip the 1st and last, 2nd and 2nd from last, etc.
   */
   public static int[][] flipHorizontal(int[][] pixelData) {
      for(int i=0; i<pixelData.length;i++){
         for(int j=0; j<pixelData[i].length/2; j++){
            // flip the packedpixels at col j and column width-1-j
            int width = pixelData[i].length;
            int tmp = pixelData[i][j];
            pixelData[i][j] = pixelData[i][width-1-j];
            pixelData[i][width-1-j]=tmp;
         }
      }
      System.out.println("Flipped Horizontally.");
      return pixelData;
   }

   public static int[][] flipVertical(int[][] pixelData) {
      System.out.println("flip-vertical is not yet implemented");
      char[][] temp = new char[pixelData.length][array[0].length];
      for(int i = 0; row < temp.length; i++) {
         temp[i] = pixelData[pixelData.length - i - 1];
      }
      System.out.println("Flipped Vertically.");
      return temp;
   }



   public static int[][] rotate90left(int[][] pixelData) {
      int col = pixelData.length;
      int row = pixelData[0].length;
      final int[][] rotatedPixel = new int[row][col];
      for(int i = 0; i < pixelData.length; i++) {
         for(int j = 0; j < pixelData[0].length; j++) {
            rotatedPixel[j][pixelData.length - 1 - i] = pixelData[i][j];
         }
      }
      return rotatedPixel;
   }

   
   
   
   /**
   * inverts the colors in the pixelData
   * by converting each packed color into an integer array of the R,G,B and A components and then
   * replacing each R,G,B component x by 255-x.
   * The code to invert a packed pixel is given below in the method invertRGB(pixelVal)
   */
   

   public static int[][] invert(int[][] pixelData) {
      System.out.println("invert is not yet implemented");
      int width = pixedData.getWidth();
      int height = pixelData.getHeight();
      int[][] result = new int[height][width];
      for(int i = 0; i < height; i++){
         for(int j = 0; j < width; j++){
            result[i][j] = pixelData.getRGB(x);
         }
      }
      System.out.println("Inverted.");
      return null;
   }



   
   /**
   * convert the pixel data to grayscale by replacing the rgb components
   * with their average value (r+g+b)/3
   */
   

   public static int[][] grayScale(int[][] pixelData){
      System.out.println("gray-scale is not yet implemented");
      for(int i = 0; i < pixelData.getWidth(); x++){
         for(int j = 0; j < pixelData.getHegith(); j++){
            int rgb = pixelData.getRGB(i,j);
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = (rgb & 0xff);
            
            int grayLevel = (r + g + b) / 3;
            int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
            pixelData(i, j, gray);
         }
      }
      System.out.println("Replaced color");
      return gray;
   }

   
   
   public static int invertRGB(int x){
      int[] rgba = getRGB(x);
      rgba[1]=255-rgba[1]; // red
      rgba[2]=255-rgba[2]; // green
      rgba[3]=255-rgba[3]; // blue
      return setRGB(rgba);
   }
   
   /**
   * returns pixelData with half the width and height OPTIONAL.
   * where consecutive 2x2 squares of pixels in the original image are averaged
   * to get a new 1x1 pixel in the new image
   */
   public static int[][] scaleDown(int[][] pixelData){ 
      System.out.println("invert is not yet implemented");
      int width = pixedData.getWidth();
      int height = pixelData.getHeight();
      int[][] newImage = new int[height][width];
      for(int i = 0; i < height; i++){
         for(int j = 0; j < width; j++){
            newImage[i][j] = pixelData[(int)(i/2)][(int)(j/2)];
         }
      }
      return newImage;
  }

   /**
   * returns pixelData with twice the width and height OPTIONAL.
   * where pixels at position (2i,2j) of the new image
   * are equal to the pixels at (i,j) of the original image
   * and the other pixels are averages of their neighbors (as in Conway's Life).
   */
   public static int[][] scaleUp(int[][] pixelData){
      int width = pixedData.getWidth();
      int height = pixelData.getHeight();
      int[][] newImage = new int[height][width];
      for(int i = 0; i < height; i++){
         for(int j = 0; j < width; j++){
            newImage[i][j] = pixelData[(int)(i*2)][(int)(j*2)];
         }
      }
      return newImage;
  }
   
   
   /* ****************************************************************
   DON'T CHANGE ANYTHING BELOW THIS LINE!!
   * ****************************************************************/
   
   
   /**
   * process a image using information from the command line
   * get the inputfile, outputfile, and command from the commandline
   * read the image file, apply the transformation, and write it out
   */
   public static void main(String[] args){
      if (args.length != 3) {
         System.out.println("Usage Error!! Use as follows:\n java PAmakeup inputfile outputfile command\n");
         return;
      }
      
      String inputFilename = args[0];
      String outputFileName = args[1];
      String command = args[2];
      
      // read the data into a BufferedImage
      BufferedImage im = loadImage(inputFilename);
      // and convert it into a 2D array of packed pixels
      int[][] pixelData = unpackPixels(im);
      
      // apply the appropriate operation on the pixelData
      switch (command) {
         case "flip-horiz": pixelData = flipHorizontal(pixelData); break;
		 case "flip-vert": pixelData = flipVertical(pixelData); break;
         case "invert": pixelData = invert(pixelData); break;
         case "rotate": pixelData = rotate90left(pixelData); break;
         case "gray-scale": pixelData = grayScale(pixelData); break;
         case "scale-up": pixelData = scaleUp(pixelData); break;
         case "scale-down": pixelData = scaleDown(pixelData); break;
         default: System.out.println("Unknown command: "+command);
      }
      
      // write the pixel data back to a file
      writeImage(pixelData,im,new File(outputFileName));
   }
   
   
   public static int[] getRGB(int x){
      int[] rgb = new int[4];
      rgb[0] = (x >> 24) & 0xff; // alpha transparency
      rgb[1] = (x >> 16) & 0xff; // red
      rgb[2] = (x >> 8) & 0xff;  // green
      rgb[3] = (x >> 0) & 0xff;  // blue
      return rgb;
   }
   
   
   public static int setRGB(int[] rgba ){
      return
      ((rgba[0] & 0xFF) << 24) | //alpha
      ((rgba[1] & 0xFF) << 16) | //red
      ((rgba[2] & 0xFF) << 8)  | //green
      ((rgba[3] & 0xFF) << 0); //blue
   }
   
   
   public static BufferedImage loadImage(String filename){
      //load file
      BufferedImage bi = null;
      InputStream in = null;
      
      //get file path from user
      File f = new File(filename);
      
      //initialize stream from file
      try {
         in = new FileInputStream(f);
      } catch (FileNotFoundException e) {
         System.out.println("Couldn't find file " + f.getAbsolutePath());
         return null;
      }
      
      //read stream into BufferedImage
      try {
         bi = ImageIO.read(in);
      } catch (IOException e) {
         System.out.println("Failed to read FileInputStream.");
         return null;
      }
      
      return bi;
   }
   
   //Uses bitwise operations to convert one integer into four channels,
   //each with a range from 0 to 255.
   public static int[][] unpackPixels(BufferedImage im) {
      System.out.println("Getting pixel values from packed data...");
      int height = im.getHeight();
      int width = im.getWidth();
      System.out.println(width);
      System.out.println(height);
      
      int[] packedData = im.getRGB(0, 0, width, height, null, 0, width);
      
      //This is a rows x columns array. That is, it is an array of rows.
      int[][] pixelData = new int[height][width];
      
      for (int row = 0; row < height; row++) {
         for (int col = 0; col < width; col ++) {
            pixelData[row][col] = packedData[(row * width) + col];
         }
      }
      return pixelData;
   }
   
   //Uses bitwise operations to convert four integer (ranging from 0 to 255)
   //into a single integer for use with the BufferedImage class.
   public static int[] packPixels(int[][] pixelData) {
      System.out.println("putting pixel values in packed format...");
      int height = pixelData.length;
      int width = pixelData[0].length;
      int[] packedData = new int[width*height];
      
      for (int row = 0; row < height; row ++) {
         for (int col = 0; col < width; col ++) {
            packedData[(row * width) + col] = pixelData[row][col];
         }
      }
      return packedData;
   }
   
   
   
   
   public static boolean writeImage(int[][] pixelData, BufferedImage img, File file) {
      int[] packedData = packPixels(pixelData);
      int height = pixelData.length;
      int width = pixelData[0].length;
      BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      im.getRaster().setDataElements(0, 0, width, height, packedData);
      
      //put pixelData into packedData
      
      //Write new packed array back into BufferedImage
      
      
      //bi.setRGB(startX, startY, w, h, rgbArray, offset, scansize)
      //im.setRGB(0, 0, width, height, packedData, 0, width);
      String filename = file.getName();
      int lastPeriod = filename.indexOf(".");
      String extension;
      String[] exts = ImageIO.getWriterFileSuffixes();
      
      if (lastPeriod==-1) {
         extension = ".png";
      }else {
         extension = filename.substring(lastPeriod+1);
      }
      
      try{
         ImageIO.write(im, extension, file);
      } catch (IOException e) {
         System.out.println("Writing image failed: "+e);
         System.out.println("The following output formats are supported:\n"+
         java.util.Arrays.toString(exts));
         return false;
      }
      
      return true;
   }
   
   
   
   
   
   
}
