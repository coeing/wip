
package helpers;

import java.util.Random;

/**
 * Generator for alpha-numeric codes.
 * 
 * @author Christian Oeing
 * @date 16.02.2013
 * 
 */
public class CodeGenerator
{

    /**
     * Symbols to use in user code.
     */
    private static final char[] symbols = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();

    /**
     * Buffer to hold user codes during generation.
     */
    private final char[] buf;

    /**
     * Random number generator.
     */
    private final Random random = new Random();

    /**
     * Constructor.
     * 
     * @param length
     *            Length of generated codes.
     */
    public CodeGenerator(int length)
    {
        if (length < 1)
        {
            throw new IllegalArgumentException("length < 1: " + length);
        }
        this.buf = new char[length];
    }

    /**
     * Generates a new code.
     * 
     * @return A new generated code with the length which was specified in the constructor.
     */
    public String nextCode()
    {
        for (int idx = 0; idx < this.buf.length; ++idx)
        {
            this.buf[idx] = symbols[this.random.nextInt(symbols.length)];
        }
        return new String(this.buf);
    }

}
