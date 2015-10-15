package com.haiming.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.haiming.utils.Log;

public class HttpClient {

	private static final int DEFAULT_PORT = 80;
	private Socket mSocket = null;
	private HttpRequest mRequest;

	public HttpClient(HttpRequest request) {
		mRequest = request;

		try {
			mSocket = new Socket(request.getHost(), DEFAULT_PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HttpResponse execute() {
		HttpResponse response = null;
		OutputStream sendStream = null;
		InputStream receiveStream = null;
		try {
			if (mSocket != null) {
				sendStream = mSocket.getOutputStream();
				receiveStream = mSocket.getInputStream();
				sendStream.write(mRequest.toBytes());
				sendStream.flush();
//				Thread.sleep(2000);
				int size = 0;
				byte[] buffer = null;
				while ((size = receiveStream.available()) <= 0) {
					continue;
				}
				buffer = new byte[size];
				receiveStream.read(buffer);
				Log.d(new String(buffer));
				if (buffer != null && buffer.length > 0) {
					response = new HttpResponse(buffer);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				receiveStream.close();
				sendStream.close();
				mSocket.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}

		return response;
	}

}
