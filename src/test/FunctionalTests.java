package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sorting.Sorter;

public class FunctionalTests {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Creates a symbolic link linkName->target by spawning a subprocess.
     * 
     * This method only works on Unix-like systems.
     * 
     * @param target
     * @param linkName
     * @return subprocess exit value (0 if successful, >0 otherwise)
     * @throws IOException
     * @throws InterruptedException
     */
    private int symlink(String target, String linkName) throws IOException, InterruptedException {
        String[] command = new String[] { "ln", "--symbolic", "--force", target, linkName };
        
        return runSystemCommand(command);
    }

    /**
     * Runs `diff -u` on to files and prints the result to System.out.
     * 
     * @param result name of the file to test
     * @param expected name of the file with expected content
     * @return 0 if the files are equal, >0 otherwise
     * @throws InterruptedException 
     * @throws IOException 
     */
    private int compareFiles(String result, String expected) throws IOException, InterruptedException {
        String[] command = new String[] { "diff", "--unified", result, expected };
        
        return runSystemCommand(command);
    }

    /**
     * Spawns a subprocess specified by the command line `command`. Prints the subprocess' stdout
     * to System.out and stderr to System.err.
     * 
     * @param command
     * @return subprocess exit value (0 if successful, >0 otherwise)
     * @throws IOException 
     * @throws InterruptedException 
     */
    private int runSystemCommand(String[] command) throws IOException, InterruptedException {

        Process proc = Runtime.getRuntime().exec(command);

        // TODO: kolla upp ProcessBuilder och dess redirectOutput istället.
        BufferedReader stdout = new BufferedReader(
                new InputStreamReader(proc.getInputStream()));
        BufferedReader stderr = new BufferedReader(
                new InputStreamReader(proc.getErrorStream()));

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

    @Test
    public void test5() throws IOException, InterruptedException {
        // flytta start, finish och namnfil
        int error;
        error = symlink("acceptance/acceptanstest5/starttider.txt", "start.txt");
        assertEquals("Failed to create symbolic link", 0, error);
        error = symlink("acceptance/acceptanstest5/maltider.txt", "finish.txt");
        assertEquals("Failed to create symbolic link", 0, error);
        error = symlink("acceptance/acceptanstest5/namnfil.txt", "namnfil.txt");
        assertEquals("Failed to create symbolic link", 0, error);
        // new Sorter
        new Sorter(1);
        // kolla result.txt
        error = compareFiles("result.txt", "acceptance/acceptanstest5/resultat.txt");
        assertEquals("resultatfiler inte identiska", 0, error);
    }

}
