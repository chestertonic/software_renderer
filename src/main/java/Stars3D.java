public class Stars3D {
    private final float m_spread;
    private final float m_speed;

    private final float m_starX[];
    private final float m_starY[];
    private final float m_starZ[];

    public Stars3D(int numStars, float spread, float speed) {
        m_spread = spread;
        m_speed = speed;

        m_starX = new float[numStars];
        m_starY = new float[numStars];
        m_starZ = new float[numStars];

        for (int i = 0; i < m_starX.length; i++) {
            initStar(i);
        }
    }

    /**
     * Initializes a star to a new pseudo-random location in 3D space.
     * @param index The index of the star to initialize
     */
    public void initStar(int index) {

        // Random values are subtracted by 0.5 and multiplied by 2 to
        // remap their coordinate range from (0, 1) to (-1, 1)
        m_starX[index] = 2 * ((float) Math.random() - 0.5f) * m_spread;
        m_starY[index] = 2 * ((float) Math.random() - 0.5f) * m_spread;
        // The z coordinate is adjusted to prevent a star from generating
        // too close to 0.
        m_starZ[index] = ((float) Math.random() + 0.0001f) * m_spread;
    }

    public void updateAndRender(Bitmap target, float delta) {
        // Clear to a black background
        target.clear((byte) 0x00);

        float halfWidth = target.getWidth() / 2.0f;
        float halfHeight = target.getHeight() / 2.0f;
        for (int i = 0; i < m_starX.length; i++) {
            // Have star z position (forward/backward in screen space)
            // move towards the "camera". 0 near/ +1 far (negative behind camera)
            m_starZ[i] -= delta * m_speed;


            // If star moves behind camera, reinitialize it
            if (m_starZ[i] <= 0) {
                initStar(i);
            }

            // convert star in camera space to screen space (-1/+1 to 0/screen_width or 0/screen_height)
            int x = (int) ((m_starX[i] / m_starZ[i]) * halfWidth + halfWidth);
            int y = (int) ((m_starY[i] / m_starZ[i]) * halfHeight + halfHeight);

            // If x or y of star is out of range, reinitialize
            if (x <= 0 || x >= target.getWidth() || y <= 0 || y >= target.getHeight()) {
                initStar(i);
            } else {
                // if not, the draw the star to the screen
                target.drawPixel(x, y, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff);
            }
        }
    }
}
