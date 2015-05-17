/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.client.android.decoding;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.zxing.client.android.camera.CameraManager;
import com.openatlas.qrcode.BarCode;
import com.openatlas.qrcode.CaptureActivity;
import com.openatlas.qrcode.R;

final class DecodeHandler extends Handler {

  private static final String TAG = DecodeHandler.class.getSimpleName();

  private final CaptureActivity activity;

	// private final MultiFormatReader multiFormatReader;

	DecodeHandler(CaptureActivity activity) {
		// multiFormatReader = new MultiFormatReader();
		// multiFormatReader.setHints(hints);
    this.activity = activity;
  }

  @Override
  public void handleMessage(Message message) {
    switch (message.what) {
      case R.id.decode:
        //Log.d(TAG, "Got decode message");
        decode((byte[]) message.obj, message.arg1, message.arg2);
        break;
      case R.id.quit:
        Looper.myLooper().quit();
        break;
    }
  }

	//BarCode mBarCode = new BarCode();

  /**
   * Decode the data within the viewfinder rectangle, and time how long it took. For efficiency,
   * reuse the same reader objects from one decode to the next.
   *
   * @param data   The YUV preview frame.
   * @param width  The width of the preview frame.
   * @param height The height of the preview frame.
   */
  private void decode(byte[] data, int width, int height) {
    long start = System.currentTimeMillis();
		// Result rawResult = null;
    
    //modify here
    byte[] rotatedData = new byte[data.length];
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++)
            rotatedData[x * height + height - y - 1] = data[x + y * width];
    }
    int tmp = width; // Here we are swapping, that's the difference to #11
    width = height;
    height = tmp;
		Rect rect = CameraManager.get().getFramingRectInPreview();
		String dataString = BarCode.decodeCrop(rotatedData, width, height,
				rect.left, rect.top, rect.width(), rect.height());
//		PlanarYUVLuminanceSource source = CameraManager.get()
//				.buildLuminanceSource(rotatedData, width, height);
		System.out.println("DecodeHandler.decode()"+dataString);
		// BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		// try {
		// rawResult = multiFormatReader.decodeWithState(bitmap);
		// } catch (ReaderException re) {
		// // continue
		// } finally {
		// multiFormatReader.reset();
		// }

		if (dataString != null) {

      long end = System.currentTimeMillis();
			Log.d(TAG, "Found barcode (" + (end - start) + " ms):\n"
					+ dataString);
			Message message = Message.obtain(activity.getHandler(),
					R.id.decode_succeeded, dataString);
//      Bundle bundle = new Bundle();
//			bundle.putParcelable(DecodeThread.BARCODE_BITMAP,
//					source.renderCroppedGreyscaleBitmap());
//      message.setData(bundle);
      //Log.d(TAG, "Sending decode succeeded message...");
      message.sendToTarget();
    } else {
      Message message = Message.obtain(activity.getHandler(), R.id.decode_failed);
      message.sendToTarget();
    }
  }

}
