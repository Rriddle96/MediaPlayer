import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;


public class MediaPlayerBackgrounds {

	public static Group createChillwaveBackground() {
        Group chillwaveBackground = new Group();

    // Set up the gradient background (sky)
    Rectangle background = createGradientBackground();
    chillwaveBackground.getChildren().add(background);

    // Add the sun to the scene
    Circle sun = createSun();
    chillwaveBackground.getChildren().add(sun);
    
    Polygon mountain = createMountain();
    chillwaveBackground.getChildren().add(mountain);
    
    Group lines = createHorizontalLoopLines();
    chillwaveBackground.getChildren().add(lines);


    return chillwaveBackground;
}
	
	

	private static Rectangle createGradientBackground() {
	    Rectangle background = new Rectangle(800, 600); // Set the size of the rectangle to match the window
	    background.setX(0); // Make sure it starts at the top-left corner
	    background.setY(0);
	    // ... (rest of your gradient setup)
    // Create a linear gradient for the vaporwave sky
    LinearGradient gradient = new LinearGradient(
        0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
        new Stop(0, Color.rgb(158, 48, 14)),
        new Stop(0.5, Color.rgb(130, 12, 51)),
        new Stop(1, Color.rgb(130, 47, 5))
    );
    background.setFill(gradient);

    return background;
}

private static Circle createSun() {
    // Create a sun with a radial gradient
    Circle sun = new Circle(400, 150, 100); // Adjusted y-coordinate for better visibility

    RadialGradient sunGradient = new RadialGradient(
        0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
        new Stop(0, Color.rgb(186, 46, 19)),
        new Stop(1, Color.rgb(219, 83, 20))
    );
    sun.setFill(sunGradient);

    return sun;
}

private static Polygon createMountain() {
    // Define the points of the polygon
    double[] points = {
        0, 600,
        0, 232,
        150, 300,
        200, 200,
        400, 150,
        430, 200,
        500, 100,
        600, 300,
        500, 300,
        600, 200,
        700, 140,
        800, 300,
        800, 600,
        0, 600 // Close the polygon by returning to the starting point
    };

    // Create a Polygon with the defined points
    Polygon mountain = new Polygon(points);
    
    LinearGradient gradient = new LinearGradient(
            0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.rgb(38, 17, 10)),
            new Stop(0.5, Color.rgb(31, 12, 19)),
            new Stop(1, Color.rgb(9, 35, 38))
        );
        mountain.setFill(gradient);
		return mountain;   
}

private static Group createHorizontalLoopLines() {
    Group linesGroup = new Group();
    
    double windowWidth = 800; // Assuming the window width is 800
    double windowHeight = 400; // Assuming the window height is 600
    double vanishingPointX = windowWidth / 2; // X coordinate of the vanishing point (center of the window)
    double vanishingPointY = windowHeight; // Y coordinate of the vanishing point (bottom of the window)
    
    int numberOfLines = 20; // Increased number of lines for better effect
    double topY = 600; // Y-coordinate where the first line starts
    double maxLineLength = windowWidth * 0.8; // Maximum length of the lines (80% of window width)

    for (int i = 0; i < numberOfLines; i++) {
        double t = (double) i / (numberOfLines - 1); // Parameter from 0 to 1
        
        // Calculate start and end points for each line
        double startY = topY + (vanishingPointY - topY) * t;
        double endY = startY;
        
        double lineLength = maxLineLength * (1 - t); // Lines get shorter as they approach the vanishing point
        double startX = vanishingPointX - lineLength / 2;
        double endX = vanishingPointX + lineLength / 2;
        
        Line line = new Line(startX, startY, endX, endY);
        
        // Adjust color and opacity
        double opacity = 0.7 - 0.5 * t; // Lines fade out as they approach the vanishing point
        line.setStroke(Color.rgb(72, 217, 76, opacity));
        
        // Adjust line thickness
        double thickness = 3 * (1 - t) + 0.5; // Lines get thinner as they approach the vanishing point
        line.setStrokeWidth(thickness);
        
        linesGroup.getChildren().add(line);
    }

    return linesGroup;
}



