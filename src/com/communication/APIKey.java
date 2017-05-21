package com.communication;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class APIKey {
	private String path;

	public APIKey(String path) {
		this.path = path;
	}

	public String get() {
		String key = "";
		try {
			key = readAPIKeyFromPath(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return key;
	}

	private String readAPIKeyFromPath(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		String key = sc.nextLine().trim();
		sc.close();
		return key;
	}
}
