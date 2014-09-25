package com.notjay.utils.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Command {
	private String cmd;
	private String output;
	private String error;
	private boolean hasExited;

	private class StreamReader extends Thread {
		private InputStream stream;
		private String streamOut;
		private StreamReader(InputStream stream) {
			this.stream = stream;
			streamOut = "";
		}
		public void run() {
			try {
				InputStreamReader isr = new InputStreamReader(stream);
				BufferedReader reader = new BufferedReader(isr);
				String line;
				while((line = reader.readLine()) != null)
					streamOut += line + String.format("%n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Command(String cmd) {
		this.cmd = cmd;
		output = "";
		error = "";
		hasExited = false;
	}

	public int exec() {
		hasExited = false;
		try {
			String[] cmd = {"cmd.exe", "/c", this.cmd};

			ProcessBuilder process = new ProcessBuilder(cmd);
			process.redirectError();
			process.redirectOutput();

			Process p = process.start();
			StreamReader outputReader = new StreamReader(p.getInputStream());
			StreamReader errorReader = new StreamReader(p.getErrorStream());
			outputReader.run();
			errorReader.run();

			int returnValue = p.waitFor();
			this.output = outputReader.streamOut;
			this.error = errorReader.streamOut;

			hasExited = true;
			return returnValue;
		} catch(IOException|InterruptedException e) {
			return -1;
		}
	}

	public String getOutput() {
		if(hasExited) return output;
		return null;
	}

	public String getError() {
		if(hasExited) return error;
		return null;
	}
}
