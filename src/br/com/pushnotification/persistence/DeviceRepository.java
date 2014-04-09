package br.com.pushnotification.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;



public class DeviceRepository {

	private static final String SEPARATOR = "######";

	public static void saveDevice(String baseDir, String deviceId, String regId)
			throws IOException {

		HashMap<String, String> devices = getDevices(baseDir);

		File f = new File(baseDir, "devices");
		if (!f.exists())
			f.createNewFile();

		FileOutputStream fos = new FileOutputStream(f);
		BufferedWriter out = new BufferedWriter(new FileWriter(f, true));

		devices.put(deviceId, regId);
		Set<String> deviceKeys = devices.keySet();
		for (String keys : deviceKeys) {
			out.write(keys + SEPARATOR + devices.get(keys) + '\n');
		}

		out.close();
		fos.close();
	}

	public static HashMap<String, String> getDevices(String baseDir)
			throws IOException {

		HashMap<String, String> devices = new HashMap<String, String>();

		File f = new File(baseDir, "devices");
		if (f.exists()) {
			
			InputStream is = new FileInputStream(f);
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));

			String line = "";
			while ((line = rd.readLine()) != null) {
				
				String[] info = line.split(SEPARATOR);
				devices.put(info[0], info[1]);
			}
			rd.close();
			is.close();
		}
		return devices;
	}

}
