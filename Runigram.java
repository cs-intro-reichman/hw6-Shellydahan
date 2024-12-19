import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		//print(tinypic);

		// Creates an image which will be the result of various 
		 //image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		//image = flippedHorizontally(tinypic);
		//image = flippedVertically(tinypic);
		//image = grayScaled(tinypic);
		image = scaled(tinypic, 3, 5);
		System.out.println();
	    print(image);
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for (int i = 0; i < image.length; i++){
			for (int j = 0; j < image[0].length; j++){
				int red = in.readInt();
		        int green = in.readInt();
		        int blue = in.readInt();
			    Color c  = new Color(red, green, blue);
				image [i][j] = c;
			}
		}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
		for (int i = 0; i < image.length; i++){
			for (int j = 0; j < image[0].length; j++){
				print(image[i][j]);
			}
			System.out.println();
		}
			
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		int r = image.length;
		int c = image[0].length;
		Color[][] flippedImageV = new Color[r][c];
        for (int i = 0; i < r; i++){
			for (int j = 0; j < c; j++){
			   flippedImageV[i][j] = image [i][c-1-j];
			}
		}
		return flippedImageV;
		
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		int r = image.length;
		int c = image[0].length;
		Color[][] flippedImageH = new Color[r][c];
        for (int i = 0; i < r; i++){
			for (int j = 0; j < c; j++){
			   flippedImageH[i][j] = image [r-1-i][j];
			}
		}
		return flippedImageH;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		int r = pixel.getRed();
		int g = pixel.getGreen();
		int b = pixel.getBlue();
		Color lum  = new Color( (int) (0.299 * r + 0.587 * g + 0.114 * b) , (int) (0.299 * r + 0.587 * g + 0.114 * b), (int)(0.299 * r + 0.587 * g + 0.114 * b));
		return lum;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		Color[][] greyImage = new Color[image.length][ image[0].length];
		 for (int i = 0; i < image.length; i++){
			for (int j = 0; j < image[0].length; j++){
				greyImage[i][j] = luminance(image[i][j]);
			}
		 }
		return greyImage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		Color[][] scaleImage = new Color[height][width];
		int h0 = image.length;
		int w0 = image[0].length;
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				scaleImage[i][j] = image[i * h0/height][j * w0/width];
			}
		}
		return scaleImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		int r = (int) (alpha * c1.getRed() + (1 - alpha) * c2.getRed());
		int g = (int) (alpha * c1.getGreen() + (1 - alpha) * c2.getGreen());
		int b = (int) (alpha * c1.getBlue() + (1 - alpha) * c2.getBlue());
		Color blendC  = new Color(r, g, b);
		return blendC;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		Color[][] blendImage = new Color[image1.length][image1[0].length];
		for (int i = 0; i < image1.length; i++){
			for (int j = 0; j < image1[0].length; j++){
                Color blendC = blend(image1[i][j], image2[i][j], alpha);
				blendImage[i][j] = blendC;
			}
		}
		
		return blendImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		target = scaled(target, source[0].length, source.length);
		double alpha = 0.0;
		for (int i = 0; i <= n; i++){
			alpha = (double)(n - i)/n;
			Color[][] morphImage = blend (source, target, alpha);
			Runigram.display(morphImage);
			StdDraw.pause(500);
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

