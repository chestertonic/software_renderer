import java.util.Arrays;

public class Bitmap {
    private final int m_width;
    private final int m_height;

    /* Pixel data will be stored as single array with each element representing
       a single color component of a pixel (i.e., [A1, R1, G1, B1, A2, R2, ...]).
       With each pixel being composed of 4 elements of the array.
     */
    private final byte m_components[];

    public Bitmap(int width, int height) {
        m_width = width;
        m_height = height;
        m_components = new byte[m_width * m_height * 4];
    }

    /**
     * Fills component array to specified shade of grey.
     * @param shade The fill shade
     */
    public void clear(byte shade) {
        Arrays.fill(m_components, shade);
    }

    /**
     * Sets the pixel at (x, y) to the color specified by (a, b, g, r).
     * @param x The x position of the pixel
     * @param y The y position of the pixel
     * @param a The alpha color component
     * @param b The blue color component
     * @param g The green color component
     * @param r The red color component
     */
    public void drawPixel(int x, int y, byte a, byte b, byte g, byte r) {
        int index = (x + y * m_width) * 4;
        m_components[index] = a;
        m_components[index + 1] = b;
        m_components[index + 2] = g;
        m_components[index + 3] = r;
    }

    /**
     * Copy bitmap components (ABGR) into destination byte array
     * (BGR).
     * @param dest Destination array
     */
    public void copyToByteArray(byte[] dest) {
        for (int i = 0; i < m_width * m_height; i++) {
            dest[i * 3] = m_components[i * 4 + 1];
            dest[i * 3 + 1] = m_components[i * 4 + 2];
            dest[i * 3 + 2] = m_components[i * 4 + 3];
        }
    }

    public int getWidth() {
        return m_width;
    }

    public int getHeight() {
        return m_height;
    }
}
