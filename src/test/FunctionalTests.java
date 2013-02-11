package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sorting.Sorter;

public class FunctionalTests {
	
	@Before
	public void setUp() {
		// remove existing files
        File file;
        file = new File("start.txt");
        file.delete();
        file = new File("finish.txt");
        file.delete();
        file = new File("namnfil.txt");
        file.delete();
	}

    /**
     * Creates a symbolic link linkName->target by spawning a subprocess.
     * 
     * This method only works on Unix-like systems.
     * 
     * @param target
     *            path to linked file
     * @param linkName
     *            name of the new symbolic link
     * @throws RuntimeException
     *             if the symbolic link could probably not be created
     * @throws IOException
     * @throws InterruptedException
     */
    private void symlink(String target, String linkName) throws IOException,
            InterruptedException {
		String[] command = new String[] { "ln", "--symbolic", target, linkName };

        int error = runSystemCommand(command);
        if (error != 0) {
            throw new RuntimeException("Failed to create symbolic link " + linkName);
        }
    }

    /**
     * Runs `diff -u` on to files and prints the result to System.out.
     * 
     * @param result
     *            name of the file to test
     * @param expected
     *            name of the file with expected content
     * @return 0 if the files are equal, >0 otherwise
     * @throws InterruptedException
     * @throws IOException
     */
    private int compareFiles(String result, String expected)
            throws IOException, InterruptedException {
        String[] command = new String[] { "diff", "--unified", result, expected };

        int error = runSystemCommand(command);
        if (error != 0) {
            // prettify
            System.out.println();
        }
        return error;
    }

    /**
     * Spawns a subprocess specified by the command line `command`. Prints the
     * subprocess' stdout to System.out and stderr to System.err.
     * 
     * @param command
     * @return subprocess exit value (0 if successful, >0 otherwise)
     * @throws IOException
     * @throws InterruptedException
     */
    private int runSystemCommand(String[] command) throws IOException,
            InterruptedException {

        Process proc = Runtime.getRuntime().exec(command);

        // TODO: kolla upp ProcessBuilder och dess redirectOutput istället.
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                proc.getInputStream()));
        BufferedReader stderr = new BufferedReader(new InputStreamReader(
                proc.getErrorStream()));

        int exitValue = proc.waitFor();
        while (stdout.ready()) {
            String line = stdout.readLine();
            System.out.println(line);
        }
        while (stderr.ready()) {
            String line = stderr.readLine();
            System.err.println(line);
        }

        proc.destroy();
        return exitValue;
    }

    /**
     * Sets up symbolic links, runs Sorter as a lap race and compares the
     * resulting file with an expected one.
     * 
     * @param laps
     *            number of laps to pass to Sorter
     * @param start
     *            path to start file
     * @param finish
     *            path to finish file
     * @param namnfil
     *            path to namnfil
     * @param result
     *            path to expected result file
     */
    private void singleFilesLapTest(int laps, String start, String finish,
            String namnfil, String result) {
        try { // catch exceptions here to decrease the amount of boilerplate
              // code in the tests
            symlink(start, "start.txt");
            symlink(finish, "finish.txt");
            symlink(namnfil, "namnfil.txt");
        } catch (InterruptedException inte) {
            inte.printStackTrace();
            fail(inte.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            fail(ioe.getMessage());
        }

        new Sorter(laps);
        
        // remove symlinks after test
        File file;
        file = new File("start.txt");
        assertTrue("start.txt kunde inte raderas", file.delete());
        file = new File("finish.txt");
        assertTrue("finish.txt kunde inte raderas", file.delete());
        file = new File("namnfil.txt");
        assertTrue("namnfil.txt kunde inte raderas", file.delete());

        // kolla result.txt
        int error = -1; // -1 is certainly bad
        try {
            error = compareFiles("result.txt", result);
        } catch (InterruptedException inte) {
            inte.printStackTrace();
            fail(inte.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            fail(ioe.getMessage());
        }
        assertEquals("resultatfiler inte identiska", 0, error);
    }
    
    private void simpleLapTest(String number) {
        singleFilesLapTest(1, "acceptance/acceptanstest" + number + "/starttider.txt",
                "acceptance/acceptanstest" + number + "/maltider.txt",
                "acceptance/acceptanstest" + number + "/namnfil.txt",
                "acceptance/acceptanstest" + number + "/resultat.txt");
    }

    @Test
    public void test3_4() {
        simpleLapTest("3_4");
    }

    @Test
    public void test3_5() {
        simpleLapTest("3_5");
    }

    @Test
    public void test5() {
        simpleLapTest("5");
    }

    @Test
    public void test6() {
        simpleLapTest("6");
    }

    @Test
    public void test9() {
        simpleLapTest("9");
    }

    @Test
    public void test15() {
        // ParseException innan iteration 3 för att starttider har ett extra \n i slutet
        simpleLapTest("15");
    }

    @Test
    public void test16() {
        simpleLapTest("16");
    }

}
