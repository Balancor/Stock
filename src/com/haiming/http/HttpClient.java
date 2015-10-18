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
				int headerEndIndex = 0;
				int responseLength = 0;
				int readIndex = 0;
				int ret = 0;

				do{
					ret = receiveStream.read(buffer);
					readIndex += ret;
					content.append(new String(buffer));
					headerEndIndex = content.toString().indexOf("\r\n\r\n");
					if(headerEndIndex > 0)
						break;
				}while(headerEndIndex <= 0);

				String header = content.toString().substring(0, headerEndIndex + 4);
				response = new HttpResponse(header.getBytes());
				responseLength  = (header.length() + Integer.parseInt(response.getHeader(HttpResponse.RESPONSE_HEADER_CONTENT_LENGTH)));
				
				buffer = null;
				buffer = new byte[1024];
				while(readIndex < responseLength ){
					size = receiveStream.available();
					if(size < 1024){
						buffer = null;
						buffer = new byte[1024];
						ret = receiveStream.read(buffer, readIndex, responseLength - readIndex);
					} else {
						ret = receiveStream.read(buffer, readIndex, 1024);
					}
					content.append(new String(buffer));
				}

//				Log.d(new String(content.toString()));

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
