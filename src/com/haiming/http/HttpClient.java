package com.haiming.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.haiming.utils.Log;

public class HttpClient implements OnRequestChangedListener {

	private static final int DEFAULT_PORT = 80;
	private Socket mSocket = null;
	private HttpRequest mRequest;

	public HttpClient(HttpRequest request) {
		init(request);
	}

	private void init(HttpRequest request) {
		mRequest = request;

		try {
			mSocket = new Socket(request.getHost(), DEFAULT_PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void increateByteArraySize(byte[] sourceByte) {
		int length = sourceByte.length;
		if (length <= 0)
			return;
		byte[] temp = new byte[2 * length];
		for (int i = 0; i < length; i++) {
			temp[i] = sourceByte[i];
		}
		sourceByte = temp;
	}

	public HttpResponse execute() {
		HttpResponse response = null;
		OutputStream sendStream = null;
		InputStream receiveStream = null;
		StringBuffer content = new StringBuffer();
		try {
			if (mSocket != null) {
				sendStream = mSocket.getOutputStream();
				receiveStream = mSocket.getInputStream();
				sendStream.write(mRequest.toBytes());

				// Log.d("Request: "+mRequest.getRequest());
				sendStream.flush();
				// Thread.sleep(2000);
				int size = 0;
				byte[] buffer = null;
				while ((size = receiveStream.available()) <= 0) {
					continue;
				}

				Log.d("Size: " + size);
				buffer = new byte[size];
				while (receiveStream.read(buffer) != -1) {
					content.append(new String(buffer));
				}

				Log.d(new String(content.toString()));

				response = new HttpResponse(content.toString().getBytes());

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

	@Override
	public void onRequestChangedListner() {

	}

}
