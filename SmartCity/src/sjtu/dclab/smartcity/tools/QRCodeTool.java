package sjtu.dclab.smartcity.tools;

import java.io.IOException;
import java.util.Hashtable;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.HybridBinarizer;

/**
/**
 * Created by zwsr/Zhang Wei on 2015/8/4.
 */
public class QRCodeTool {
    private static int QR_WIDTH = 400, QR_HEIGHT = 400;
    public QRCodeTool(){
    }

    //通过传入字符串src生成对应的二维码
    //返回的图片对象为BitMap,默认大小定义在类中,默认为400x400
    public static Bitmap createQRBitmap(String src)
    {
        Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
        try
        {
            //简单判断src合法性
            if (src == null || src.length() < 1)
            {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

            BitMatrix bitMatrix = new QRCodeWriter().encode(src, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];

            for (int y = 0; y < QR_HEIGHT; y++)
            {
                for (int x = 0; x < QR_WIDTH; x++)
                {
                    if (bitMatrix.get(x, y))
                    {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    }
                    else
                    {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888

            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            return bitmap;


        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }
        return bitmap;

    }


    //通过传入位图Bitmap(Adroid格式)生成对应的字符串
    //返回的图片对象为String
    public static String decodeQRBitmap(Bitmap bMap){
        String decode_str = "error decoding";
        try {
            Hashtable hints = new Hashtable();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

            int[] intArray = new int[bMap.getWidth() * bMap.getHeight()];
//copy pixel data from the Bitmap into the 'intArray' array
            bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(), bMap.getHeight());

            LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(), intArray);

            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result decode_rst = new MultiFormatReader().decode(bitmap, hints);
            decode_str = decode_rst.getText();

            return decode_str;
        }catch (ReaderException re) {
            System.out.println(re.toString());
        }
        catch (Exception ex) {
        }
        return decode_str;
}

}