public static Group createVaporwaveBackground() {
    Group vaporwaveBackground = new Group();

    // Set up the canvas size
    double width = 800;
    double height = 600;
    Canvas canvas = new Canvas(width, height);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    // Draw the gradient background
    drawGradientBackground(gc, width, height);

    // Draw the checkerboard pattern
    drawCheckerboard(gc, width, height / 2);

    // Add the canvas to the group
    vaporwaveBackground.getChildren().add(canvas);

    return vaporwaveBackground;
}

private static void drawGradientBackground(GraphicsContext gc, double width, double height) {
    LinearGradient gradient = new LinearGradient(
            0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.SKYBLUE),
            new Stop(1, Color.MAGENTA)
    );
    gc.setFill(gradient);
    gc.fillRect(0, 0, width, height);
}

private static void drawCheckerboard(GraphicsContext gc, double width, double height) {
    int tileSize = 50; // Size of each square in the checkerboard
    boolean isWhite = true;

    for (int row = 0; row < height / tileSize; row++) {
        for (int col = 0; col < width / tileSize; col++) {
            gc.setFill(isWhite ? Color.WHITE : Color.BLACK);
            gc.fillRect(col * tileSize, (height / 2) + (row * tileSize), tileSize, tileSize);
            isWhite = !isWhite;
        }
        isWhite = !isWhite;
    }
}

public static Group createSynthwaveBackground() {
    Group synthwaveBackground = new Group();

    // Set up the canvas size
    double width = 800;
    double height = 600;
    Canvas canvas = new Canvas(width, height);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    // Draw the gradient background
    drawGradientBackground(gc);

    // Draw the animated perspective grid
    AnimationTimer animationTimer = new AnimationTimer() {
        private double offsetY = 0; // Offset to control the vertical position of lines

        @Override
        public void handle(long now) {
            // Clear the canvas before redrawing
            gc.clearRect(0, 0, width, height);
            drawGradientBackground(gc);
            drawGrid(gc, offsetY);
            drawMirroredGrid(gc, offsetY);

            // Update the offset for the animation
            offsetY += 1; // Speed of the animation
            if (offsetY >= 20) {
                offsetY = 0; // Reset the offset to create a continuous loop
            }
        }
    };
    animationTimer.start();

    synthwaveBackground.getChildren().add(canvas);
    return synthwaveBackground;
}

private static void drawGradientBackground(GraphicsContext gc) {
    // Create a gradient background from dark purple to neon pink
    LinearGradient gradient = new LinearGradient(
        0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
        new Stop(0, Color.DARKSLATEBLUE),
        new Stop(1, Color.HOTPINK)
    );
    gc.setFill(gradient);
    gc.fillRect(0, 0, 800, 600);
}

private static void drawGrid(GraphicsContext gc, double offsetY) {
    gc.setStroke(Color.MAGENTA);
    gc.setLineWidth(1.0);

    // Draw horizontal grid lines for the bottom half with the offset for animation
    for (int y = 300; y <= 600; y += 20) {
        double animatedY = y + offsetY;
        double xStart = 400 - (animatedY - 300); // Adjusted to align with perspective lines
        double xEnd = 400 + (animatedY - 300);
        gc.strokeLine(xStart, animatedY, xEnd, animatedY);
    }

    // Draw perspective grid lines for the bottom half
    for (int i = 0; i <= 800; i += 20) {
        gc.strokeLine(400, 300, i, 600);
    }
}

private static void drawMirroredGrid(GraphicsContext gc, double offsetY) {
    gc.setStroke(Color.MAGENTA);
    gc.setLineWidth(1.0);

    // Draw horizontal grid lines for the top half (mirrored) with the offset for animation
    for (int y = 300; y >= 0; y -= 20) {
        double animatedY = y - offsetY;
        double xStart = 400 - (300 - animatedY); // Adjusted to align with perspective lines
        double xEnd = 400 + (300 - animatedY);
        gc.strokeLine(xStart, animatedY, xEnd, animatedY);
    }

    // Draw mirrored perspective grid lines for the top half
    for (int i = 0; i <= 800; i += 20) {
        gc.strokeLine(400, 300, i, 0);
    }
}
}

