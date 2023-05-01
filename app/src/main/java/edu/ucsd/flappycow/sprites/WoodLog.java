package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class WoodLog extends IGameObstacle{

    @Override
    public void onInitBitmap() {
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }
}
