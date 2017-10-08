package teachingassistant.uploader;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import teachingassistant.teacher.Teacher;

public class Uploader implements Runnable {

	private final static String URL = "http://intranet.isistan.unicen.edu.ar:8081/Logs/";

	@Override
	public void run() {
		File folder = new File(Teacher.LOG_PATH);

		for (final File file : folder.listFiles()) {
			if (file.getName().endsWith(".zip")) {
				sendFile(file);
			}
		}

	}

	private void sendFile(File file) {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost uploadFile = new HttpPost(URL);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addBinaryBody("file", file);
			HttpEntity multipart = builder.build();
			uploadFile.setEntity(multipart);
			CloseableHttpResponse response = httpClient.execute(uploadFile);

			if (response.getStatusLine().getStatusCode() == 200)
				file.delete();
		} catch (IOException e) {
		}

	}
}
