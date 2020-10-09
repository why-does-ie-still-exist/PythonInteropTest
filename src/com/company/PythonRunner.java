package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class PythonRunner {
    public static void main(String[] args) throws IOException, URISyntaxException {
        var start = System.nanoTime();
        System.out.println(runInternalScript("/printtest.py"));
        var end = System.nanoTime();
        System.out.println("time: " + (end - start));
    }

    public static String runInternalScript(String location) throws URISyntaxException, IOException {
        URL url = PythonRunner.class.getResource(location);
        var path = Paths.get(url.toURI());
        System.out.println(path.toAbsolutePath().toString());
        Process script = runPyScript(path.toString());
        return getOutput(script);
    }

    public static String getOutput(Process p) throws IOException {
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));
        var buffer = new StringBuffer();
        for (String s : stdInput.lines().toArray(String[]::new)) buffer.append(s);
        return buffer.toString();
    }

    public static Process runPyScript(String scriptlocation) throws IOException {
        return Runtime.getRuntime().exec(new String[]{"python", scriptlocation});
    }
}
