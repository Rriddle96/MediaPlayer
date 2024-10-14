# MediaPlayer

## Synopsis
This project that I've made is a simple media player that uses audio files from local folders to play.

## Code example 
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

## contributors
As of now I am the only one who has contributed to this project.
