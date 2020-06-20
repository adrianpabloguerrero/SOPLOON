package ar.edu.unicen.isistan.si.teachingassistant.plugin.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

public class JsonFile<T> {

	private String path;

	public JsonFile(String path) {
		this.path = path;
	}

	public boolean store(T content) {
		Gson gson = new Gson();

		String jsonContent = gson.toJson(content);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path))) {
			writer.write(jsonContent);
			writer.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public T read(Class<T> tClass) {
		File file = new File(this.path);
		if (file.exists()) {
			try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				StringBuilder content = new StringBuilder();
				String line = null;
				while ((line = bufferedReader.readLine()) != null)
					content.append(line);
				Gson gson = new Gson();
				return gson.fromJson(content.toString(), tClass);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

}
